package br.com.asoncsts.multi.gymtrack.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data.exercise.api.ExerciseApi
import br.com.asoncsts.multi.gymtrack.data.exercise.remote.ExerciseRemote
import br.com.asoncsts.multi.gymtrack.data.exercise.repository.ExerciseRepository
import br.com.asoncsts.multi.gymtrack.data.user.api.ExerciseExecutionApi
import br.com.asoncsts.multi.gymtrack.data.user.api.WorkoutApi
import br.com.asoncsts.multi.gymtrack.data.user.local.WorkoutLocal
import br.com.asoncsts.multi.gymtrack.data.user.remote.ExerciseExecutionRemote
import br.com.asoncsts.multi.gymtrack.data.user.remote.WorkoutRemote
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExerciseExecutionRepository
import br.com.asoncsts.multi.gymtrack.data.user.repository.WorkoutRepository
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
    // Coil
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

    single<AppDatabase> {
        platform.databaseBuilder
            //.addMigrations(MIGRATIONS)
            .fallbackToDestructiveMigrationOnDowngrade(true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    // Api
    single<ExerciseApi> {
        ExerciseApi.Impl(
            client = get(),
            host = BuildConfig.HOST
        )
    }
    single<ExerciseExecutionApi> {
        ExerciseExecutionApi.Impl(
            client = get(),
            host = BuildConfig.HOST
        )
    }
    single<WorkoutApi> {
        WorkoutApi.Impl(
            client = get(),
            host = BuildConfig.HOST
        )
    }

    // Local
    single<WorkoutLocal> {
        WorkoutLocal.Impl(
            workoutDao = get<AppDatabase>()
                .workoutDao()
        )
    }

    // Remote
    single<ExerciseRemote> {
        ExerciseRemote.Impl(
            api = get()
        )
    }
    single<ExerciseExecutionRemote> {
        ExerciseExecutionRemote.Impl(
            api = get()
        )
    }
    single<WorkoutRemote> {
        WorkoutRemote.Impl(
            api = get()
        )
    }

    // Repository
    single<ExerciseRepository> {
        ExerciseRepository.Impl(
            remote = get()
        )
    }
    single<ExerciseExecutionRepository> {
        ExerciseExecutionRepository.Impl(
            remote = get()
        )
    }
    single<WorkoutRepository> {
        WorkoutRepository.Impl(
            local = get(),
            remote = get()
        )
    }
}
