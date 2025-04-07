package com.example.city.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.city.utils.CityContentType
import com.example.city.utils.CityNavigationType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.city.data.CategoryType
import com.example.city.data.Recommendation

@Composable
fun CityApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val navigationType: CityNavigationType
    val viewModel: CityViewModel = viewModel()
    val cityUiState = viewModel.uiState.collectAsState().value
    val contentType: CityContentType

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = CityNavigationType.BOTTOM_NAVIGATION
            contentType = CityContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = CityNavigationType.NAVIGATION_RAIL
            contentType = CityContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = CityNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = CityContentType.LIST_AND_DETAIL
        }
        else -> {
            navigationType = CityNavigationType.BOTTOM_NAVIGATION
            contentType = CityContentType.LIST_ONLY
        }
    }
    CityHomeScreen(
        navigationType = navigationType,
        contentType = contentType,
        cityUiState = cityUiState,
        onTabPressed = { categoryType: CategoryType ->
            viewModel.updateCurrentCategory(categoryType = categoryType)
            viewModel.resetHomeScreenStates()
        },
        onEmailCardPressed = { recommendation: Recommendation ->
            viewModel.updateDetailsScreenStates(
                recommendation = recommendation
            )
        },
        onDetailScreenBackPressed = {
            viewModel.resetHomeScreenStates()
        },
        onMoveToParks = {
            viewModel.updateCategoryScreenStatesToPark()
        },
        onMoveToRestaurants = {
            viewModel.updateCategoryScreenStatesToRestaurant()
        },
        modifier = modifier
    )
}