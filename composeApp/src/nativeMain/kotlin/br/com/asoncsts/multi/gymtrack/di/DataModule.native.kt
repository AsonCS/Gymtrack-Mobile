package br.com.asoncsts.multi.gymtrack.di

import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.asoncsts.multi.gymtrack.database.AppDatabase

actual val platform = object : Platform {

    override val databaseBuilder: RoomDatabase.Builder<AppDatabase>
        get() {
            val dbFilePath = "$documentDirectory/my_room.db"
            return Room.databaseBuilder<AppDatabase>(
                name = dbFilePath,
            )
        }

    override val engine
        get() = TODO("Not yet implemented")

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
