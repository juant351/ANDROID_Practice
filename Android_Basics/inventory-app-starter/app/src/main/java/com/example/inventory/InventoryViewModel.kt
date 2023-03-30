package com.example.inventory

import androidx.lifecycle.*
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDAO
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDAO: ItemDAO) : ViewModel() {

    val allItems: LiveData<List<Item>> = itemDAO.getItems().asLiveData()

    // Takes in an Item object and adds the data to the database in a non-blocking way.
    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDAO.insert(item)
        }
    }

    // Takes in three strings and returns an Item instance.
    private fun getNewItemEntry(itemName: String, itemPrice: String, itemCount: String): Item {
        return Item(
            itemName = itemName,
            itemPrice = itemPrice.toDouble(),
            quantityInStock = itemCount.toInt()
        )
    }

    // Takes in three strings for item details. This will be called from the UI fragment to add Item details to the database.
    fun addNewItem(itemName: String, itemPrice: String, itemCount: String) {
        val newItem = getNewItemEntry(itemName, itemPrice, itemCount)
        insertItem(newItem)
    }

    fun isEntryValid(itemName: String, itemPrice: String, itemCount: String): Boolean {
        if (itemName.isBlank() || itemPrice.isBlank() || itemCount.isBlank()) {
            return false
        }
        return true
    }

    fun retrieveItem(id: Int): LiveData<Item> {
        return itemDAO.getItem(id).asLiveData()
    }

    /* Takes an instance of the entity class, Item and returns nothing.
     * It calls the DAO's update method.
     */
    private fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDAO.update(item)
        }
    }

    fun sellItem(item: Item) {
        if (item.quantityInStock > 0) {
            val newItem = item.copy(quantityInStock = item.quantityInStock - 1)
            updateItem(newItem)
        }
    }

    // Check if the quantity of an item is greater than 0
    fun isStockAvailable(item: Item): Boolean {
        return (item.quantityInStock > 0)
    }

    // It calls the DAO delete() function.
    fun deleteItem(item: Item) {
        viewModelScope.launch{
            itemDAO.delete(item)
        }
    }

    // Creates an Item from the attributes
    private fun getUpdatedItemEntry(
        itemId: Int,
        itemName: String,
        itemPrice: String,
        itemCount: String
    ): Item{
        return Item(
            id = itemId,
            itemName = itemName,
            itemPrice = itemPrice.toDouble(),
            quantityInStock = itemCount.toInt()
        )
    }

    // With the entries from the fragment, it gets a new Item and send it to update
    fun updateItem(
        itemId: Int,
        itemName: String,
        itemPrice: String,
        itemCount: String
    ) {
        val updatedItem = getUpdatedItemEntry(itemId, itemName, itemPrice, itemCount)
        updateItem(updatedItem)
    }
}

// Tip: The creation of the ViewModel factory is mostly boilerplate code, so you can reuse this code for future ViewModel factories
class InventoryViewModelFactory(private val itemDAO: ItemDAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(itemDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}