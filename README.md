# Declarative Multiplatformist

This repo is a playground for my Personal DO at [Doist](https://doist.com/). 

Personal DOs are 1 month up-to-our-choice-projects that Doist allows us to explore yearly.
For my 2021 Personal DO I chose to explore declarative UIs on [android](https://developer.android.com/jetpack/compose) and [iOS](https://developer.apple.com/xcode/swiftui/) as well Kotlin Multiplatform to share logic between the 2 apps. 

So expect to see a lot of experimentation here! 😊

## Structure
This is a single repo in which each of the 3 main folders contains one "component" of the project - the android and iOS apps as well as the KMP project.

### android
It contains the Android component of this experiment. You can get it up and running like any regular Android app.

_Note:_ ensure you publish the _common_ module before trying to run the app, otherwise you'll get an error because that dependency can't be found.

### ios
It contains the iOS component of this experiment.

Having come from Android native development and having never done any iOS development, here are some resources that helped me getting up to speed:
- https://developer.apple.com/documentation/swiftui/managing-model-data-in-your-app
- https://developer.apple.com/tutorials/app-dev-training/#swiftui-essentials

Ideas to improve:
- Added tasks' persistence
- Localization
- Making the UI more friendly

### common
Contains the KMP project. To open and edit it, you can use [IntelliJ IDEA](https://www.jetbrains.com/idea/)

Right now, the way this module is setup to be distributed is through a local maven repository [ref](https://www.jetbrains.com/idea/).

To do it locally, please do the following:
- Check out this repository
- Switch to the `common` folder
- Through your terminal, run `./gradlew publishToMavenLocal`

At this point, the library should be available on your device's local maven repository.
To consume it on the Android app, you need to add this dependency:
```kotlin
implementation("com.pedrosantos:declarative-multiplatformist-common:$version")
```
