import SwiftUI
import Kingfisher
import localdb_dto
import localdb_api
import shared_logic
import rick_morty


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

class ObservableState: ObservableObject {
    @Published var viewModel: CharacterListViewModel = CharacterListViewModel(api: RickAndMortyApiModule().value!)
    @Published var data: [shared_logic.Rick_mortyResult]?

    init() {
        self.viewModel.characterList.collect(collector: Collector<[shared_logic.Rick_mortyResult]> { yourValue in
            self.data = yourValue
        }) { (error) in
            // code which is executed if the Flow object completed
        }
    }
}


struct ContentView: View {
    
    @StateObject private var observableState = ObservableState()
    
    var body: some View {
        let list: [shared_logic.Rick_mortyResult]? = observableState.data
        if (list == nil || list?.isEmpty == true){
            
        } else {
            List(list!,id: \.self) {character in
            HStack {
                KFImage(URL(string: character.image)!).resizable().aspectRatio(contentMode: .fit).frame(width: 48,height: 48)
                Text(character.name)
            }
        }
        }
        
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
