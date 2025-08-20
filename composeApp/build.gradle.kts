@file:OptIn(ExperimentalComposeLibrary::class)

import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.net.InetAddress
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.androidxRoom)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.firebaseAppdistribution)
    alias(libs.plugins.firebaseCrashlytics)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
}

val keystoreProperties = Properties().apply {
    runCatching {
        load(
            rootProject.file("keystore/keystore.properties")
                .inputStream()
        )
    }
}
val lApplicationId = libs.versions.applicationId
    .get()
val lApplicationVersion = libs.versions.applicationVersion
    .get()
val lApplicationVersionCode = libs.versions.applicationVersion
    .get()
    .replace(".", "")
    .toInt()

room {
    schemaDirectory("$projectDir/schemas")
}

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
            // Required when using NativeSQLiteDriver
            linkerOpts.add("-lsqlite3")
        }
    }

    jvm("desktop")

    sourceSets {
        androidMain.dependencies {
            implementation(project.dependencies.platform(libs.firebase.bom))

            implementation(compose.preview)

            implementation(libs.androidx.activity.compose)
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
            //implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.runtime)
            implementation(compose.ui)

            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
            implementation(libs.androidx.navigation.compose)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.room.runtime)
            implementation(libs.sqlite)

            implementation(libs.bundles.coil3)
            implementation(libs.bundles.ktor)
        }

        val desktopMain by getting
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)

            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.apache5)
        }

        val desktopTest by getting
        desktopTest.dependencies {
            implementation(compose.uiTest)
            implementation(compose.desktop.uiTestJUnit4)

            implementation(libs.test.junit)
        }

        nativeMain.dependencies {
            implementation(libs.ktor.darwin)
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
        //"src/commonMain/composeResources"
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
    add("kspCommonMainMetadata", libs.room.compiler)
    add("kspAndroid", libs.room.compiler)
    add("kspDesktop", libs.room.compiler)
    //add("kspNative", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
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
            |   const val applicationId = "$lApplicationId"
            |   const val applicationVersion = "$lApplicationVersion"
            |   const val applicationVersionCode = "$lApplicationVersionCode"
            |
            |   const val debug = true
            |
            |   const val firebaseDefaultWebClientId = "${keystoreProperties["firebaseDefaultWebClientId"]}"
            |   
            |   const val host = "https://gymtrack-sigma.vercel.app/api"
            |   //const val host = "http://$ip:3000/api"
            |   const val hostImage = "https://gymtrack-sigma.vercel.app/image"
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
