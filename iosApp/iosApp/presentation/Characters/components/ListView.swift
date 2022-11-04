import SwiftUI
import shared_logic

struct ListView: View {
    var list: [shared_logic.Rick_mortyResult]
    var onLast: ()->Void
 
    init(list: [shared_logic.Rick_mortyResult], onLast: @escaping () -> Void) {
        self.list = list
        self.onLast = onLast
    }
    
    var body: some View {
        List(list,id: \.self) {character in
            CharacterView(character: character).onAppear{
                if (character==list.last){
                    onLast()
                }
            }
        }
    }
    
}
