import SwiftUI
import Kingfisher
import shared_logic

struct CharacterView: View{
    var character: shared_logic.Rick_mortyResult
    init(character: shared_logic.Rick_mortyResult) {
        self.character = character
    }
    var body: some View{
        HStack {
            KFImage(URL(string: character.image)!).resizable().aspectRatio(contentMode: .fit).frame(width: 48,height: 48)
            VStack(alignment: .leading){
                Text("Name: \(character.name)")
                Text("Species: \(character.species)")
                Text("Status: \(character.status)")
            }
        }
    }
}
