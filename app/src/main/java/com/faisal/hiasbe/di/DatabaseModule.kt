package com.faisal.hiasbe.di

import android.app.Application
import androidx.room.Room
import com.faisal.hiasbe.data.local.TodoDao
import com.faisal.hiasbe.data.local.TodoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    companion object{
        private const val DATA_BASE_NAME="todoDatabase"
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback: TodoDataBase.Callback): TodoDataBase {
        return Room.databaseBuilder(application, TodoDataBase::class.java,DATA_BASE_NAME)
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideGitRepositoryDoa(database: TodoDataBase): TodoDao {
        return database.getTodoDao()
    }


}