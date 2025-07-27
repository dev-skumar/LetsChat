This is a Kotlin Multiplatform Project targeting Android, Web & Desktop.

To run the application on:
  - **Android** : use default `composeApp` gradle run configuration.
  - **Web** : run this command in terminal ==> `./gradlew wasmJsBrowserDevelopmentRun`
  - **Desktop** : run this command in terminal ==> `./gradlew desktopRun`
  <br> <br>
    If Desktop app doesn't run, use this alternative way:
    - Step 1 : open `main.kt` file from `desktopMain` folder of composeApp.
    - Step 2 : run main function using `run` gutter icon. This will still not work but new **MainKt** run configuration will be created and selected.
    - Step 3 : now run this command in terminal ==> `./gradlew run`

<br>

---

<br>