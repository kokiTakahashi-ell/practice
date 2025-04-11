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
import android.gesture.Prediction
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Modifier
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
    val fixedTitle = stringResource(R.string.fixed_title)
    val navController = rememberNavController()
    val fullScheduleTitle = stringResource(R.string.full_schedule)
    var topAppBarTitle by remember { mutableStateOf(fullScheduleTitle) }
    val fullSchedule by viewModel.getFullSchedule().collectAsState(emptyList())
    val onBackHandler = {
        topAppBarTitle = fullScheduleTitle//この変数を変えるとタイトルが変わる
        navController.navigateUp()
    }

    Scaffold(
        topBar = {
            BusScheduleTopAppBar(//ここをいじって上のbar部分を変更する　検索バーの追加と監視を行う　viewmodel側で監視
                title = topAppBarTitle,
                fixedTitle = fixedTitle,
                onSearchTextChange = { searchText ->
                    viewModel.updateSearchText(searchText)
//                    navController.navigate(BusScheduleScreens.AirportDetails.name)//画面遷移先変更必要
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
                val listAirport = uiState.listAirport
                AirportListScreen(
                    stateTitle = "Airport List",
                    selectedAirport = selectedAirport,
                    listAirport = listAirport,
                    contentPadding = innerPadding,
                    onRegisterFavoriteAirportClick = { departureCode, destinationCode ->
                        viewModel.viewModelScope.launch {
                            viewModel.insertFavoriteAirport(departureCode, destinationCode)
                        }
                    }
                )
            }

            composable("AirportDetails") {
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
                val favoriteList by viewModel.getFullSchedule().collectAsState(emptyList())
                Log.d(TAG, "BusScheduleApp: favoriteList: ${favoriteList.size}")
                FavoriteListScreen(
                    stateTitle = "Favorite Airports",
                    listFavorite = favoriteList,
                    onGetAirportByIataCode = { iataCode ->//iatacodeを元にairport_tableから情報を取得する
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
fun AirportListScreen(
    stateTitle: String,
    selectedAirport: Airport,//選択された空港を入れるviewmodelの中のuiStateか変数で
    listAirport: List<Airport>,//選択された空港以外の空港リスト
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
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
                        onRegisterFavoriteAirportClick?.invoke(airport.iataCode, selectedAirport.iataCode)//タップすると登録される
                    }
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
                            text = selectedAirport.iataCode,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = selectedAirport.name,
                            style = MaterialTheme.typography.bodyLarge
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
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = airport.name,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteListScreen(
    stateTitle: String,
    listFavorite: List<BusSchedule>, // favorite空港リスト
    onGetAirportByIataCode: (String) -> Airport?, // iataCodeからAirportを取得する関数
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Log.d(TAG, "FavoriteListScreen: listFavorite: ${listFavorite.size}")//入っていない
    StateTitle(stateTitle)
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
    ) {
        items(
            items = listFavorite,
            key = { favorite -> favorite.id }
        ) { favorite ->
            val departureAirport = onGetAirportByIataCode(favorite.departureCode) // 出発地の空港情報を取得
            val arrivalAirport = onGetAirportByIataCode(favorite.destinationCode) // 到着地の空港情報を取得
            Log.d(TAG, "FavoriteListScreen: departureAirport: ${departureAirport?.iataCode}")
            Log.d(TAG, "FavoriteListScreen: arrivalAirport: ${arrivalAirport?.iataCode}")

            if (departureAirport != null && arrivalAirport != null) {
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
                                text = "$departureAirport.iataCode",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "$departureAirport.name",
                                style = MaterialTheme.typography.bodyLarge
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
                                text = "$arrivalAirport.iataCode",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "$arrivalAirport.name",
                                style = MaterialTheme.typography.bodyLarge
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
//    navController: NavController
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = airport.iataCode,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = dimensionResource(R.dimen.font_large).value.sp,
                        fontWeight = FontWeight(300)
                    ),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = airport.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = dimensionResource(R.dimen.font_large).value.sp,
                        fontWeight = FontWeight(300)
                    ),
                    modifier = Modifier.weight(2f)
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
    onSearchTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var searchText by remember { mutableStateOf("") }//検索バーの文字列を保持する変数
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
            modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer)
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
            navController = rememberNavController()
        )
    }
}
