

# 👨‍💻 Devposts
Devposts is a sample application that displays a brief selection of the hottest articles from [Dev.to](https://dev.to),  showcasing featured content for developers.

# 🌠 Sceenshots

| Articles Screen Light | Articles Screen Dark | Article Details Screen Light | Article Details Screen Dark |
|-------|-------|-------|-------|
| ![Articles Screen Light](https://github.com/sham-h93/devposts/blob/develop/media/articles-screen-light.png)   | ![Articles Screen Dark](https://github.com/sham-h93/devposts/blob/develop/media/articles-screen-dark.png)   | ![Article Details Screen Light](https://github.com/sham-h93/devposts/blob/develop/media/articledetails-screen-light.png)   | ![Article Details Screen Dark](https://github.com/sham-h93/devposts/blob/develop/media/articledetails-screen-dark.png)   |

### 🎯 Why I Built This?

In this project, I aimed to design and implement a scalable, maintainable, reusable, and testable architecture based on Clean Architecture and modular principles, in a way that is simple and easy for others to understand.

## 🧱 Project Architecture
![devposts_banner](https://github.com/sham-h93/devposts/blob/develop/media/banner.png)


### What is Clean Architecture?
Before introducing that, let me say that [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) is not a template, trend, or some boilerplate. In fact, this architecture comes from following some principles like [SOLID Principles](https://www.digitalocean.com/community/conceptual-articles/s-o-l-i-d-the-first-five-principles-of-object-oriented-design). These principles are conceptual guidelines that prioritize the separation of concerns, making your software independent of frameworks, UI, and databases.

### Modularzation and Component principles
If we want to implement a well-modularized project, we have to follow something like SOLID principles (Which are applied at the class and interface level), but at the component (Module) level. These principles are [Component Principles](https://dev.to/rubemfsv/component-principles-the-concept-behind-the-code-dn6). These principles govern the dependencies between modules and ensure that the overall architecture remains flexible.

By combining **Clean Architecture** with a **modular design**, we can establishes a strong foundation for building large-scale Android applications that are easy to extend, maintain, and test.

Modules depend only on more stable modules, minimizing the ripple effect of changes. Each module is **highly cohesive** and **loosely coupled**, providing clear separation of concerns and easier testing.

### Presentation Layer Pattern
The project employs the [Model-View-Intent **(MVI)**](https://java-design-patterns.com/patterns/model-view-intent/#model-view-intent-pattern-java-tutorials) pattern in its presentation modules, a unidirectional data flow pattern that improves state management and UI predictability.

Additionally, the project leverages [Convention Plugins](https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html)  for streamlined Gradle configuration, keeping build logic clean and reusable across modules.


#### Modules:
- `:app`  Application entry point
- `:data` Contains`:datasources` and `:repository` - `:domain` — Business logic and use cases
- `:feature`  UI logic for individual features, e.g., `:articles` and `:articledetails`
-  `:libraries` — Reusable libraries like `:core`, `:navigation`, `:common`, and `:designsystem`
## ✨ Features
- Clean and Modular Architecture
- Convention Plugins for reusable build logic
- Offline-first support with **Room** and **Paging 3**
-  Declarative UI with **Jetpack Compose**
- Remote API access using **Ktor Client**
-  Dependency Injection with **Hilt**
-  MVI-based presentation layer
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

- [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle) for Kotlin code style checks
- [Convention Plugins](https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html) — Gradle plugins that centralize and reuse build configuration across multiple modules.

---  
>Please note that this project is a work in progress and may include technical debt. Contributions are welcome to help refine and enhance it, with the goal of delivering a robust and scalable architecture.

# API
This project integrates with the **[Dev.to API](https://developers.forem.com/api)** (also known as the **Forem API**), enabling seamless access to published articles and content directly from the Dev.to platform.