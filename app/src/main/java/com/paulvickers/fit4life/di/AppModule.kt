package com.paulvickers.fit4life.di

import android.app.Application
import androidx.room.Room
import com.paulvickers.fit4life.data.Fit4LifeDatabase
import com.paulvickers.fit4life.data.repository.WorkoutDayRepositoryImpl
import com.paulvickers.fit4life.data.repository.WorkoutTitleRepositoryImpl
import com.paulvickers.fit4life.domain.repository.WorkoutDayRepository
import com.paulvickers.fit4life.domain.repository.WorkoutTitleRepository
import com.paulvickers.fit4life.domain.use_case.DeleteWorkoutDaysByWorkoutTitleIdUseCase
import com.paulvickers.fit4life.domain.use_case.GetDaysOfWorkoutUseCase
import com.paulvickers.fit4life.domain.use_case.InsertWorkoutDayUseCase
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

    @Provides
    @Singleton
    fun provideWorkoutTitleRepository(db: Fit4LifeDatabase): WorkoutTitleRepository {
        return WorkoutTitleRepositoryImpl(db.workoutTitleDao)
    }

    @Provides
    @Singleton
    fun provideWorkoutDayRepository(db: Fit4LifeDatabase): WorkoutDayRepository {
        return WorkoutDayRepositoryImpl(db.workoutDayDao)
    }

    @Provides
    @Singleton
    fun provideWorkoutUseCases(repository: WorkoutTitleRepository): WorkoutTitleUseCases {
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
    fun provideGetDaysOfWorkUseCase(repository: WorkoutDayRepository): GetDaysOfWorkoutUseCase {
        return GetDaysOfWorkoutUseCase(
            repository
        )
    }

    @Provides
    @Singleton
    fun provideDeleteWorkoutDaysByWorkoutTitleIdTitleUseCase(repository: WorkoutDayRepository): DeleteWorkoutDaysByWorkoutTitleIdUseCase {
        return DeleteWorkoutDaysByWorkoutTitleIdUseCase(
            repository
        )
    }

    @Provides
    @Singleton
    fun provideInsertWorkoutDayUseCase(repository: WorkoutDayRepository): InsertWorkoutDayUseCase {
        return InsertWorkoutDayUseCase(
            repository
        )
    }

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope