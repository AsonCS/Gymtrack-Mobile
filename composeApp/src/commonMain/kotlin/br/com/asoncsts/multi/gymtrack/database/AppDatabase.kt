package br.com.asoncsts.multi.gymtrack.database

import androidx.room.*
import br.com.asoncsts.multi.gymtrack.data.user.local.dao.*
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.*

// https://developer.android.com/kotlin/multiplatform/room#android
@ConstructedBy(AppDatabaseConstructor::class)
@Database(
    entities = [
        ExecutionEntity::class,
        ExerciseExecutionEntity::class,
        WorkoutEntity::class,
        WorkoutWithExerciseExecutionsCrossRefEntity::class
    ],
//    autoMigrations = [
//        AutoMigration(from = 1, to = 2)
//    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun executionDao(): ExecutionDao
    abstract fun exerciseExecutionDao(): ExerciseExecutionDao
    abstract fun exerciseExecutionWithExecutionsDao(): ExerciseExecutionWithExecutionsDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun workoutWithExerciseExecutionsDao(): WorkoutWithExerciseExecutionsDao
}
