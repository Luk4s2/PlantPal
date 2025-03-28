# 🌿 PlantPal

PlantPal is a Kotlin Multiplatform Android app that lets you browse and explore plant species using
data from the [Perenual Plant API](https://perenual.com/docs/api). It showcases modern Android
development practices including Jetpack Compose, MVVM architecture, Retrofit networking, and more.

---

## 🚀 Features

- Browse a list of plant species with images
- Tap to view detailed info on each plant
- Live data from Perenual Plant API
- Jetpack Compose UI
- ViewModel + Repository pattern
- Navigation between screens
- Unit test and static analysis with Detekt

---

## 🛠 Tech Stack

- **Kotlin Multiplatform (Android)**
- **Jetpack Compose**
- **ViewModel / LiveData / StateFlow**
- **Retrofit + Moshi**
- **Perenual API**
- **Coil (Image loading)**
- **JUnit (Testing)**
- **Detekt (Static code analysis)**

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

## 🔑 API Key

This project uses a free API key from Perenual. A sample key is pre-integrated:

```
API_KEY = sk-J1k467dfc6e0b511f9355
```

---

## 🧪 Testing

To run unit tests:

```bash
./gradlew test
```

---

## 🧹 Static Code Analysis

To run Detekt checks:

```bash
./gradlew detekt
```

---

## 📄 License

MIT License
