package com.maxidev.grocerylist

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxidev.grocerylist.data.db.AppDataBase
import com.maxidev.grocerylist.data.db.dao.RecipeDao
import com.maxidev.grocerylist.data.db.entities.RecipeEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeDataBaseTest {
    private lateinit var recipeDao: RecipeDao
    private lateinit var db: AppDataBase
    private val recipeOne = RecipeEntity(id = 1L, recipeTitle = "InsertTest", recipeBody = "🍞")
    private val recipeTwo = RecipeEntity(id = 2L, recipeTitle = "InsertingTest", recipeBody = "🍒")

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java
        ).build()
        recipeDao = db.recipeDao()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_toDb() = runBlocking {
        addOneRecipe()
        val allItems = recipeDao.getAllRecipe().first()
        assertEquals(allItems[0], recipeOne)
    }

    @Test
    @Throws(Exception::class)
    fun getAllItems() = runBlocking {
        addTwoRecipe()
        val allItems = recipeDao.getAllRecipe().first()
        assertEquals(allItems[0], recipeOne)
        assertEquals(allItems[1], recipeTwo)
    }

    @Test
    @Throws(Exception::class)
    fun getOneItem() = runBlocking {
        addOneRecipe()
        val item = recipeDao.getRecipeById(1)
        assertEquals(item.first(), recipeOne)
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteItems_deletesAllItemsFromDB() = runBlocking {
        addTwoRecipe()
        recipeDao.deleteRecipe(recipeOne)
        recipeDao.deleteRecipe(recipeTwo)
        val allItems = recipeDao.getAllRecipe().first()
        assertTrue(allItems.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateItems_updatesItemsInDB() = runBlocking {
        addTwoRecipe()
        recipeDao.updateRecipe(RecipeEntity(1L, "Apples", "E"))
        recipeDao.updateRecipe(RecipeEntity(2L, "Yes", "A"))

        val allItems = recipeDao.getAllRecipe().first()
        assertEquals(allItems[0], RecipeEntity(1L, "Apples", "E"))
        assertEquals(allItems[1], RecipeEntity(2L, "Yes", "A"))
    }

    private suspend fun addOneRecipe() {
        recipeDao.insertRecipe(recipeOne)
    }

    private suspend fun addTwoRecipe() {
        recipeDao.insertRecipe(recipeOne)
        recipeDao.insertRecipe(recipeTwo)
    }
}