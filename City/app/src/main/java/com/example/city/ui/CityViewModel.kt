package com.example.city.ui

import android.R.attr.category
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.city.data.CategoryType
import com.example.city.data.DataSource
import com.example.city.data.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.text.category

class CityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState

    init {
        initializeUIState(categoryType = CategoryType.Shopping, dataSource = DataSource.recommendations)
    }

    private fun initializeUIState(categoryType: CategoryType, dataSource: List<Recommendation>) {
        // Initialize the UI state with the data from the DataSource
        // val category: Map<CategoryType, List<Recommendation>> =
//        val category: Map<CategoryType, List<Recommendation>> =
//            DataSource.recommendations_park.groupBy { it.category }
//        _uiState.value =
//            CityUiState(
//                category = category,
//                currentSelectedRecommendation = category[CategoryType.Shopping]?.get(0)
//                    ?: DataSource.defaultRecommendation
//            )

        val category: Map<CategoryType, List<Recommendation>> =
            dataSource.groupBy { it.category }
        val initialRecommendation = category[categoryType]?.get(0) ?: DataSource.defaultRecommendation
        val admittedCategory = CityUiState(
                category = category,
                currentSelectedRecommendation = category[categoryType]?.get(0)
                    ?: DataSource.defaultRecommendation,
//                currentRecommendationBox = category[categoryType]?:emptyList()
//                    ?: emptyMap<CategoryType, List<Recommendation>>(),
            )

        Log.d("CityViewModel", "Category: $category")
        Log.d("CityViewModel", "Initial Recommendation: $initialRecommendation")
        Log.d("CityViewModel", "inner: ${admittedCategory.currentRecommendationBox}")

        _uiState.value =
            CityUiState(
                category = category,
                currentSelectedRecommendation = initialRecommendation,
//                currentRecommendationBox = category[categoryType]?:emptyList()
            )
    }

    fun updateDetailsScreenStates(recommendation: Recommendation) {
        _uiState.update {
            it.copy(
                currentSelectedRecommendation = recommendation,
                isShowingCategoryPage = false
            )
        }
    }

    fun updateCategoryScreenStatesToPark() {
        val category: Map<CategoryType, List<Recommendation>> =
            DataSource.recommendations_park.groupBy { it.category }
        val new =  lazy { category[CategoryType.Park]!! }
        _uiState.update {
            it.copy(
                category = category,
                currentCategory = CategoryType.Park,
                isShowingCategoryPage = true,
//                currentRecommendationBox = category[CategoryType.Park]?:emptyList()
            )

        }
//        initializeUIState(categoryType = CategoryType.Park, dataSource = DataSource.recommendations_park)
//        updateCurrentCategory(CategoryType.Park)
    }

    fun updateCategoryScreenStatesToRestaurant() {
        val category: Map<CategoryType, List<Recommendation>> =
            DataSource.recommendations_park.groupBy { it.category }
        _uiState.update {
            it.copy(
                category = category,
                currentCategory = CategoryType.Restaurant,
                isShowingCategoryPage = true,
//                currentRecommendationBox = category[CategoryType.Restaurant]?:emptyList()
            )
        }
//        initializeUIState(categoryType = CategoryType.Restaurant, dataSource = DataSource.recommendations_restaurant)
//        updateCurrentCategory(CategoryType.Restaurant)
    }


    fun resetHomeScreenStates() {
        _uiState.update {
            it.copy(
                currentSelectedRecommendation = it.category[it.currentCategory]?.get(0)
                    ?: DataSource.defaultRecommendation,
                isShowingCategoryPage = true,
            )
        }
    }

    fun updateCurrentCategory(categoryType: CategoryType) {
        val category: Map<CategoryType, List<Recommendation>> =
            DataSource.recommendations_park.groupBy { it.category }
        _uiState.update {
            it.copy(
                currentCategory = categoryType,
//                currentRecommendationBox = category[categoryType]?:emptyList(),
            )
        }
    }
}