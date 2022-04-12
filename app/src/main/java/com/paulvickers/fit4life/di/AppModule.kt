package com.paulvickers.fit4life.di

import android.app.Application
import androidx.room.Room
import com.paulvickers.fit4life.data.data_access_objects.*
import com.paulvickers.fit4life.data.database.Fit4LifeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFit4LifeDatabase(
        app: Application,
        callback: Fit4LifeDatabase.Callback
    ): Fit4LifeDatabase {
        return Room.databaseBuilder(
            app,
            Fit4LifeDatabase::class.java,
            Fit4LifeDatabase.DATABASE_NAME,
        )
            .addCallback(callback)
            .build()
    }

    @ApplicationScope
    @Singleton
    @Provides
    fun provideApplicationScope(): CoroutineScope = CoroutineScope(SupervisorJob())

    @MainDispatcher
    @Singleton
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    fun provideWorkoutTitleDao(db: Fit4LifeDatabase): WorkoutTitleDao = db.workoutTitleDao

    @Provides
    @Singleton
    fun provideWorkoutDayDao(db: Fit4LifeDatabase): WorkoutDayDao = db.workoutDayDao

    @Provides
    @Singleton
    fun provideWorkoutWeekDao(db: Fit4LifeDatabase): WorkoutWeekDao = db.workoutWeekDao


    @Provides
    @Singleton
    fun provideExerciseTitleDao(db: Fit4LifeDatabase): ExerciseTitleDao = db.exerciseTitleDao

    @Provides
    @Singleton
    fun provideSetDao(db: Fit4LifeDatabase): SetDao = db.setDao

    @Provides
    @Singleton
    fun provideHistoryDao(db: Fit4LifeDatabase): HistoryDao = db.historyDao
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher