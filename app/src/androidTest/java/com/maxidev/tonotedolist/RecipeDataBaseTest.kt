package com.maxidev.tonotedolist

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maxidev.tonotedolist.data.db.AppDataBase
import com.maxidev.tonotedolist.data.db.dao.NoteDao
import com.maxidev.tonotedolist.data.db.entities.NoteEntity
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
    private lateinit var noteDao: NoteDao
    private lateinit var db: AppDataBase
    private val recipeOne = NoteEntity(id = 1L, noteTitle = "InsertTest", noteBody = "🍞")
    private val recipeTwo = NoteEntity(id = 2L, noteTitle = "InsertingTest", noteBody = "🍒")

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java
        ).build()
        noteDao = db.noteDao()
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
        val allItems = noteDao.getAllNotes().first()
        assertEquals(allItems[0], recipeOne)
    }

    @Test
    @Throws(Exception::class)
    fun getAllItems() = runBlocking {
        addTwoRecipe()
        val allItems = noteDao.getAllNotes().first()
        assertEquals(allItems[0], recipeOne)
        assertEquals(allItems[1], recipeTwo)
    }

    @Test
    @Throws(Exception::class)
    fun getOneItem() = runBlocking {
        addOneRecipe()
        val item = noteDao.getNoteById(1)
        assertEquals(item.first(), recipeOne)
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteItems_deletesAllItemsFromDB() = runBlocking {
        addTwoRecipe()
        noteDao.deleteNote(recipeOne)
        noteDao.deleteNote(recipeTwo)
        val allItems = noteDao.getAllNotes().first()
        assertTrue(allItems.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateItems_updatesItemsInDB() = runBlocking {
        addTwoRecipe()
        noteDao.updateNote(NoteEntity(1L, "Apples", "E"))
        noteDao.updateNote(NoteEntity(2L, "Yes", "A"))

        val allItems = noteDao.getAllNotes().first()
        assertEquals(allItems[0], NoteEntity(1L, "Apples", "E"))
        assertEquals(allItems[1], NoteEntity(2L, "Yes", "A"))
    }

    private suspend fun addOneRecipe() {
        noteDao.insertNote(recipeOne)
    }

    private suspend fun addTwoRecipe() {
        noteDao.insertNote(recipeOne)
        noteDao.insertNote(recipeTwo)
    }
}