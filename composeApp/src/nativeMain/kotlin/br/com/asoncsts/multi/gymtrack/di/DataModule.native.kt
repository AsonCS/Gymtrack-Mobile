package br.com.asoncsts.multi.gymtrack.di

import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.asoncsts.multi.gymtrack.database.AppDatabase
import coil3.PlatformContext

actual val platform = object : Platform {

    override val coilContext: PlatformContext
        get() = TODO("Not yet implemented")

    override val databaseBuilder: RoomDatabase.Builder<AppDatabase>
        get() {
            val dbFilePath = "$documentDirectory/gymtrack_room.db"
            return Room.databaseBuilder<AppDatabase>(
                name = dbFilePath,
            )
        }

    override val engine
        get() = TODO("Not yet implemented")

    override val type = Platform.Type.IOS

}

private val documentDirectory: String
    get() {
        /*
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
    */
        TODO("Not yet implemented")
    }
