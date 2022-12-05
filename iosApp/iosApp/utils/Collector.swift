import Foundation
import MultiPlatformLibrary



class Collector<T>: FlowColletor {
    
    func emit(value: Any?, completionHandler: @escaping (Error?) -> Void) {
        DispatchQueue.main.async {
            self.callback(value as! T)
        }
        completionHandler(nil)
    }
    

    let callback:(T) -> Void

    init(callback: @escaping (T) -> Void) {
        self.callback = callback
    }

}
