package br.com.asoncsts.multi.gymtrack

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class GenericTest {

    @Test
    fun `Math 4 operations`() = runBlocking {
        Assert.assertEquals(
            2,
            1 + 1
        )
        Assert.assertEquals(
            0,
            2 - 2
        )
        Assert.assertEquals(
            9,
            3 * 3
        )
        Assert.assertEquals(
            1,
            4 / 4
        )
    }

}

/*
Executing tasks: [tasks] in project C:\Users\acsgs\MultiProjects\Gymtrack\composeApp

Type-safe project accessors is an incubating feature.
> Task :composeApp:tasks
------------------------------------------------------------
Tasks runnable from project ':composeApp'
------------------------------------------------------------
Android tasks
-------------
androidDependencies - Displays the Android dependencies of the project.
signingReport - Displays the signing info for the base and test modules
sourceSets - Prints out all the source sets defined in this project.
Build tasks
-----------
allMetadataJar - Assembles a jar archive containing the metadata for all Kotlin source sets.
assemble - Assemble main outputs for all the variants.
assembleAndroidTest - Assembles all the Test applications.
assembleUnitTest - Assembles all the unit test applications.
build - Assembles and tests this project.
buildDependents - Assembles and tests this project and all projects that depend on it.
buildKotlinToolingMetadata - Build metadata json file containing information about the used Kotlin tooling
buildNeeded - Assembles and tests this project and all projects it depends on.
bundle - Assemble bundles for all the variants.
clean - Deletes the build directory.
cleanAllTests - Deletes all the test results.
compileAppleMainKotlinMetadata - Compiles a klibrary from the 'appleMain' compilation in target 'metadata'.
compileDebugAndroidTestSources
compileDebugSources
compileDebugUnitTestSources
compileIosMainKotlinMetadata - Compiles a klibrary from the 'iosMain' compilation in target 'metadata'.
compileKotlinIosArm64 - Compiles a klibrary from the 'main' compilation in target 'iosArm64'.
compileKotlinIosSimulatorArm64 - Compiles a klibrary from the 'main' compilation in target 'iosSimulatorArm64'.
compileKotlinIosX64 - Compiles a klibrary from the 'main' compilation in target 'iosX64'.
compileNativeMainKotlinMetadata - Compiles a klibrary from the 'nativeMain' compilation in target 'metadata'.
compileReleaseSources
compileReleaseUnitTestSources
compileTestKotlinIosArm64 - Compiles a klibrary from the 'test' compilation in target 'iosArm64'.
compileTestKotlinIosSimulatorArm64 - Compiles a klibrary from the 'test' compilation in target 'iosSimulatorArm64'.
compileTestKotlinIosX64 - Compiles a klibrary from the 'test' compilation in target 'iosX64'.
desktopJar - Assembles an archive containing the main classes.
desktopMainClasses - Assembles outputs for compilation 'main' of target 'desktop'
desktopSourcesJar - Assembles a jar archive containing the sources of target 'desktop'.
desktopTestClasses - Assembles outputs for compilation 'test' of target 'desktop'
embedAndSignAppleFrameworkForXcode - Embed and sign  framework as requested by Xcode's environment variables
iosArm64Binaries - Assembles outputs for target 'iosArm64'.
iosArm64MainBinaries - Links all binaries for compilation 'main' of target 'iosArm64'.
iosArm64MainKlibrary - Assembles outputs for compilation 'main' of target 'iosArm64'
iosArm64MetadataElements - Assembles Kotlin metadata of target 'iosArm64'.
iosArm64SourcesJar - Assembles a jar archive containing the sources of target 'iosArm64'.
iosArm64TestBinaries - Links all binaries for compilation 'test' of target 'iosArm64'.
iosArm64TestKlibrary - Assembles outputs for compilation 'test' of target 'iosArm64'
iosSimulatorArm64Binaries - Assembles outputs for target 'iosSimulatorArm64'.
iosSimulatorArm64MainBinaries - Links all binaries for compilation 'main' of target 'iosSimulatorArm64'.
iosSimulatorArm64MainKlibrary - Assembles outputs for compilation 'main' of target 'iosSimulatorArm64'
iosSimulatorArm64MetadataElements - Assembles Kotlin metadata of target 'iosSimulatorArm64'.
iosSimulatorArm64SourcesJar - Assembles a jar archive containing the sources of target 'iosSimulatorArm64'.
iosSimulatorArm64TestBinaries - Links all binaries for compilation 'test' of target 'iosSimulatorArm64'.
iosSimulatorArm64TestKlibrary - Assembles outputs for compilation 'test' of target 'iosSimulatorArm64'
iosX64Binaries - Assembles outputs for target 'iosX64'.
iosX64MainBinaries - Links all binaries for compilation 'main' of target 'iosX64'.
iosX64MainKlibrary - Assembles outputs for compilation 'main' of target 'iosX64'
iosX64MetadataElements - Assembles Kotlin metadata of target 'iosX64'.
iosX64SourcesJar - Assembles a jar archive containing the sources of target 'iosX64'.
iosX64TestBinaries - Links all binaries for compilation 'test' of target 'iosX64'.
iosX64TestKlibrary - Assembles outputs for compilation 'test' of target 'iosX64'
linkDebugFrameworkIosArm64 - Links a framework 'debugFramework' for a target 'iosArm64'.
linkDebugFrameworkIosSimulatorArm64 - Links a framework 'debugFramework' for a target 'iosSimulatorArm64'.
linkDebugFrameworkIosX64 - Links a framework 'debugFramework' for a target 'iosX64'.
linkDebugTestIosArm64 - Links a test executable 'debugTest' for a target 'iosArm64'.
linkDebugTestIosSimulatorArm64 - Links a test executable 'debugTest' for a target 'iosSimulatorArm64'.
linkDebugTestIosX64 - Links a test executable 'debugTest' for a target 'iosX64'.
linkIosArm64 - Links all binaries for target 'iosArm64'.
linkIosSimulatorArm64 - Links all binaries for target 'iosSimulatorArm64'.
linkIosX64 - Links all binaries for target 'iosX64'.
linkReleaseFrameworkIosArm64 - Links a framework 'releaseFramework' for a target 'iosArm64'.
linkReleaseFrameworkIosSimulatorArm64 - Links a framework 'releaseFramework' for a target 'iosSimulatorArm64'.
linkReleaseFrameworkIosX64 - Links a framework 'releaseFramework' for a target 'iosX64'.
metadataAppleMainClasses - Assembles outputs for compilation 'appleMain' of target 'metadata'
metadataCommonMainClasses - Assembles outputs for compilation 'commonMain' of target 'metadata'
metadataIosMainClasses - Assembles outputs for compilation 'iosMain' of target 'metadata'
metadataMainClasses - Assembles outputs for compilation 'main' of target 'metadata'
metadataNativeMainClasses - Assembles outputs for compilation 'nativeMain' of target 'metadata'
metadataSourcesJar - Assembles a jar archive containing the sources of target 'metadata'.
wasmJsJar - Assembles an archive containing the main classes.
wasmJsMainClasses - Assembles outputs for compilation 'main' of target 'wasmJs'
wasmJsSourcesJar - Assembles a jar archive containing the sources of target 'wasmJs'.
wasmJsTestClasses - Assembles outputs for compilation 'test' of target 'wasmJs'
Compose desktop tasks
---------------------
checkRuntime
createDistributable
createReleaseDistributable
createRuntimeImage
flattenJars
flattenReleaseJars
notarizeDmg
notarizeReleaseDmg
package
packageDeb
packageDistributionForCurrentOS
packageDmg
packageMsi
packageReleaseDeb
packageReleaseDistributionForCurrentOS
packageReleaseDmg
packageReleaseMsi
packageReleaseUberJarForCurrentOS
packageUberJarForCurrentOS
prepareAppResources
proguardReleaseJars
run
runDistributable
runRelease
runReleaseDistributable
suggestRuntimeModules
unpackDefaultComposeDesktopJvmApplicationResources
Firebase Crashlytics tasks
--------------------------
injectCrashlyticsMappingFileIdDebug - Generates and injects a mapping file id into the app, used by Crashlytics for deobfuscation.
injectCrashlyticsMappingFileIdRelease - Generates and injects a mapping file id into the app, used by Crashlytics for deobfuscation.
Help tasks
----------
buildEnvironment - Displays all buildscript dependencies declared in project ':composeApp'.
dependencies - Displays all dependencies declared in project ':composeApp'.
dependencyInsight - Displays the insight into a specific dependency in project ':composeApp'.
help - Displays a help message.
javaToolchains - Displays the detected java toolchains.
kotlinDslAccessorsReport - Prints the Kotlin code for accessing the currently available project extensions and conventions.
outgoingVariants - Displays the outgoing variants of project ':composeApp'.
projects - Displays the sub-projects of project ':composeApp'.
properties - Displays the properties of project ':composeApp'.
resolvableConfigurations - Displays the configurations that can be resolved in project ':composeApp'.
tasks - Displays the tasks runnable from project ':composeApp'.
Ide tasks
---------
resolveIdeDependencies - Debugging/Diagnosing task that will resolve dependencies for the IDE
Install tasks
-------------
installDebug - Installs the Debug build.
installDebugAndroidTest - Installs the android (on device) tests for the Debug build.
installRelease - Installs the Release build.
uninstallAll - Uninstall all applications.
uninstallDebug - Uninstalls the Debug build.
uninstallDebugAndroidTest - Uninstalls the android (on device) tests for the Debug build.
uninstallRelease - Uninstalls the Release build.
Interop tasks
-------------
commonize - Aggregator task for all c-interop & Native distribution commonizer tasks
runCommonizer - [Deprecated: Use 'commonize' instead]
Kotlin browser tasks
--------------------
wasmJsBrowserDevelopmentExecutableDistribution
wasmJsBrowserDevelopmentRun - start development webpack dev server
wasmJsBrowserDevelopmentWebpack - build webpack development bundle
wasmJsBrowserDistribution
wasmJsBrowserProductionRun - start production webpack dev server
wasmJsBrowserProductionWebpack - build webpack production bundle
wasmJsBrowserRun
wasmJsBrowserWebpack
NodeJs tasks
------------
wasmJsPackageJson - Create package.json file for compilation 'main' (target wasmJs (wasm))
wasmJsTestPackageJson - Create package.json file for compilation 'test' (target wasmJs (wasm))
Run tasks
---------
desktopRun - Jvm Run task for target 'desktop' and compilation 'main'. This task can act as carrier for the IDE to execute jvm based code
Verification tasks
------------------
allTests - Runs the tests for all targets and create aggregated report
check - Runs all checks.
checkJetifier - Checks whether Jetifier is needed for the current project
checkKotlinGradlePluginConfigurationErrors - Checks that Kotlin Gradle Plugin hasn't reported project configuration errors, failing otherwise. This task always runs before compileKotlin* or similar tasks.
connectedAndroidTest - Installs and runs instrumentation tests for all flavors on connected devices.
connectedCheck - Runs all device checks on currently connected devices.
connectedDebugAndroidTest - Installs and runs the tests for debug on connected devices.
desktopTest - Runs the tests of the test test run.
deviceAndroidTest - Installs and runs instrumentation tests using all Device Providers.
deviceCheck - Runs all device checks using Device Providers and Test Servers.
iosSimulatorArm64Test - Executes Kotlin/Native unit tests for target iosSimulatorArm64.
iosX64Test - Executes Kotlin/Native unit tests for target iosX64.
lint - Runs lint on the default variant.
lintDebug - Print text output from the corresponding lint report task
lintFix - Runs lint on the default variant and applies any safe suggestions to the source code.
lintRelease - Print text output from the corresponding lint report task
lintVitalRelease - Print text output from the corresponding lint report task
test - Run unit tests for all variants.
testDebugUnitTest - Run unit tests for the debug build.
testReleaseUnitTest - Run unit tests for the release build.
updateLintBaseline - Updates the lint baseline using the default variant.
wasmJsBrowserTest - Run all wasmJs tests inside browser using karma and webpack
wasmJsTest - Run JS tests for all platforms
Rules
-----
Pattern: clean<TaskName>: Cleans the output files of a task.
Pattern: build<ConfigurationName>: Assembles the artifacts of a configuration.
To see all tasks and more detail, run gradle tasks --all
To see more detail about a task, run gradle help --task <task>

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.9/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.
BUILD SUCCESSFUL in 456ms
1 actionable task: 1 executed

Build Analyzer results available
10:44:56 AM: Execution finished 'tasks'.
 */
