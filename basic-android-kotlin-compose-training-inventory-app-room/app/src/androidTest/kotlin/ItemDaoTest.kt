package com.example.inventory

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import com.example.inventory.data.InventoryDatabase
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import org.junit.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue


@RunWith(AndroidJUnit4::class)
class ItemDaoTest {
    private lateinit var itemDao: ItemDao
    private lateinit var inventoryDatabase: InventoryDatabase
    private var item1 = Item(1, "Apples", 10.0, 20)
    private var item2 = Item(2, "Bananas", 15.0, 97)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        inventoryDatabase = Room.inMemoryDatabaseBuilder(context, InventoryDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        itemDao = inventoryDatabase.itemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        inventoryDatabase.close()
    }


    private suspend fun addOneItemToDb() {
        itemDao.insert(item1)
    }

    private suspend fun addTwoItemsToDb() {
        itemDao.insert(item1)
        itemDao.insert(item2)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsItemIntoDB() = runBlocking {
        addOneItemToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], item1)
    }
    @Test
    @Throws(Exception::class)
    fun daoGetAllItems_returnsAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], item1)
        assertEquals(allItems[1], item2)
    }
}