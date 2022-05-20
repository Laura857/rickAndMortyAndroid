package com.formation.app.ui.views.location.view

import CustomCardComponent
import CustomSearchBarComponent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.formation.app.ui.views.location.viewModel.LocationViewModel
import com.formation.app.ui.views.navigation.AppViewState

@Composable
fun LocationView(
    navController: NavHostController,
    locationViewModel: LocationViewModel = hiltViewModel(),
) {
    val locations by locationViewModel.locationsFound.collectAsState()
    val name by locationViewModel.name.collectAsState()
    val isSearch by locationViewModel.isSearch.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        locationViewModel.fetchLocations()
    })

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CustomSearchBarComponent(
            value = name,
            title = "Name",
            placeholder = "name",
            hidden = false,
            icon = Icons.Default.Search,
            modifier = Modifier
                .padding(10.dp)
                .size(height = 60.dp, width = 250.dp),
            setFunction = locationViewModel::setName,
            searchFunction = locationViewModel::searchByName
        )

        if (locations.isEmpty() && isSearch) {
            Text("Oups This location doesn't exists")
        } else {
            LazyColumn {
                items(locations) {
                    CustomCardComponent(
                        it.name,
                        null,
                        it.type,
                    ) { navController.navigate(AppViewState.LocationDetails.route + "?id=${it.id}") }
                }
            }
        }
    }
}