package com.maxidev.tonotedolist.di

import android.content.Context
import androidx.room.Room
import com.maxidev.tonotedolist.data.db.AppDataBase
import com.maxidev.tonotedolist.data.db.dao.NoteDao
import com.maxidev.tonotedolist.data.db.dao.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "app_data_base"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesGroceryDao(dataBase: AppDataBase): ToDoDao {
        return dataBase.todoDao()
    }

    @Provides
    @Singleton
    fun providesRecipeDao(dataBase: AppDataBase): NoteDao {
        return dataBase.noteDao()
    }
}