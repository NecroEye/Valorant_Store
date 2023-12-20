# <p align="center"> Valorant Store </p>

<br>

<!-- Technologies -->
## Structures Used
- MVVM
- Navigation Component
- Dependency Injection | Hilt
- Coroutine
- Retrofit
- Room
- View Binding
- Parcelable
- Glide
- Timber 
- Truth & Mockito for testing

For animation : Lottie used
<br>

## Dependency
```
      //UI
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.airbnb.android:lottie:$lottieVersion")
    implementation("com.ms-square:expandableTextView:0.1.4")


    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")


    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")


    //LifeCycle & LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")


    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")


    //Timber - Logger
    implementation("com.jakewharton.timber:timber:5.0.1")

    //Room
    implementation ("androidx.room:room-runtime:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")


    //Testing
    implementation("com.google.truth:truth:1.1.4")
    testImplementation("org.mockito:mockito-core:4.7.0")
```

app build.gradle:

```
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-parcelize'
    id 'androidx.navigation.safeargs.kotlin'
    id 'kotlin-kapt'
    id 'com.google.dagger.hilt.android'
}

buildFeatures {
      viewBinding true
      dataBinding true
 }
```
project build.gradle:

```
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("com.android.library") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.0" apply false
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
```

<!-- Manifest File -->
## Manifest File
```
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

