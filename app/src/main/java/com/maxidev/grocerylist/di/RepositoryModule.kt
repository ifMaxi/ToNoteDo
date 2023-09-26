package com.maxidev.grocerylist.di

import com.maxidev.grocerylist.data.repository.groceryrepository.GroceryRepository
import com.maxidev.grocerylist.data.repository.groceryrepository.GroceryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface RepositoryModule {
    @Binds
    fun provideGroceryRepositoryImpl(repository: GroceryRepositoryImpl): GroceryRepository
}