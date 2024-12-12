import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.jetbrains.kotlin.incremental.deleteDirectoryContents
import java.net.InetAddress
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.firebaseAppdistribution)
    alias(libs.plugins.firebaseCrashlytics)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

val keystoreProperties = Properties().apply {
    load(rootProject.file("keystore/keystore.properties").inputStream())
}
val lApplicationId = libs.versions.applicationId
    .get()
val lApplicationVersion = libs.versions.applicationVersion
    .get()
val lApplicationVersionCode = libs.versions.applicationVersion
    .get()
    .replace(".", "")
    .toInt()

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm("desktop")

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        androidMain.dependencies {
            implementation(project.dependencies.platform(libs.firebase.bom))

            implementation(compose.preview)

            implementation(libs.androidx.activity.compose)
            implementation(libs.coil3.okhttp)
            implementation(libs.firebase.analytics)
            implementation(libs.firebase.auth)
            implementation(libs.firebase.crashlytics)
            implementation(libs.firebase.ui)
            implementation(libs.google.id)
            implementation(libs.koin.android)
            implementation(libs.ktor.okhttp)
        }

        commonMain.dependencies {
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.runtime)
            implementation(compose.ui)

            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.navigation.compose)
            implementation(libs.coil3.compose)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.ktor.core)
            implementation(libs.ktor.logging)
            implementation(libs.ktor.negotiation)
            implementation(libs.ktor.serialization.json)
        }

        val desktopMain by getting
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)

            implementation(libs.coil3.jvm)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.apache5)
        }

        val desktopTest by getting
        desktopMain.dependencies {
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTestJUnit4)

            implementation(libs.test.junit)
        }

    }
}

android {
    namespace = lApplicationId
    compileSdk = libs.versions.android.compileSdk
        .get()
        .toInt()

    sourceSets["debug"].res.srcDirs(
        "src/androidDebug/res",
        "src/commonMain/composeResources"
    )

    defaultConfig {
        applicationId = lApplicationId
        minSdk = libs.versions.android.minSdk
            .get()
            .toInt()
        targetSdk = libs.versions.android.targetSdk
            .get()
            .toInt()
        versionCode = lApplicationVersionCode
        versionName = lApplicationVersion
    }
    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties["appKeystoreUploadAlias"]
                .toString()
            keyPassword = keystoreProperties["appKeystoreUploadPassword"]
                .toString()
            storeFile = rootProject.file(
                keystoreProperties["appKeystoreUploadFile"]
                    .toString()
            )
            storePassword = keystoreProperties["appKeystoreUploadPassword"]
                .toString()
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "br.com.asoncsts.multi.gymtrack.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = lApplicationId
            packageVersion = lApplicationVersion
        }
    }
}

val buildConfigGenerator by tasks.registering(Sync::class) {
    val ip = InetAddress.getLocalHost().hostAddress

    from(
        resources.text.fromString(
            """
            |package $lApplicationId.generated
            |
            |object BuildConfig {
            |   //const val APPLICATION_ID = "$lApplicationId"
            |   //const val APPLICATION_VERSION = "$lApplicationVersion"
            |   //const val APPLICATION_VERSION_CODE = "$lApplicationVersionCode"
            |
            |   const val DEBUG = true
            |
            |   //const val FIREBASE_APP_ID = "${keystoreProperties["firebaseAppId"]}"
            |   //const val FIREBASE_API_KEY = "${keystoreProperties["firebaseApiKey"]}"
            |   //const val FIREBASE_AUTH_DOMAIN = "${keystoreProperties["firebaseAuthDomain"]}"
            |   //const val FIREBASE_AUTH_API_HOST_IDENTIFY = "https://identitytoolkit.googleapis.com/v1"
            |   //const val FIREBASE_AUTH_API_HOST_TOKEN = "https://securetoken.googleapis.com/v1"
            |   const val FIREBASE_DEFAULT_WEB_CLIENT_ID = "${keystoreProperties["firebaseDefaultWebClientId"]}"
            |   //const val FIREBASE_MEASUREMENT_ID = "${keystoreProperties["firebaseMeasurementId"]}"
            |   //const val FIREBASE_MESSAGING_SENDER_ID = "${keystoreProperties["firebaseMessagingSenderId"]}"
            |   //const val FIREBASE_PROJECT_ID = "${keystoreProperties["firebaseProjectId"]}"
            |   //const val FIREBASE_STORAGE_BUCKET = "${keystoreProperties["firebaseStorageBucket"]}"
            |   //const val FIREBASE_WEB_API_KEY = "${keystoreProperties["firebaseWebApiKey"]}"
            |   
            |   const val HOST = "http://$ip:3000/api"
            |
            |}
            |
            """.trimMargin()
        )
    ) {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        rename { "BuildConfig.kt" } // set the file name FIREBASE_AUTH_API_TOKEN_HOST
        into("") // change the directory to match the package
    }

    // the target directory
    into(
        layout.projectDirectory.dir(
            "src/commonMain/kotlin/${
                lApplicationId.replace(
                    ".",
                    "/"
                )
            }/generated/"
        )
    )
}

val moveBuiltFiles by tasks.registering(Sync::class) {
    val destination = layout.projectDirectory.dir(
        "../dist/"
    )
    try {
        destination.asFile.deleteDirectoryContents()
    } catch (_: Throwable) {
        // Do nothing
    }
    from(
        layout.projectDirectory.dir(
            "build/dist/wasmJs/productionExecutable/"
        )
    )
    into(
        destination
    )
}
