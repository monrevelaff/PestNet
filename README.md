# PestNet

**PestNet Mobile** is an offline Android application for identifying common crop pests and supporting **Integrated Pest Management (IPM)** decisions. It uses a bundled TensorFlow Lite model to perform on-device pest detection from images captured using the camera or selected from the gallery.

The detected pest is matched with a local pest record containing information such as appearance, signs of infestation, action thresholds, and biological, cultural, and chemical control methods.

All accounts, scans, and images are stored locally on the device. The application does not require a backend or internet connection for its core functionality.

## Screenshots

Screenshots of the application will be added here.

<!--
Example:

| Home | Pest Detection | Pest Library |
| --- | --- | --- |
| ![Home](screenshots/home.png) | ![Detection](screenshots/detection.png) | ![Library](screenshots/library.png) |

| Scan History | Pest Details | Settings |
| --- | --- | --- |
| ![History](screenshots/history.png) | ![Pest Details](screenshots/pest-details.png) | ![Settings](screenshots/settings.png) |
-->

## Features

* **On-device pest detection.** The TensorFlow Lite model and class labels are bundled with the application (`app/src/main/assets/model.tflite` and `labels.txt`). Inference runs locally without requiring an internet connection.
* **Camera and gallery support.** Capture an image using the device camera with CameraX or select an existing image from the gallery.
* **Detection results.** Detected pests are displayed with their predicted class, confidence score, and bounding boxes.
* **Scan results.** Saved scans contain the detected label, confidence, pest count, timestamp, image path, and a link to the corresponding pest library entry.
* **Pest library.** Browse preloaded pest information, including descriptions, physical characteristics, signs of infestation, action thresholds, and control methods.
* **Scan history.** Review previous scans and mark important scans as favourites.
* **Local accounts.** Users can register and log in directly on the device. Passwords are stored using BCrypt hashes and email addresses are unique.
* **Appearance and accessibility.** Includes light and dark themes, an in-app guide, and a legal information screen.

## How It Works

1. The user captures an image using the camera or selects an image from the gallery.
2. The image is processed locally on the device.
3. The bundled TensorFlow Lite model performs pest detection and classification.
4. The detected pest is matched with the corresponding entry in the local pest database.
5. The application displays the detection result and relevant IPM information.
6. The user can save the scan and review it later through the scan history.

The complete detection workflow operates locally, allowing the application to function without an internet connection.

## Pest Classes

The bundled model detects and classifies the following pest classes:

* Aphids
* Beetle
* Caterpillar
* Leafhopper
* Mealybugs
* Slug
* Sowbug
* Spidermites
* Thrips
* Weevil

Pest library information was adapted from the [University of California Statewide Integrated Pest Management Program](https://ipm.ucanr.edu/).

## Datasets

The pest detection model was trained using datasets sourced from [Roboflow Universe](https://universe.roboflow.com/).

-(https://universe.roboflow.com/pest-classifier/pest-aphid)
-(https://universe.roboflow.com/jade-9fvdy/aphids-fgtid)
-(https://universe.roboflow.com/uet-taxila-x6wdk/pest-detection-green-leafhopper)
-(https://universe.roboflow.com/hydroponics-cuihh/pest-types)
-(https://universe.roboflow.com/fixed0301/thrips-detection-for-smart-trap)
-(https://universe.roboflow.com/thesis-8yqnt/thrips-gz4bl)

## Requirements

* Android Studio with Android SDK 35
* JDK 17 (required by Android Gradle Plugin 8.7)
* Android device or emulator running Android 7.0 (API 24) or newer
* Camera permission for live image capture
* Photo access for selecting images from the gallery

## Build and Run

Open the `PestNetMobile` directory in Android Studio and run the `app` configuration.

Alternatively, build the debug APK from the command line:

```bash
cd PestNetMobile
./gradlew assembleDebug
```

The generated debug APK can be found at:

```text
PestNetMobile/app/build/outputs/apk/debug/
```

**Application ID:**

```text
com.example.pestmanagementapp
```

**Launcher name:** `PestNet Mobile`

## Project Layout

```text
PestNetMobile/
  app/src/main/
    assets/          TensorFlow Lite model and labels
    java/.../
      data/          Room database, repositories, detection, preloaded pests
      di/            Hilt modules
      ui/            Compose screens
                     (home, camera, library, history, results)
      viewmodels/    ViewModels
    res/             Themes, strings, and pest reference images
```

The Gradle project name is `PestManagementApp` and the Android module is `:app`.

## Technology Stack

| Component             | Technology                  |
| --------------------- | --------------------------- |
| UI                    | Jetpack Compose, Material 3 |
| Navigation            | Navigation Compose          |
| Dependency Injection  | Hilt                        |
| Local Database        | Room                        |
| Camera                | CameraX                     |
| Pest Detection        | TensorFlow Lite 2.9         |
| Image Loading         | Coil                        |
| Password Hashing      | jBCrypt                     |
| Secure Preferences    | AndroidX Security Crypto    |
| Build System          | Gradle 8.9                  |
| Programming Language  | Kotlin 1.9                  |
| Android Gradle Plugin | 8.7.2                       |
| Minimum SDK           | 24                          |
| Compile / Target SDK  | 35                          |

## Project Context

PestNet Mobile was developed as part of my university dissertation project.

The project explores the use of **on-device machine learning and mobile application development** to support pest identification and Integrated Pest Management. A key focus of the project was developing an application that could operate without an internet connection while remaining accessible to users with varying levels of technical experience.

The detection model was trained using pest image datasets sourced from Roboflow Universe and subsequently integrated into the application as a TensorFlow Lite model.

The project covers the design, development, machine learning model integration, local data management, user interface implementation, and testing of the mobile application.

## Future Improvements

Potential future improvements include:

* Expanding the number of supported pest species and crops.
* Improving detection accuracy using larger and more diverse datasets.
* Supporting additional languages.
* Providing more detailed crop-specific recommendations.
* Further optimising the detection model for lower-end Android devices.
* Conducting additional usability testing with a wider range of users.

## Disclaimer

Pest detections and control information are provided as a starting point and should not be considered a definitive diagnosis or professional agricultural advice.

Users should confirm pest identification with an appropriate local extension service or qualified agronomist before treating a crop and should always follow the instructions and safety requirements on pesticide labels.
