import SwiftUI
import MultiPlatformLibrary

struct ListView: View {
    var list: [MultiPlatformLibrary.Rick_mortyResult]
    var onLast: ()->Void
 
    init(list: [MultiPlatformLibrary.Rick_mortyResult], onLast: @escaping () -> Void) {
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
