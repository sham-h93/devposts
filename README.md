
# 🔥 Hotline News
Hotline News is a sample news application focused on **scalability**, **maintainability**, and **testability**.  
This project is built using **Clean Architecture** and follows a **modular** structure, making it a great foundation for large-scale applications.

Each module depends only on more stable modules, which reduces the ripple effect of changes across the codebase.  
Modules are **highly cohesive** and **loosely coupled**, resulting in better separation of concerns and easier testing.

The project also uses **Convention Plugins** to simplify Gradle configuration and keep the build logic clean and reusable across modules.

The project is still evolving and has a lot of room for improvement. Contributions are welcome!

---

## 🧱 Project Structure

🔽 Project Structure Diagram:
![hotlinenews](https://github.com/sham-h93/hotlinenews/blob/develop/media/hotline_banner.png)


The project is divided into well-separated modules:

- `:app`: Entry point of the application
- `:data`: Contains data sources and repositories
- `:domain`: Contains business logic and use cases
- `:feature`: Contains UI logic for each feature, such as `:articles` and `:articledetails`
- `:libraries`: Reusable libraries like `:core`, `:navigation`, `:common`, and `:designsystem`


## ✨ Features

- Clean Architecture
- Convention Plugins for clean build logic
- Offline-first support using Room and Paging 3
- Declarative UI with Jetpack Compose
- Remote data via Ktor client
- Dependency Injection using Hilt
- MVI-based presentation layer
- Modular codebase for scalability
- Easy to maintain and test

---

## 🛠️ Tech Stack

- Kotlin
- Jetpack Compose
- Room
- Ktor
- Hilt
- Paging 3
- Kotlinx Serialization
- MVI
- Modularization
- Convention Plugins

---


### Prerequisites

To run the app, you need an [API key from NewsAPI.org](https://newsapi.org/).  


