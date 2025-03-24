package com.example.goals.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.goals.R

data class Goals(
    val day: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val goal: Int,
    @StringRes val explain: Int
)


val goals = listOf(
    Goals(1, R.drawable.town, R.string.goal1, R.string.goal1_description),
    Goals(2, R.drawable.town, R.string.goal2, R.string.goal2_description),
    Goals(3, R.drawable.town, R.string.goal3, R.string.goal3_description),
    Goals(4, R.drawable.town, R.string.goal4, R.string.goal4_description),
    Goals(5, R.drawable.town, R.string.goal5, R.string.goal5_description),
    Goals(6, R.drawable.town, R.string.goal6,  R.string.goal6_description),
    Goals(7, R.drawable.town, R.string.goal7, R.string.goal7_description),
    Goals(8, R.drawable.town, R.string.goal8,  R.string.goal8_description),
    Goals(9, R.drawable.town, R.string.goal9,  R.string.goal9_description),
    Goals(10, R.drawable.town, R.string.goal10,  R.string.goal10_description)
)