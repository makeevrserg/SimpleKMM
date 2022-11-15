# SimpleKMM
<p align="center">
    SimpleKMM is the Kotlin Multiplatform application of IOS,JS and JVM example with shared logic
</p>

### **Platforms**
- [x] IOS - shared logic; native UI
- [x] JS - shared logic; compose for web as UI
- [x] JVM/Android - shared logic and UI

## Directory structure
    
    ├── androidAPp          # Mobile app
    ├── buildSrc            # Build components
    ├── desktop             # Desktop app
    ├── iosApp              # IOS native swiftUI app
    ├── js                  # js Compose For Web app
    ├── KiosAPp             # Kotlin Compose IOS app
    ├── modules
    │   ├── localdb-api     # API for local database
    │   ├── localdb-dto     # DTO of local database
    │   ├── rick-morty      # Rick And Morty public api
    │   ├── shared-logic    # Shared logic - viewModels, paging
    │   └── shared-ui       # Shared UI for desktop and android
