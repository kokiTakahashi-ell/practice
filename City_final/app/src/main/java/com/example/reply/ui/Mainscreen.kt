package com.example.reply.ui

import android.R.attr.onClick
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Drafts
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.reply.R
import com.example.reply.data.Email
import com.example.reply.data.MailboxType
import com.example.reply.data.local.LocalAccountsDataProvider
import com.example.reply.ui.utils.ReplyContentType
import com.example.reply.ui.utils.ReplyNavigationType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySelectionScreen(
    onTabPressed: ((MailboxType) -> Unit),
    onStartScreen: () -> Unit,
    offStartScreen: () -> Unit,
) {
    val categories = listOf(
        MailboxType.Inbox,
        MailboxType.Sent,
        MailboxType.Drafts
    )
    var selected = false

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
//        Text("カテゴリを選択", style = MaterialTheme.typography.headlineMedium)
        ReplyMainTopBar(modifier = Modifier.padding(top = 24.dp))
        Spacer(modifier = Modifier.height(16.dp))


        categories.forEach { category ->
//            Button(
//                onClick = {
//                    onTabPressed(category)
//                    offStartScreen()
//                          },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp)
//            ) {
//                Text(text = category.name)
//            }
            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (selected) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.secondaryContainer
                    }
                ),
                onClick = {
                    onTabPressed(category)
                    offStartScreen()
                    selected = true
                }
            ) {
                Row (
                    modifier = Modifier,
                ){
                    ReplyProfileImage(
                        drawableResource = LocalAccountsDataProvider.defaultAccount.avatar,
                        description = stringResource(R.string.profile),
                        modifier = Modifier
                            .padding(end = dimensionResource(R.dimen.topbar_profile_image_padding_end))
                            .size(dimensionResource(R.dimen.topbar_profile_image_size))
                    )
                    Column(
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.email_list_item_inner_padding))
                    ) {
                        Text(
                            text = "${category.name}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(
                                top = dimensionResource(R.dimen.email_list_item_header_subject_spacing),
                                bottom = dimensionResource(R.dimen.email_list_item_subject_body_spacing)
                            ),
                        )
                    }
                }

            }
        }
    }
}


@Composable
private fun ReplyMainTopBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        ReplyLogo(
            modifier = Modifier
                .size(dimensionResource(R.dimen.topbar_logo_size))
                .padding(start = dimensionResource(R.dimen.topbar_logo_padding_start))
        )
        Text(
            text = "My City",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(start = dimensionResource(R.dimen.topbar_logo_padding_start))
                .weight(1f)
                .wrapContentWidth()
        )
        ReplyProfileImage(
            drawableResource = LocalAccountsDataProvider.defaultAccount.avatar,
            description = stringResource(R.string.profile),
            modifier = Modifier
                .padding(end = dimensionResource(R.dimen.topbar_profile_image_padding_end))
                .size(dimensionResource(R.dimen.topbar_profile_image_size))
        )
    }
}

