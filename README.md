
# devposts 
devposts is a sample application that displays a brief selection of the hottest articles from [Dev.to](https://dev.to),  showcasing featured content for developers. 

> This project is evolving!


## 🧱 Project Structure
![devposts_banner](https://github.com/sham-h93/devposts/blob/develop/media/banner.png)


This project demonstrates a **scalable**, **maintainable**, and **testable** architecture.  
By combining **Clean Architecture** with a **modular design**, it establishes a strong foundation for building large-scale Android applications that are easy to extend, maintain, and test.  

Modules depend only on more stable modules, minimizing the ripple effect of changes. Each module is **highly cohesive** and **loosely coupled**, providing clear separation of concerns and easier testing.  

The project employs the Model-View-Intent **(MVI)** pattern in its presentation modules, a unidirectional data flow pattern that improves state management and UI predictability.  

Additionally, it leverages **Convention Plugins** for streamlined Gradle configuration, keeping build logic clean and reusable across modules.


#### Modules:
- `:app` — Application entry point  
- `:data` —  Contains`:datasources` and `:repository`  
- `:domain` — Business logic and use cases  
- `:feature` — UI logic for individual features, e.g., `:articles` and `:articledetails`  
- `:libraries` — Reusable libraries like `:core`, `:navigation`, `:common`, and `:designsystem`  

## ✨ Features
- Clean Architecture  
- Convention Plugins for reusable build logic  
- Offline-first support with **Room** and **Paging 3**  
- Declarative UI with **Jetpack Compose**  
- Remote API access using **Ktor Client**  
- Dependency Injection with **Hilt**  
- MVI-based presentation layer  
- Modular codebase for scalability  
- Easy maintenance and testing  


## 🛠️ Development

- [Kotlin](https://kotlinlang.org/) — A modern, concise, and safe programming language.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) — Android’s modern UI toolkit for building declarative and reactive UIs.  
- [Room](https://developer.android.com/training/data-storage/room) — An ORM for SQLite, providing a clean API for local data persistence.  
- [Ktor](https://ktor.io/) — An asynchronous framework for building connected applications, used here as the HTTP client.  
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) — A dependency injection library built on top of Dagger for Android.  
- [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) — A library for loading and displaying large datasets efficiently.  
- [Coil ](https://coil-kt.github.io/coil/compose/) — An image loading library for Android backed by Kotlin Coroutines, optimized for Jetpack Compose.  

- [MVI (Model–View–Intent)]() — A unidirectional data flow pattern that improves state management and UI predictability.  
-  [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle) for Kotlin code style checks
- [Convention Plugins](https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html) — Gradle plugins that centralize and reuse build configuration across multiple modules.  



# API
This project integrates with the **[Dev.to API](https://developers.forem.com/api)** (also known as the **Forem API**), enabling seamless access to published articles and content directly from the Dev.to platform.
