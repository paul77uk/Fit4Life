package com.paulvickers.fit4life.di

import android.app.Application
import androidx.room.Room
import com.paulvickers.fit4life.data.Fit4LifeDatabase
import com.paulvickers.fit4life.domain.use_case.workout_day_use_cases.*
import com.paulvickers.fit4life.domain.use_case.workout_title_usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
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
        callback: Fit4LifeDatabase.Callback,
    ): Fit4LifeDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            Fit4LifeDatabase::class.java,
        )
            .addCallback(callback)
            .build()
    }

    @ApplicationScope
    @Singleton
    @Provides
    fun provideApplicationScope(): CoroutineScope = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideWorkoutTitleRepository(db: Fit4LifeDatabase): com.paulvickers.fit4life.domain.repository.WorkoutTitleRepository {
        return com.paulvickers.fit4life.data.repositories.WorkoutTitleRepository(db.workoutTitleDao)
    }

    @Provides
    @Singleton
    fun provideWorkoutDayRepository(db: Fit4LifeDatabase): com.paulvickers.fit4life.domain.repository.WorkoutDayRepository {
        return com.paulvickers.fit4life.data.repositories.WorkoutDayRepository(db.workoutDayDao)
    }

    @Provides
    @Singleton
    fun provideWorkoutUseCases(repository: com.paulvickers.fit4life.domain.repository.WorkoutTitleRepository): WorkoutTitleUseCases {
        return WorkoutTitleUseCases(
            getWorkoutTitlesUseCase = GetWorkoutTitlesUseCase(repository),
            getWorkoutTitleByIdUseCase = GetWorkoutTitleByIdUseCase(repository),
            insertWorkoutTitleUseCase = InsertWorkoutTitleUseCase(repository),
            deleteWorkoutTitleUseCase = DeleteWorkoutTitleUseCase(repository),
            updateWorkoutTitleUseCase = UpdateWorkoutTitleUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDayUseCases(repository: com.paulvickers.fit4life.domain.repository.WorkoutDayRepository): WorkoutDayUseCases {
        return WorkoutDayUseCases(
            insertWorkoutDayUseCase = InsertWorkoutDayUseCase(repository),
            updateWorkoutDayUseCase = UpdateWorkoutDayUseCase(repository),
            getDaysOfWorkoutUseCase = GetDaysOfWorkoutUseCase(repository),
            getWorkoutDayByIdUseCase = GetWorkoutDayByIdUseCase(repository),
            deleteDayUseCase = DeleteDayUseCase(repository)
        )
    }

//    @Provides
//    @Singleton
//    fun provideGetDaysOfWorkUseCase(repository: WorkoutDayRepository): GetDaysOfWorkoutUseCase {
//        return GetDaysOfWorkoutUseCase(
//            repository
//        )
//    }

//    @Provides
//    @Singleton
//    fun provideDeleteWorkoutDaysByWorkoutTitleIdTitleUseCase(repository: WorkoutDayRepository): DeleteWorkoutDaysByWorkoutTitleIdUseCase {
//        return DeleteWorkoutDaysByWorkoutTitleIdUseCase(
//            repository
//        )
//    }

//    @Provides
//    @Singleton
//    fun provideDeleteDayUseCase(repository: WorkoutDayRepository): DeleteDayUseCase {
//        return DeleteDayUseCase(
//            repository
//        )
//    }
//
//    @Provides
//    @Singleton
//    fun provideInsertWorkoutDayUseCase(repository: WorkoutDayRepository): InsertWorkoutDayUseCase {
//        return InsertWorkoutDayUseCase(
//            repository
//        )
//    }

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope