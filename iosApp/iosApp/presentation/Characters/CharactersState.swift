import SwiftUI
import Kingfisher
import localdb_dto
import localdb_api
import rick_morty
import MultiPlatformLibrary

class CharactersState: ObservableObject {
    @Published var viewModel: CharacterListViewModel = CharacterListViewModel(api: RickAndMortyApiModule.shared.value!)
    @Published var data: [MultiPlatformLibrary.Rick_mortyResult]?
    func onEndReached(){
        viewModel.loadNextPage()
    }
    init() {
        self.viewModel.characterList.collect(collector: Collector<[MultiPlatformLibrary.Rick_mortyResult]> { yourValue in
            self.data = yourValue
        }) { (error) in
        }
    }
}
