package com.example.dessertclicker.ui

import androidx.annotation.DrawableRes
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert

data class DessertUiState (
    val revenue: Int = 0,                     //合計の値段
    val dessertsSold: Int = 0,                //売れた個数
    val currentIndex : Int = 0,                            //Listのindex
    val countSold : Int = 0,
    val currentPrice : Int,
    @DrawableRes val dessertImageId: Int, //画像Id
//    val desserts: List<Dessert> = Datasource.dessertList,          //データリスト
)
