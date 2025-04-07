package com.example.city.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CityType (
    val id: Long,
    @StringRes val name: Int,
    @DrawableRes val imageUrl: Int,
    val category: CategoryType,
)