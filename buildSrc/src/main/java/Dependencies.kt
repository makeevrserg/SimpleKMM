object Dependencies {

    const val version = "1.0.10"
    const val versionCode = 5
    const val compileSdkVersion = 33
    const val minSdkVersion = 21
    const val targetSdkVersion = 33
    const val buildTools = "7.2.2"
    object Domain{
        const val ktor = "2.1.2"
    }

    object DI {
//        const val hiltViewModels = "1.0.0-alpha03"
//        const val daggerHilt = "2.43.2"
//        const val hiltCompiler = "1.0.0"
//        const val daggerHiltTesting = "2.43.2"
//        const val hiltNavigationCompose = "1.0.0"

        const val koin = "3.2.2"
        const val koinAndroid = "3.2.2"
        const val koinAndroidCompose = "3.2.1"
        const val koinKtor = "3.2.2"
    }

    object Google {
        const val gmsAds = "21.1.0"
        const val gmsAuth = "20.2.0"
        const val coroutinesPlayServiceces = "1.6.1"
        const val firebaseBom = "30.1.0"
        const val services = "4.3.10"
        const val crashlytics = "2.9.0"
        const val firebaseMessaging = "23.0.5"
    }


    object Data {
        const val room = "2.4.3"
        const val retrofit = "2.9.0"
        const val retrofitCoroutines = "0.9.2"
        const val realmPlugin = "1.0.2"
    }

    object Kotlin {
        const val version = "1.7.10"
        const val coroutines = "1.6.3"
        const val json = "1.3.3"
        const val kaml = "0.46.0"
        const val serializationJson = "1.3.3"
        const val decompose = "1.0.0-alpha-06"
        const val dateTime = "0.4.0"
    }

    object Android {
        const val application = "7.2.0"
        const val lifecycleExt = "2.2.0"
        const val leakCanary = "2.8.1"
        const val navigation = "2.3.0-alpha01"
        const val constraintLayout = "2.1.3"
        const val androidxCore = "1.2.0"
        const val androidMaterial = "1.5.0"
        const val androidxTestExtJunit = "1.1.3"
        const val espressoCore = "3.4.0"
        const val activityKtx = "1.6.0-alpha04"
        const val androidxAppCompat = "1.1.0"
        const val androidxRecycler = "1.1.0"
        const val materialDesign = "1.1.0"
        const val coroutines = "1.3.9"
        const val lifecycle = "2.5.1"
    }

    object Compose {
        const val compose = "1.3.0"
        const val composeUI = "1.2.1"
        const val composeFoundation = "1.2.1"
        const val composeRuntime = "1.2.1"
        const val composeMaterial = "1.2.1"
        const val composeCompiler = "1.3.0"
        const val navigationCompose = "2.5.1"
        const val accompanistPager = "0.26.2-beta"
        const val composeConstraintLayout = "1.0.1"
        const val composeUiTest = "1.1.1"
        const val coil = "2.0.0-rc03"
        const val activityCompose = "1.4.0"
        const val viewModelCompose = "2.4.1"
    }
}