package com.maxidev.tonotedolist.di

import com.maxidev.tonotedolist.data.repository.todorepository.TodoRepository
import com.maxidev.tonotedolist.data.repository.todorepository.TodoRepositoryImpl
import com.maxidev.tonotedolist.data.repository.noterepository.NoteRepository
import com.maxidev.tonotedolist.data.repository.noterepository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsToDoRepositoryImpl(
        repository: TodoRepositoryImpl
    ): TodoRepository

    @Binds
    abstract fun bindsNoteRepositoryImpl(
        repository: NoteRepositoryImpl
    ): NoteRepository
}