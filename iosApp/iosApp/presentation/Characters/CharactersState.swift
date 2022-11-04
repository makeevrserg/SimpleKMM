import SwiftUI
import Kingfisher
import localdb_dto
import localdb_api
import shared_logic
import rick_morty

class CharactersState: ObservableObject {
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
