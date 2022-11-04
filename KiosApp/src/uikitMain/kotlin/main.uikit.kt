import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import com.makeevrserg.simplekmm.ui.navigation.NavHostComponent
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.cstr
import kotlinx.cinterop.autoreleasepool
import kotlinx.cinterop.useContents
import kotlinx.cinterop.toCValues
import platform.Foundation.NSStringFromClass
import platform.UIKit.UIApplication
import platform.UIKit.UIResponder
import platform.UIKit.UIApplicationDelegateProtocol
import platform.UIKit.UIResponderMeta
import platform.UIKit.UIApplicationDelegateProtocolMeta
import platform.UIKit.UIWindow
import platform.UIKit.UIInterfaceOrientationMask
import platform.UIKit.UIInterfaceOrientationMaskAll
import platform.UIKit.UIScreen
import platform.UIKit.UIApplicationMain

fun main() {
    val args = emptyArray<String>()
    memScoped {
        val argc = args.size + 1
        val argv = (arrayOf("skikoApp") + args).map { it.cstr.ptr }.toCValues()
        autoreleasepool {
            UIApplicationMain(argc, argv, null, NSStringFromClass(SkikoAppDelegate))
        }
    }
}

class SkikoAppDelegate : UIResponder, UIApplicationDelegateProtocol {
    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta

    private val lifecycle = LifecycleRegistry()
    val root by lazy {
        NavHostComponent(DefaultComponentContext(lifecycle = lifecycle))
    }

    @OverrideInit
    constructor() : super()

    private var _window: UIWindow? = null
    override fun window() = _window
    override fun setWindow(window: UIWindow?) {
        _window = window
    }

    override fun application(
        application: UIApplication,
        supportedInterfaceOrientationsForWindow: UIWindow?
    ): UIInterfaceOrientationMask {
        return UIInterfaceOrientationMaskAll
    }

    override fun application(
        application: UIApplication,
        didFinishLaunchingWithOptions: Map<Any?, *>?
    ): Boolean {
        window = UIWindow(frame = UIScreen.mainScreen.bounds)
        window!!.rootViewController = Application("SlackComposeiOS", content = {
            Column {
                root.render()
            }
        })
        window!!.makeKeyAndVisible()
        return true
    }

    override fun applicationDidBecomeActive(application: UIApplication) {
        lifecycle.resume()
    }

    override fun applicationWillResignActive(application: UIApplication) {
        lifecycle.stop()
    }

    override fun applicationWillTerminate(application: UIApplication) {
        lifecycle.destroy()
    }
}
