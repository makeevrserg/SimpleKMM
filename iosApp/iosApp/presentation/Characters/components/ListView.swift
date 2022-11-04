import SwiftUI
import shared_logic

struct ListView: View {
    var list: [shared_logic.Rick_mortyResult]
    init(list: [shared_logic.Rick_mortyResult]) {
        self.list = list
    }
    
    var body: some View {
        List(list,id: \.self) {character in
            CharacterView(character: character)
        }
    }
    
}
