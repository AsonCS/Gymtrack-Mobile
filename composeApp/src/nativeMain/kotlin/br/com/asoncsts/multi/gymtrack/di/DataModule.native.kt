@file:OptIn(ExperimentalForeignApi::class)

package br.com.asoncsts.multi.gymtrack.di

import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.asoncsts.multi.gymtrack.database.AppDatabase
import coil3.PlatformContext
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.*

actual val platform = object : Platform {

    override val coilContext = PlatformContext.INSTANCE

    override val databaseBuilder: RoomDatabase.Builder<AppDatabase>
        get() {
            val dbFilePath = "$documentDirectory/gymtrack_room.db"
            return Room.databaseBuilder<AppDatabase>(
                name = dbFilePath,
            )
        }

}

private val documentDirectory: String
    get() {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory?.path)
    }
