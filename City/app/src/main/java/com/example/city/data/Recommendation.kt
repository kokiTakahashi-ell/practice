package com.example.city.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recommendation (
    val id: Long,
    @StringRes val name: Int,
    @StringRes val description: Int,
    @DrawableRes val imageUrl: Int,
    val category: CategoryType = CategoryType.Shopping,
)
