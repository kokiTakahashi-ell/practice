package com.example.city.ui

import com.example.city.data.CategoryType
import com.example.city.data.DataSource
import com.example.city.data.Recommendation

data class CityUiState (
    val category: Map<CategoryType, List<Recommendation>> = emptyMap(),
    val currentCategory: CategoryType = CategoryType.Shopping,
    val currentSelectedRecommendation: Recommendation = DataSource.recommendations[0],
    val isShowingCategoryPage: Boolean = true,
    val isShowingDetailsListScreen: Boolean = false,
    val isShowingRecommendationDetails: Boolean = false,
//    val currentRecommendationBox: List<Recommendation> = emptyList(),
    ) {
        val currentRecommendationBox: List<Recommendation> by lazy { category[currentCategory]!! }
}