import Foundation
import shared_logic
class Collector<T>: shared_logic.Kotlinx_coroutines_coreFlowCollector {
    
    func emit(value: Any?, completionHandler: @escaping (Error?) -> Void) {
        callback(value as! T)
        completionHandler(nil)
    }
    

    let callback:(T) -> Void

    init(callback: @escaping (T) -> Void) {
        self.callback = callback
    }

}
