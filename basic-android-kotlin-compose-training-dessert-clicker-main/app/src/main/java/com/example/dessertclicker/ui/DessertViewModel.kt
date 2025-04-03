package com.example.dessertclicker.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.log
import android.os.Bundle


class DessertViewModel: ViewModel() {
    //data
    private val desserts: List<Dessert> = Datasource.dessertList

    //variable
    private var revenue by mutableIntStateOf(0)
    private var currentIndex by mutableIntStateOf(0)
    private var currentPrice by mutableIntStateOf(desserts[currentIndex].price)
    private var currentImage by mutableIntStateOf(desserts[currentIndex].imageId)
    private var countSold by mutableIntStateOf(0)

    //UI state variable
    private val _uiState = MutableStateFlow(DessertUiState(dessertImageId = desserts[currentIndex].imageId, currentPrice = currentPrice))
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    init {
        resetDesert()
    }

    private fun resetDesert(){

    }

//    fun updateCount (revenue: Int, sold: Int) {
//        countSold = sold
//        countRevenue = revenue
//    }

    fun addCount() {
        countSold += 1
        revenue += currentPrice
        Log.d( TAG,"revenue : $revenue")
        Log.d( TAG,"currentPrice : $currentPrice")
        Log.d( TAG,"desserts[currentIndex] : $desserts")
    }

    private fun updateDessertState (
        @DrawableRes imageId: Int
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                revenue = revenue,
                dessertsSold = countSold,
                dessertImageId = imageId,
                currentIndex = currentIndex
            )
        }
    }
//addCount()->updateUi
    fun updateUi () {
        val image = determineDessertToShow(desserts = desserts, dessertsSold = countSold)
        if (image.imageId != currentImage) {
            currentIndex++
            //明示的に宣言
            currentPrice = desserts[currentIndex].price
            currentImage = image.imageId
        }
        Log.d( TAG,"currentIndex : $currentIndex")
        updateDessertState(image.imageId)
    }

    private fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                break
            }
        }
        return dessertToShow
    }
}