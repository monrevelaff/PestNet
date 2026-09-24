# PestNet

PestNet Mobile is an offline Android app for identifying common crop pests and looking up integrated pest management (IPM) guidance. A photo from the camera or gallery is scored on the device with a bundled TensorFlow Lite model. The matching pest record then shows appearance, infestation signs, action thresholds, and biological, cultural, and chemical controls.

Accounts, scans, and images stay on the phone. The app does not call a backend.

## Features

- **On-device detection.** The model and class labels ship in the app (`app/src/main/assets/model.tflite` and `labels.txt`). Inference runs locally with TensorFlow Lite.
- **Camera and gallery.** Capture a photo with CameraX, or pick an existing image. The annotated result is saved with bounding boxes.
- **Scan results.** Each saved scan stores the label, confidence, pest count, timestamp, and image path, and links to the matching library entry.
- **Pest library.** Browse preloaded pests. Each entry has overview, physical description, signs of infestation, action thresholds, and control methods.
- **Scan history.** Review past scans and mark favourites.
- **Local accounts.** Sign up and log in on the device. Passwords are stored as BCrypt hashes. Email addresses are unique.
- **Appearance.** Light and dark themes, an in-app guide, and a legal screen.

## Pest classes

The bundled model classifies these labels:

- Aphids
- Beetle
- Caterpillar
- Leafhopper
- Mealybugs
- Slug
- Sowbug
- Spidermites
- Thrips
- Weevil

Library text was adapted from the [University of California Statewide Integrated Pest Management Program](https://ipm.ucanr.edu/).

## Requirements

- Android Studio with Android SDK 35
- JDK 17 (required by Android Gradle Plugin 8.7)
- A device or emulator running Android 7.0 (API 24) or newer
- Camera permission for live capture, and photo access for the gallery picker

## Build and run

Open the `PestNetMobile` directory in Android Studio and run the `app` configuration.

From the command line:

```bash
cd PestNetMobile
./gradlew assembleDebug
```

The debug APK is written to `PestNetMobile/app/build/outputs/apk/debug/`. The application id is `com.example.pestmanagementapp`. The launcher name is PestNet Mobile.

## Project layout

```
PestNetMobile/
  app/src/main/
    assets/          TensorFlow Lite model and labels
    java/.../
      data/          Room database, repositories, detection, preloaded pests
      di/            Hilt modules
      ui/            Compose screens (home, camera, library, history, results)
      viewmodels/
    res/             Theme, strings, pest reference images
```

The Gradle project name is `PestManagementApp`. The Android module is `:app`.

## Stack

| Piece | Choice |
| --- | --- |
| UI | Jetpack Compose, Material 3 |
| Navigation | Navigation Compose |
| Dependency injection | Hilt |
| Persistence | Room (users, pest info, scan results) |
| Camera | CameraX |
| Detection | TensorFlow Lite 2.9 |
| Images | Coil |
| Passwords | jBCrypt |
| Preferences | AndroidX Security Crypto |
| Build | Gradle 8.9, Kotlin 1.9, Android Gradle Plugin 8.7.2 |
| SDK | `minSdk` 24, `compileSdk` / `targetSdk` 35 |

## Disclaimer

Detections and control notes are a starting point, not a diagnosis. Confirm an identification with a local extension service or agronomist before treating a crop, and follow the pesticide label.
