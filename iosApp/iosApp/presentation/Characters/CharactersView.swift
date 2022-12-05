import SwiftUI
import Kingfisher
import localdb_dto
import localdb_api
import rick_morty
import MultiPlatformLibrary
import mokoMvvmFlowSwiftUI
struct CharactersView: View {
    
    @StateObject private var observableState = CharactersState()
    @ObservedObject var viewModel: CharacterListViewModel = CharacterListViewModel(api: RickAndMortyApiModule.shared.value!)
    var body: some View {
        
        let list: [MultiPlatformLibrary.Rick_mortyResult]? = viewModel.state(\.characterList)
        if (list == nil || list?.isEmpty == true){
            Text("Loading")
        } else {
            ListView(list: list!){
                viewModel.loadNextPage()
            }
        }
        
    }
    
}
