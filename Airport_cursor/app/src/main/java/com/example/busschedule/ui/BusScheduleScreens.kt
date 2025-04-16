/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.busschedule.ui

import android.R.attr.name
import android.R.attr.onClick
import android.database.sqlite.SQLiteDatabase.deleteDatabase
import android.gesture.Prediction
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.busschedule.R
import com.example.busschedule.data.airport.Airport
import com.example.busschedule.data.bus.BusSchedule
import com.example.busschedule.ui.theme.BusScheduleTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class BusScheduleScreens {
    FavoriteListScreen,
    AirportListScreen,
    AirportDetails,
}
var TAG = "test"
@Composable
fun BusScheduleApp(
    viewModel: BusScheduleViewModel = viewModel(factory = BusScheduleViewModel.factory)
) {
    // 再コンポーズの回数を追跡するためのカウンター
    val recompositionCount = remember { mutableStateOf(0) }
    recompositionCount.value++
    
    // 再コンポーズの回数をログに出力
    LaunchedEffect(Unit) {
        Log.d("Recomposition", "BusScheduleApp recomposed ${recompositionCount.value} times")
    }
    
    val fixedTitle = stringResource(R.string.fixed_title)
    val navController = rememberNavController()
    val fullScheduleTitle = stringResource(R.string.full_schedule)
    var topAppBarTitle by remember { mutableStateOf(fullScheduleTitle) }
    val state = viewModel.searchUiState.collectAsState().value
    val searchText = state.textSearch
//    viewModel.deleteDatabase(LocalContext.current, "bus_schedule")

    Scaffold(
        topBar = {
            BusScheduleTopAppBar(//ここをいじって上のbar部分を変更する　検索バーの追加と監視を行う　viewmodel側で監視
                title = topAppBarTitle,
                fixedTitle = fixedTitle,
                searchText = searchText,
                onSearchTextChange = { searchText ->
                    viewModel.updateSearchText(searchText)
                },
                modifier = Modifier,
                navController = navController
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BusScheduleScreens.FavoriteListScreen.name
        ) {
            composable("AirportListScreen") {
                val uiState = viewModel.searchUiState.collectAsState().value
                val selectedAirport = uiState.selectedAirport ?: Airport(
                    iataCode = "",
                    name = "",
                    passengers = 0,
                    id = 0
                )//初期値の代入
                topAppBarTitle = "Flight from ${selectedAirport.iataCode}"//題名のセット
                val listAirport = uiState.listAirport
                AirportListScreen(
                    stateTitle = "Airport List",
                    selectedAirport = selectedAirport,
                    listAirport = listAirport,
                    contentPadding = innerPadding,
                    isFavorite = { departureCode, destinationCode ->
                        var exist : Boolean = false
                        viewModel.viewModelScope.launch {
                            exist = viewModel.existsByFavorite(departureCode, destinationCode).first()
                        }
                        exist
                    },
                    onAddFavorite = { departureCode, destinationCode ->
                        viewModel.viewModelScope.launch {
                            viewModel.insertFavoriteAirport(departureCode, destinationCode)
                        }
                    },
                    onRemoveFavorite = { departureCode, destinationCode ->
                        viewModel.viewModelScope.launch {
                            viewModel.deleteByDepartureAndDestination(departureCode, destinationCode)
                        }
                    },
                    onRegisterFavoriteAirportClick = { departureCode, destinationCode ->
                        viewModel.viewModelScope.launch {
                            viewModel.insertFavoriteAirport(departureCode, destinationCode)
                            Log.d(TAG, "BusScheduleApp: insertFavoriteAirport: $departureCode, $destinationCode")
                        }
                    }
                )
            }

            composable("AirportDetails") {
                topAppBarTitle = "Airport Details"
                val uiState = viewModel.searchUiState.collectAsState().value
                val searchingText = uiState.textSearch
                val airportList = uiState.listAirport
                val airportDetails by viewModel.getSearchAirports(searchingText).collectAsState(emptyList())
                AirportDetails(
                    stateTitle = "Airport Details",
                    airports = airportDetails,
                    contentPadding = innerPadding,
                    onAirportClick = { clickedAirport ->
                        viewModel.updateSelectedAirport(
                            airportDetails.firstOrNull { it == clickedAirport } ?: Airport(
                                iataCode = "",
                                name = "",
                                passengers = 0,
                                id = 0
                            )
                        )
                        viewModel.updateListAirport(clickedAirport)
                        navController.navigate(BusScheduleScreens.AirportListScreen.name)
                    }
                )
            }

            composable("FavoriteListScreen") {
                topAppBarTitle = "Favorite Airport List"
                viewModel.updateFavoriteAirport()
                val uiState = viewModel.searchUiState.collectAsState().value
                val favoriteList by viewModel.getFullSchedule().collectAsState(emptyList())
                
                // LaunchedEffect(Unit) {
                //     viewModel.updateFavoriteAirport()
                // }
                
                val departureAirport = uiState.favoriteDepartureAirport
                val destinationAirport = uiState.favoriteDestinationAirport
                
                FavoriteListScreen(
                    stateTitle = "Favorite Airports",
                    listFavorite = favoriteList,
                    departureAirport = departureAirport,
                    destinationAirport = destinationAirport,
                    onGetAirportByIataCode = { iataCode ->
                        var airport: Airport? = null
                        viewModel.viewModelScope.launch {
                            viewModel.getIataAirport(iataCode).collect { result ->
                                airport = result
                            }
                        }
                        airport
                    },
                    contentPadding = innerPadding
                )
            }
        }
    }
}

@Composable
fun StarIcon(
    isFavorite: Boolean, // 初期状態を受け取る
    onAddFavorite: () -> Unit, // 登録処理
    onRemoveFavorite: () -> Unit, // 削除処理
    modifier: Modifier = Modifier
) {
    var isSelected by remember { mutableStateOf(isFavorite) } // 初期状態を設定
    IconButton(onClick = {
        isSelected = !isSelected
        if (isSelected) {
            onAddFavorite() // 登録処理を実行
        } else {
            onRemoveFavorite() // 削除処理を実行
        }
    }) {
        Icon(
            imageVector = if (isSelected) Icons.Filled.Star else Icons.Outlined.Star,
            contentDescription = if (isSelected) "Selected Star" else "Unselected Star",
            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun AirportListScreen(
    modifier: Modifier = Modifier,
    stateTitle: String,
    selectedAirport: Airport,//選択された空港を入れるviewmodelの中のuiStateか変数で
    listAirport: List<Airport>,//選択された空港以外の空港リスト
    isFavorite : ((String, String) -> Boolean)? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onRemoveFavorite: ((String, String) -> Unit)? = null,//favoriteから削除トリガー
    onAddFavorite: ((String, String) -> Unit)? = null,//favoriteに登録トリガー
    onRegisterFavoriteAirportClick: ((String, String) -> Unit)? = null//favoriteに登録トリガー
) {
    Log.d(TAG, "AirportListScreen: selectedAirport: ${selectedAirport.iataCode}")
    Log.d(TAG, "AirportListScreen: listAirport: ${listAirport.size}")
    StateTitle(stateTitle)
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
    ) {
        items(
            items = listAirport,
            key = { airport -> airport.id }
        ) { airport ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .clickable(enabled = onRegisterFavoriteAirportClick != null) {
                        onRegisterFavoriteAirportClick?.invoke(selectedAirport.iataCode, airport.iataCode)//タップすると登録される
                        Log.d(TAG, "AirportListScreen: tap!!!!")
                    }
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Column(
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.padding_medium))
                            .weight(0.7f)
                    ) {
                        Text(
                            text = "Depart",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = selectedAirport.iataCode,
                                style = MaterialTheme.typography.bodyLarge.copy(),
                                textAlign = TextAlign.Left,
                                modifier = Modifier.weight(0.2f)
                            )
                            Spacer(modifier.weight(0.1f))
                            Text(
                                text = selectedAirport.name,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Text(
                            text = "Arrive",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = airport.iataCode,
                                style = MaterialTheme.typography.bodyLarge.copy(),
                                textAlign = TextAlign.Left,
                                modifier = Modifier.weight(0.2f)
                            )
                            Spacer(modifier.weight(0.1f))
                            Text(
                                text = airport.name,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    StarIcon(
                        isFavorite = isFavorite?.invoke(selectedAirport.iataCode, airport.iataCode) ?:false,
                        onAddFavorite = {onAddFavorite?.invoke(selectedAirport.iataCode, airport.iataCode)},
                        onRemoveFavorite = {onRemoveFavorite?.invoke(selectedAirport.iataCode, airport.iataCode)},
                        modifier = modifier
                            .weight(0.3f)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

@Composable
fun FavoriteListScreen(
    stateTitle: String,
    listFavorite: List<BusSchedule>, // favorite空港リスト
    departureAirport: List<Airport>, // 出発地の空港リスト
    destinationAirport: List<Airport>, // 到着地の空港リスト
    onGetAirportByIataCode: (String) -> Airport?, // iataCodeからAirportを取得する関数
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    // 再コンポーズの回数を追跡するためのカウンター
    val recompositionCount = remember { mutableStateOf(0) }
    recompositionCount.value++
    
    // 再コンポーズの回数をログに出力
    LaunchedEffect(Unit) {
        Log.d("Recomposition", "FavoriteListScreen recomposed ${recompositionCount.value} times")
    }
    
    Log.d(TAG, "FavoriteListScreen: listFavorite: ${listFavorite.size}")
    StateTitle(stateTitle)
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
    ) {
        items(
            items = listFavorite.zip(departureAirport.zip(destinationAirport)) { favorite, (departure, destination) ->
                Triple(favorite, departure, destination)
            },
            key = { (favorite, _, _) -> favorite.id }
        ) { (favorite, departure, destination)  ->
            // 空港情報を保持するState

            if (departure != null && destination != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_medium))
                ) {
                    Column(
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
                    ) {
                        Text(
                            text = "Depart",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = departure.iataCode,
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.weight(0.2f)
                            )
                            Spacer(modifier.weight(0.1f))
                            Text(
                                text = departure.name,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Text(
                            text = "Arrive",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = destination.iataCode,
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.weight(0.2f)
                            )
                            Spacer(modifier.weight(0.1f))
                            Text(
                                text = destination.name,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun AirportDetails(
    stateTitle: String,
    airports: List<Airport>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onAirportClick: ((Airport) -> Unit)? = null,
) {

    StateTitle(stateTitle)
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
    ) {

        items(
            items = airports,
            key = { airport -> airport.id }
        ) { airport ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = onAirportClick != null) {
                        onAirportClick?.invoke(airport)
                    }
                    .padding(dimensionResource(R.dimen.padding_medium)),
            ) {
                Text(
                    text = airport.iataCode,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = dimensionResource(R.dimen.font_large).value.sp,
                        fontWeight = FontWeight(300)
                    ),
                    modifier = Modifier.weight(0.2f),
                    textAlign = TextAlign.Left
                )
                Spacer(modifier.weight(0.1f))
                Text(
                    text = airport.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = dimensionResource(R.dimen.font_large).value.sp,
                        fontWeight = FontWeight(300)
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun StateTitle(title: String){
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge.copy(
            fontSize = dimensionResource(R.dimen.font_large).value.sp,
            fontWeight = FontWeight(300)
        ),
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusScheduleTopAppBar(
    title: String,
    fixedTitle : String,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    // 再コンポーズの回数を追跡するためのカウンター
    val recompositionCount = remember { mutableStateOf(0) }
    recompositionCount.value++
    
    // 再コンポーズの回数をログに出力
    LaunchedEffect(Unit) {
        Log.d("Recomposition", "BusScheduleTopAppBar recomposed ${recompositionCount.value} times")
    }
    
    var searchText = searchText
    LaunchedEffect(searchText) {
        if (searchText.isEmpty()){
            navController.navigate(BusScheduleScreens.FavoriteListScreen.name)
        } else {
            navController.navigate(BusScheduleScreens.AirportDetails.name)
        }
    }
    Column(modifier = modifier) {
        TopAppBar(
            title = { Text(fixedTitle) },
            modifier = modifier
                .background(MaterialTheme.colorScheme.onSecondary)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = searchText,
                onValueChange = { newText ->
                    searchText = newText
                    onSearchTextChange(newText)//検索欄の文字列をviewmodelにセット 画面遷移
                },
                placeholder = { Text("検索...") },
                modifier = Modifier.fillMaxWidth(0.9f)
            )
//    }
        }
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = dimensionResource(R.dimen.font_large).value.sp,
                fontWeight = FontWeight(300)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(dimensionResource(R.dimen.padding_medium)),
            textAlign = TextAlign.Left
        )

    }
}



@Preview(showBackground = true)
@Composable
fun BusScheduleTopAppBarPreview() {
    BusScheduleTheme {
        BusScheduleTopAppBar(
            title = "Sample Title",
            fixedTitle = "Fixed Title",
            onSearchTextChange = { /* 検索テキスト変更時の処理 */ },
            navController = rememberNavController(),
            modifier = Modifier.padding(16.dp),
            searchText = "Sample Search Text"
        )
    }
}
