package com.example.city.ui

import android.annotation.SuppressLint
import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.city.R
import com.example.city.data.Recommendation
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import com.example.city.data.DataSource
import androidx.compose.foundation.lazy.items

@Composable
fun CityListOnlyContent(
    cityUiState: CityUiState,
    onEmailCardPressed: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    val recommendations = cityUiState.currentRecommendationBox

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.email_list_item_vertical_spacing)
        )
    ) {
        item {
            CityHomeTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.topbar_padding_vertical))
            )
        }
        items(recommendations, key = { recommendation -> recommendation.id }) { recommendation ->
            CityEmailListItem(
                recommendation = recommendation,
                selected = false,
                onCardClick = {
                    onEmailCardPressed(recommendation)
                }
            )
        }
    }
}

@Composable
fun CityCategoryOnlyContent(
    cityUiState: CityUiState,
    onEmailCardPressed: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    val recommendations = cityUiState.currentRecommendationBox

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.email_list_item_vertical_spacing)
        )
    ) {
        item {
            CityHomeTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.topbar_padding_vertical))
            )
        }
        items(recommendations, key = { recommendation -> recommendation.id }) { recommendation ->
            CityEmailListItem(
                recommendation = recommendation,
                selected = false,
                onCardClick = {
                    onEmailCardPressed(recommendation)
                }
            )
        }
    }
}

@SuppressLint("ContextCastToActivity")
@Composable
fun CityListAndDetailContent(
    cityUiState: CityUiState,
    onEmailCardPressed: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    val recommendations = cityUiState.currentRecommendationBox
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        LazyColumn(
            contentPadding = WindowInsets.statusBars.asPaddingValues(),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(R.dimen.email_list_only_horizontal_padding)),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.email_list_item_vertical_spacing)
            )
        ) {
            items(recommendations, key = { recommendation -> recommendation.id }) { recommendation ->
                CityEmailListItem(
                    recommendation = recommendation,
                    selected = cityUiState.currentSelectedRecommendation.id == recommendation.id,
                    onCardClick = {
                        onEmailCardPressed(recommendation)
                    },
                )
            }
        }
        val activity = LocalContext.current as Activity
        CityDetailsScreen(
            cityUiState = cityUiState,
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.email_list_item_vertical_spacing))
                .weight(1f),
            onBackPressed = { activity.finish() }
        )
    }
}

@Composable
fun CityEmailListItem(
    recommendation: Recommendation,
    selected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (selected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.secondaryContainer
            }
        ),
        onClick = onCardClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.email_list_item_inner_padding))
        ) {
            CityRecommendationItemHeader(
                recommendation,
                Modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(recommendation.name),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.email_list_item_header_subject_spacing),
                    bottom = dimensionResource(R.dimen.email_list_item_subject_body_spacing)
                ),
            )
            Text(
                text = stringResource(recommendation.description),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun CityRecommendationItemHeader(recommendation: Recommendation, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        CityProfileImage(
            drawableResource = recommendation.imageUrl,
            description = stringResource(recommendation.name),
            modifier = Modifier.size(dimensionResource(R.dimen.email_header_profile_size))
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
fun CityProfileImage(
    @DrawableRes drawableResource: Int,
    description: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier.clip(CircleShape),
            painter = painterResource(drawableResource),
            contentDescription = description,
        )
    }
}

@Composable
fun CityLogo(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Image(
        painter = painterResource(R.drawable.recommendation_image1),
        contentDescription = stringResource(R.string.logo),
        colorFilter = ColorFilter.tint(color),
        modifier = modifier
    )
}

@Composable
private fun CityHomeTopBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        CityLogo(
            modifier = Modifier
                .size(dimensionResource(R.dimen.topbar_logo_size))
                .padding(start = dimensionResource(R.dimen.topbar_logo_padding_start))
        )
        CityProfileImage(
            drawableResource = DataSource.defaultRecommendation.imageUrl,
            description = stringResource(R.string.profile),
            modifier = Modifier
                .padding(end = dimensionResource(R.dimen.topbar_profile_image_padding_end))
                .size(dimensionResource(R.dimen.topbar_profile_image_size))
        )
    }
}