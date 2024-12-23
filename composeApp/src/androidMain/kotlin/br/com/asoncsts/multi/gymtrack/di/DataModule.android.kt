package br.com.asoncsts.multi.gymtrack.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.asoncsts.multi.gymtrack.database.AppDatabase
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual val platform: Platform = object : Platform, KoinComponent {

    val context by inject<Context>()

    override val databaseBuilder: RoomDatabase.Builder<AppDatabase>
        get() {
            val appContext = context.applicationContext
            val dbFile = appContext.getDatabasePath("my_room.db")
            return Room.databaseBuilder<AppDatabase>(
                context = appContext,
                name = dbFile.absolutePath
            )
        }

    override val engine = OkHttp

}
