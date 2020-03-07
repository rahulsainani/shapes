<h1 align="center">Shapes</h1></br>
<p align="center">
A simple android app that draws shapes on a canvas showcasing MVVM + Clean Architecture using Kotlin, Coroutines, Flow, Dagger, Room, LiveData, View Model etc. The app is modularised by feature.
</p>
</br>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://circleci.com/gh/rahulsainani/shapes"><img src="https://circleci.com/gh/rahulsainani/shapes.svg?style=svg"></a>
</p>

## What it does

*Editor screen*

1. There are 3 buttons (square, circle, and triangle). Each time you tap on a button: an object of that shape will be created, and displayed in a random position on screen.
2. Tapping on a shape will cause it to become another shape: tapping a square will make it a circle, tapping a circle will make it a triangle, tapping a triangle will make it a square.
3. Long pressing on a shape will remove the shape. 
4. You can undo any action. Each time you press undo it will cancel the last action performed.

*Stats screen*

1. Shows a list with the name and count of each shape.
2. Tapping on a list item will delete all shapes of that kind.

## Screenshots

<p align="center">
<img src="/screenshots/add-shapes.gif" width="30%"/>
<img src="/screenshots/switch-shapes.gif" width="30%"/>
<img src="/screenshots/undo.gif" width="30%"/>
</p>


## Tech Stack + Libraries Used

- [Kotlin](https://github.com/JetBrains/kotlin) + [Coroutines + Flow](https://github.com/Kotlin/kotlinx.coroutines)
- [Android ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Livedata](https://developer.android.com/topic/libraries/architecture/livedata)
- [Room](https://developer.android.com/topic/libraries/architecture/room)
  for Disk Persistance
- [Dagger](https://github.com/google/dagger) for Dependency Injection
- [Lottie](https://github.com/airbnb/lottie-android) for easy animations
- [Timber](https://github.com/JakeWharton/timber) for logging
- [JUnit 5](https://junit.org/junit5/docs/current/user-guide/),
  [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin) and
  [AssertJ](https://assertj.github.io/doc/#assertj-overview) for testing


## License
```
Copyright 2020 Rahul Sainani

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```