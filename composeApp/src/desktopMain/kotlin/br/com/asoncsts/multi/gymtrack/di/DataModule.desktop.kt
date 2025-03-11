package br.com.asoncsts.multi.gymtrack.di

import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.asoncsts.multi.gymtrack.database.AppDatabase
import coil3.PlatformContext
import io.ktor.client.engine.apache5.Apache5
import java.io.File

actual val platform = object : Platform {

    override val coilContext: PlatformContext
        get() = PlatformContext.INSTANCE

    override val databaseBuilder: RoomDatabase.Builder<AppDatabase>
        get() {
            val dbFile = File(
                "build/gymtrack_room",
                "gymtrack_room.db"
            )
            return Room.databaseBuilder<AppDatabase>(
                name = dbFile.absolutePath,
            )
        }

    override val engine = Apache5

}
