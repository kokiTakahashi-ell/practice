package com.example.city.ui

import android.R.attr.category
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.city.R
import com.example.city.data.CategoryType
import com.example.city.data.CityType
import com.example.city.data.Recommendation
import androidx.compose.material3.Button

@Composable
fun CityDetailsScreen(
    cityUiState: CityUiState,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false,
) {
    BackHandler {
        onBackPressed()
    }
    Box(modifier = modifier) {
        LazyColumn(
            contentPadding = WindowInsets.safeDrawing.asPaddingValues(),
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            item {
                if (isFullScreen) {
                    CityDetailsScreenTopBar(
                        onBackPressed,
                        cityUiState,
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = dimensionResource(R.dimen.detail_topbar_padding_bottom),
                                top = dimensionResource(R.dimen.topbar_padding_vertical)
                            )
                    )
                }
                CityEmailDetailsCard(
                    recommendation = cityUiState.currentSelectedRecommendation,
                    categoryType = cityUiState.currentCategory,
                    isFullScreen = isFullScreen,
                    modifier = if (isFullScreen) {
                        Modifier.padding(horizontal = dimensionResource(R.dimen.detail_card_outer_padding_horizontal))
                    } else {
                        Modifier.padding(end = dimensionResource(R.dimen.detail_card_outer_padding_horizontal))
                    }
                )
            }
        }
    }
}

@Composable
private fun CityDetailsScreenTopBar(
    onBackButtonClicked: () -> Unit,
    cityUiState: CityUiState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onBackButtonClicked,
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.detail_topbar_back_button_padding_horizontal))
                .background(MaterialTheme.colorScheme.surface, shape = CircleShape),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.navigation_back)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = dimensionResource(R.dimen.detail_subject_padding_end))
        ) {
            Text(
                text = stringResource(cityUiState.currentSelectedRecommendation.name),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CityEmailDetailsCard(
    recommendation: Recommendation,
    categoryType: CategoryType,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false,
) {
    val context = LocalContext.current
    val displayToast = { text: String ->
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.detail_card_inner_padding))
        ) {
            DetailsScreenHeader(
                recommendation,
                Modifier.fillMaxWidth()
            )
            if (isFullScreen) {
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.detail_content_padding_top)))
            } else {
                Text(
                    text = stringResource(recommendation.name),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(
                        top = dimensionResource(R.dimen.detail_content_padding_top),
                        bottom = dimensionResource(R.dimen.detail_expanded_subject_body_spacing)
                    ),
                )
            }
            Text(
                text = stringResource(recommendation.description),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

//@Composable
//private fun DetailsScreenButtonBar(
//    categoryType: CategoryType,
//    displayToast: (String) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Box(modifier = modifier) {
//        when (categoryType) {
//            CategoryType.Drafts ->
//                ActionButton(
//                    text = stringResource(id = R.string.continue_composing),
//                    onButtonClicked = displayToast
//                )
//
//            CategoryType.Spam ->
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(
//                            vertical = dimensionResource(R.dimen.detail_button_bar_padding_vertical)
//                        ),
//                    horizontalArrangement = Arrangement.spacedBy(
//                        dimensionResource(R.dimen.detail_button_bar_item_spacing)
//                    ),
//                ) {
//                    ActionButton(
//                        text = stringResource(id = R.string.move_to_inbox),
//                        onButtonClicked = displayToast,
//                        modifier = Modifier.weight(1f)
//                    )
//                    ActionButton(
//                        text = stringResource(id = R.string.delete),
//                        onButtonClicked = displayToast,
//                        containIrreversibleAction = true,
//                        modifier = Modifier.weight(1f)
//                    )
//                }
//
//            CategoryType.Restaurant, CategoryType.Park ->
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(
//                            vertical = dimensionResource(R.dimen.detail_button_bar_padding_vertical)
//                        ),
//                    horizontalArrangement = Arrangement.spacedBy(
//                        dimensionResource(R.dimen.detail_button_bar_item_spacing)
//                    ),
//                ) {
//                    ActionButton(
//                        text = stringResource(id = R.string.city),
//                        onButtonClicked = displayToast,
//                        modifier = Modifier.weight(1f)
//                    )
//                    ActionButton(
//                        text = stringResource(id = R.string.city_all),
//                        onButtonClicked = displayToast,
//                        modifier = Modifier.weight(1f)
//                    )
//                }
//        }
//    }
//}

@Composable
private fun DetailsScreenHeader(recommendation: Recommendation, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        CityProfileImage(
            drawableResource = recommendation.imageUrl,
            description = stringResource(recommendation.name),
            modifier = Modifier.size(
                dimensionResource(R.dimen.email_header_profile_size)
            )
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = dimensionResource(R.dimen.email_header_content_padding_horizontal),
                    vertical = dimensionResource(R.dimen.email_header_content_padding_vertical)
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(recommendation.name),
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = stringResource(recommendation.name),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Composable
private fun ActionButton(
    text: String,
    onButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    containIrreversibleAction: Boolean = false,
) {
    Box(modifier = modifier) {
        Button(
            onClick = { onButtonClicked(text) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.detail_action_button_padding_vertical)),
            colors = ButtonDefaults.buttonColors(
                containerColor =
                    if (containIrreversibleAction) {
                        MaterialTheme.colorScheme.onErrorContainer
                    } else {
                        MaterialTheme.colorScheme.primaryContainer
                    }
            )
        ) {
            Text(
                text = text,
                color =
                    if (containIrreversibleAction) {
                        MaterialTheme.colorScheme.onError
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
            )
        }
    }
}

//@Composable
//fun Button(
//    onClick: () -> Unit,
//    modifier: Modifier,
//    colors: ButtonColors,
//    content: @Composable () -> Unit
//) {
//    TODO("Not yet implemented")
//}
