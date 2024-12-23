package br.com.asoncsts.multi.gymtrack.di

import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.asoncsts.multi.gymtrack.database.AppDatabase
import io.ktor.client.engine.apache5.Apache5
import java.io.File

actual val platform = object : Platform {

    override val databaseBuilder: RoomDatabase.Builder<AppDatabase>
        get() {
            val dbFile = File(
                System.getProperty("java.io.tmpdir"),
                "my_room.db"
            )
            return Room.databaseBuilder<AppDatabase>(
                name = dbFile.absolutePath,
            )
        }

    override val engine = Apache5

}
