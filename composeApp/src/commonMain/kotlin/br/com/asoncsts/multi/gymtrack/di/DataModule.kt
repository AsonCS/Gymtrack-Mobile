package br.com.asoncsts.multi.gymtrack.di

import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data.exercise.api.ExerciseApi
import br.com.asoncsts.multi.gymtrack.data.exercise.remote.ExerciseRemote
import br.com.asoncsts.multi.gymtrack.data.exercise.repository.ExerciseRepository
import br.com.asoncsts.multi.gymtrack.data.image.api.ImageApi
import br.com.asoncsts.multi.gymtrack.data.image.repository.ImageRepository
import br.com.asoncsts.multi.gymtrack.data.user.api.ExerciseExecutionApi
import br.com.asoncsts.multi.gymtrack.data.user.api.WorkoutApi
import br.com.asoncsts.multi.gymtrack.data.user.remote.ExerciseExecutionRemote
import br.com.asoncsts.multi.gymtrack.data.user.remote.WorkoutRemote
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExerciseExecutionRepository
import br.com.asoncsts.multi.gymtrack.data.user.repository.WorkoutRepository
import br.com.asoncsts.multi.gymtrack.extension.log
import br.com.asoncsts.multi.gymtrack.generated.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

interface Platform {
    val engine: HttpClientEngineFactory<*>
}

expect val platform: Platform

internal fun dataModule() = module {
    // Api
    factory<ExerciseApi> {
        ExerciseApi.Impl(BuildConfig.HOST)
    }
    factory<ExerciseExecutionApi> {
        ExerciseExecutionApi.Impl(BuildConfig.HOST)
    }
    factory<ImageApi> {
        ImageApi.Impl(BuildConfig.HOST_IMAGE)
    }
    factory<WorkoutApi> {
        WorkoutApi.Impl(BuildConfig.HOST)
    }

    // Remote
    single<ExerciseRemote> {
        ExerciseRemote.Impl(
            api = get(),
            client = get()
        )
    }
    single<ExerciseExecutionRemote> {
        ExerciseExecutionRemote.Impl(
            api = get(),
            client = get()
        )
    }
    single<WorkoutRemote> {
        WorkoutRemote.Impl(
            api = get(),
            client = get()
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
    single<ImageRepository> {
        ImageRepository.Impl(
            api = get()
        )
    }
    single<WorkoutRepository> {
        WorkoutRepository.Impl(
            remote = get()
        )
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
}
