package br.com.asoncsts.multi.gymtrack.database

import androidx.room.*
import br.com.asoncsts.multi.gymtrack.data.user.local.model.*

// https://developer.android.com/kotlin/multiplatform/room#android
here
@ConstructedBy(AppDatabaseConstructor::class)
@Database(
    entities = [
        ExerciseExecutionEntity::class,
        ExerciseExecutionWorkoutCrossRefEntity::class,
        WorkoutEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
}
