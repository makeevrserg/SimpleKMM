import SwiftUI
import Kingfisher
import localdb_dto
import localdb_api
import shared_logic
import rick_morty

struct CharactersView: View {
    
    @StateObject private var observableState = CharactersState()
    
    var body: some View {
        let list: [shared_logic.Rick_mortyResult]? = observableState.data
        if (list == nil || list?.isEmpty == true){
            
        } else {
            ListView(list: list!){
                observableState.onEndReached()
            }
        }
        
    }
    
}

