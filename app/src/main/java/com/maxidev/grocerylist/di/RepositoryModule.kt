package com.maxidev.grocerylist.di

import com.maxidev.grocerylist.data.repository.groceryrepository.GroceryRepository
import com.maxidev.grocerylist.data.repository.groceryrepository.GroceryRepositoryImpl
import com.maxidev.grocerylist.data.repository.reciperepository.RecipeRepository
import com.maxidev.grocerylist.data.repository.reciperepository.RecipeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsGroceryRepositoryImpl(
        repository: GroceryRepositoryImpl
    ): GroceryRepository

    @Binds
    abstract fun bindsRecipeRepositoryImpl(
        repository: RecipeRepositoryImpl
    ): RecipeRepository
}