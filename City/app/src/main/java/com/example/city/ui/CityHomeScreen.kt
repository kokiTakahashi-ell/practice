package com.example.city.ui

import android.R.attr.description
import android.nfc.Tag
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Drafts
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Report
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.city.R
import com.example.city.data.CategoryType
import com.example.city.data.DataSource
import com.example.city.data.Recommendation
import com.example.city.utils.CityContentType
import com.example.city.utils.CityNavigationType

@Composable
fun CityHomeScreen(
    navigationType: CityNavigationType,
    contentType: CityContentType,
    cityUiState: CityUiState,
    onTabPressed: (CategoryType) -> Unit,
    onEmailCardPressed: (Recommendation) -> Unit,
    onDetailScreenBackPressed: () -> Unit,
    onMoveToParks: () -> Unit,
    onMoveToRestaurants: () -> Unit,
    modifier: Modifier = Modifier
) {

    val navigationItemContentList = listOf(
        NavigationItemContent(
            categoryType = CategoryType.Shopping,
            icon = Icons.Default.Inbox,
            text = "shopping"
        ),
        NavigationItemContent(
            categoryType = CategoryType.Park,
            icon = Icons.AutoMirrored.Filled.Send,
            text = "park"
        ),
        NavigationItemContent(
            categoryType = CategoryType.Restaurant,
            icon = Icons.Default.Drafts,
            text = "restaurants"
        ),
    )
    if (navigationType == CityNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            modifier = Modifier.testTag(stringResource(R.string.navigation_drawer)),
            drawerContent = {
                PermanentDrawerSheet(Modifier.width(dimensionResource(R.dimen.drawer_width))) {
                    NavigationDrawerContent(
                        selectedDestination = cityUiState.currentCategory,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier
                            .wrapContentWidth()
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                            .padding(dimensionResource(R.dimen.drawer_padding_content))
                    )
                }
            }
        ) {
            CityAppContent(
                navigationType = navigationType,
                contentType = contentType,
                cityUiState = cityUiState,
                onTabPressed = onTabPressed,
                onEmailCardPressed = onEmailCardPressed,
                navigationItemContentList = navigationItemContentList,
                onMoveToParks = onMoveToParks,
                onMoveToRestaurants = onMoveToRestaurants,
                modifier = modifier,
            )
        }
    } else {
        if (cityUiState.isShowingCategoryPage) {
            CityAppContent(
                navigationType = navigationType,
                contentType = contentType,
                cityUiState = cityUiState,
                onTabPressed = onTabPressed,
                onEmailCardPressed = onEmailCardPressed,
                onMoveToParks = onMoveToParks,
                onMoveToRestaurants = onMoveToRestaurants,
                navigationItemContentList = navigationItemContentList,
                modifier = modifier,
            )
        } else {
            CityDetailsScreen(
                cityUiState = cityUiState,
                isFullScreen = true,
                onBackPressed = onDetailScreenBackPressed,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun CityCategoryContent(
    navigationType: CityNavigationType,
    contentType: CityContentType,
    cityUiState: CityUiState,
    onTabPressed: ((CategoryType) -> Unit),
    onEmailCardPressed: (Recommendation) -> Unit,
    onMoveToParks: () -> Unit,
    onMoveToRestaurants: () -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier)

    {
        Row(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = navigationType == CityNavigationType.NAVIGATION_RAIL) {
                val navigationRailContentDescription = stringResource(R.string.navigation_rail)
                CityNavigationRail(
                    currentTab = cityUiState.currentCategory,
                    onTabPressed = onTabPressed,
                    navigationItemContentList = navigationItemContentList,
                    modifier = Modifier
                )
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                if (contentType == CityContentType.LIST_AND_DETAIL) {
                    CityListAndDetailContent(
                        cityUiState = cityUiState,
                        onEmailCardPressed = onEmailCardPressed,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    CityListOnlyContent(
                        cityUiState = cityUiState,
                        onEmailCardPressed = onEmailCardPressed,
                        modifier = Modifier.weight(1f)
                            .padding(
                                horizontal = dimensionResource(R.dimen.email_list_only_horizontal_padding)
                            )
                    )
                }
//                AnimatedVisibility(visible = navigationType == CityNavigationType.BOTTOM_NAVIGATION) {
//                    CityBottomNavigationBar(
//                        cityUiState = cityUiState,
//                        currentTab = cityUiState.currentCategory,
//                        onTabPressed = onTabPressed,
//                        onMoveToParks = onMoveToParks,
//                        onMoveToRestaurants = onMoveToRestaurants,
//                        navigationItemContentList = navigationItemContentList,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                    )
//                }
            }
        }
    }
}


@Composable
private fun CityAppContent(
    navigationType: CityNavigationType,
    contentType: CityContentType,
    cityUiState: CityUiState,
    onTabPressed: ((CategoryType) -> Unit),
    onEmailCardPressed: (Recommendation) -> Unit,
    onMoveToParks: () -> Unit,
    onMoveToRestaurants: () -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier)

    {
        Row(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = navigationType == CityNavigationType.NAVIGATION_RAIL) {
                val navigationRailContentDescription = stringResource(R.string.navigation_rail)
                CityNavigationRail(
                    currentTab = cityUiState.currentCategory,
                    onTabPressed = onTabPressed,
                    navigationItemContentList = navigationItemContentList,
                    modifier = Modifier
                )
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                if (contentType == CityContentType.LIST_AND_DETAIL) {
                    CityListAndDetailContent(
                        cityUiState = cityUiState,
                        onEmailCardPressed = onEmailCardPressed,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    CityListOnlyContent(
                        cityUiState = cityUiState,
                        onEmailCardPressed = onEmailCardPressed,
                        modifier = Modifier.weight(1f)
                            .padding(
                                horizontal = dimensionResource(R.dimen.email_list_only_horizontal_padding)
                            )
                    )
                }
                AnimatedVisibility(visible = navigationType == CityNavigationType.BOTTOM_NAVIGATION) {
                    CityBottomNavigationBar(
                        cityUiState = cityUiState,
                        currentTab = cityUiState.currentCategory,
                        onTabPressed = onTabPressed,
                        onMoveToParks = onMoveToParks,
                        onMoveToRestaurants = onMoveToRestaurants,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun CityNavigationRail(
    currentTab: CategoryType,
    onTabPressed: ((CategoryType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationRailItem(
                selected = currentTab == navItem.categoryType,
                onClick = { onTabPressed(navItem.categoryType) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                }
            )
        }
    }
}
private const val TAG = "MyApp"
@Composable
private fun CityBottomNavigationBar(
    cityUiState: CityUiState,
    currentTab: CategoryType,
    onTabPressed: ((CategoryType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    onMoveToParks: () -> Unit,
    onMoveToRestaurants: () -> Unit,
    modifier: Modifier = Modifier
) {

    Log.d(TAG, "currentTab: $currentTab")
    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentTab == navItem.categoryType,
                onClick = {
                    onTabPressed(navItem.categoryType)
                    Log.d(TAG, "push")
                    onMoveToParks()
                    onMoveToRestaurants()
                          },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                }
            )
        }
    }
}

@Composable
private fun NavigationDrawerContent(
    selectedDestination: CategoryType,
    onTabPressed: ((CategoryType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        NavigationDrawerHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.profile_image_padding)),
        )
        for (navItem in navigationItemContentList) {
            NavigationDrawerItem(
                selected = selectedDestination == navItem.categoryType,
                label = {
                    Text(
                        text = navItem.text,
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.drawer_padding_header))
                    )
                },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                ),
                onClick = { onTabPressed(navItem.categoryType) }
            )
        }
    }
}

@Composable
private fun NavigationDrawerHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CityLogo(modifier = Modifier.size(dimensionResource(R.dimen.reply_logo_size)))
        CityProfileImage(
            drawableResource = DataSource.defaultRecommendation.imageUrl,
            description = stringResource(id = R.string.profile),
            modifier = Modifier.size(dimensionResource(R.dimen.profile_image_size))
        )
    }
}

private data class NavigationItemContent(
    val categoryType: CategoryType,
    val icon: ImageVector,
    val text: String
)