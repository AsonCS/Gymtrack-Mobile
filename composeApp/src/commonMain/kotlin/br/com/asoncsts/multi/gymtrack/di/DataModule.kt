package br.com.asoncsts.multi.gymtrack.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data.exercise.api.ExerciseApi
import br.com.asoncsts.multi.gymtrack.data.exercise.remote.ExerciseRemote
import br.com.asoncsts.multi.gymtrack.data.exercise.repository.ExerciseRepository
import br.com.asoncsts.multi.gymtrack.data.user.local.*
import br.com.asoncsts.multi.gymtrack.data.user.repository.*
import br.com.asoncsts.multi.gymtrack.database.AppDatabase
import br.com.asoncsts.multi.gymtrack.extension.log
import br.com.asoncsts.multi.gymtrack.generated.BuildConfig
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.*
import coil3.util.DebugLogger
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import okio.FileSystem
import org.koin.dsl.module

interface Platform {
    val coilContext: PlatformContext
    val databaseBuilder: RoomDatabase.Builder<AppDatabase>
    val engine: HttpClientEngineFactory<*>
}

expect val platform: Platform

internal fun dataModule() = module {
    // region Coil
    single<ImageLoader> {
        ImageLoader.Builder(platform.coilContext)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(platform.coilContext, 0.3)
                    .strongReferencesEnabled(true)
                    .build()
            }
            .networkCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
                    .maxSizeBytes(512L * 1024 * 1024) // 512MB
                    .build()
            }
            .crossfade(true)
            .logger(DebugLogger()) // tag = ImageLoader
            .build()
    }
    factory<ImageRequest.Builder> {
        val builder = ImageRequest.Builder(platform.coilContext)
            .coroutineContext(Dispatchers.IO)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .networkCachePolicy(CachePolicy.ENABLED)

        builder
    }
    // endregion

    // region Ktor
    single {
        HttpClient(platform.engine) {
            install(Logging) {
                level = LogLevel.INFO

                logger = object : Logger {
                    override fun log(message: String) {
                        TAG_DATA.log("KtorHttpClient: $message")
                    }
                }
            }
            install(ContentNegotiation) {

                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    encodeDefaults = true
                    classDiscriminator = "#class"
                })
            }
            install(HttpCache)
        }
    }
    // endregion

    // region Database
    single<AppDatabase> {
        platform.databaseBuilder
            //.addMigrations(MIGRATIONS)
            .fallbackToDestructiveMigrationOnDowngrade(true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
    // endregion

    // region Api
    single<ExerciseApi> {
        ExerciseApi.Impl(
            client = get(),
            host = BuildConfig.HOST
        )
    }
    // endregion

    // region Local
    single<ExerciseExecutionLocal> {
        ExerciseExecutionLocal.Impl(
            dao = get<AppDatabase>()
                .exerciseExecutionDao()
        )
    }
    single<WorkoutLocal> {
        WorkoutLocal.Impl(
            dao = get<AppDatabase>()
                .workoutDao()
        )
    }
    single<WorkoutWithExerciseExecutionsLocal> {
        WorkoutWithExerciseExecutionsLocal.Impl(
            dao = get<AppDatabase>()
                .workoutWithExerciseExecutionsDao()
        )
    }
    // endregion

    // region Remote
    single<ExerciseRemote> {
        ExerciseRemote.Impl(
            api = get()
        )
    }
    // endregion

    // region Repository
    single<ExerciseRepository> {
        ExerciseRepository.Impl(
            remote = get()
        )
    }
    single<ExerciseExecutionRepository> {
        ExerciseExecutionRepository.Impl(
            local = get()
        )
    }
    single<WorkoutRepository> {
        WorkoutRepository.Impl(
            local = get()
        )
    }
    single<WorkoutWithExerciseExecutionsRepository> {
        WorkoutWithExerciseExecutionsRepository.Impl(
            local = get()
        )
    }
    // endregion
}
