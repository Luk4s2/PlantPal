# 🌿 PlantPal

PlantPal is a Kotlin Multiplatform Android app that lets you browse and explore plant species using
data from the [Perenual Plant API](https://perenual.com/docs/api). It showcases modern Android
development practices including Jetpack Compose, MVVM architecture, Retrofit networking, and more.

---

## 🚀 Features

- Browse a list of plant species with images
- Tap to view detailed info on each plant
- Using LRU caches instead of local database
- Live data from Perenual Plant API
- Jetpack Compose UI + Hilt inject
- ViewModel + Repository pattern
- Navigation between screens
- Unit tests and static analysis with Detekt
- CI/CD pipeline for automatic build, test, and lint checks

---

## 🛠 Tech Stack

- **Kotlin Multiplatform (Android)**
- **Jetpack Compose**
- **Hilt**
- **ViewModel / LiveData / StateFlow**
- **Retrofit + Moshi**
- **Perenual API**
- **Coil (Image loading)**
- **JUnit (Testing)**
- **Detekt (Static code analysis)**
- **GitHub Actions (CI/CD)**

---

## 🔄 CI/CD

PlantPal uses **GitHub Actions** for continuous integration and deployment. Every pull request and
push to `main` triggers workflows that:

- Run unit tests (`./gradlew test`)
- Run Detekt for code quality checks (`./gradlew detekt`)
- Build the Android app to verify build integrity

This ensures code quality and app stability with every change.

Workflow files are located in `.github/workflows/`.

---

## 📦 Setup

1. Clone the repo:

```bash
git clone https://github.com/yourusername/plantpal.git
cd plantpal
```

2. Open in Android Studio (Giraffe or newer)

3. Sync Gradle & Build the project

4. Run the app on your Android emulator or device

---

## 📄 License

MIT License
