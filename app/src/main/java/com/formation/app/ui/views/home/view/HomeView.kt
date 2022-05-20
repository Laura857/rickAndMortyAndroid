package com.formation.app.ui.views.home.view

import CustomPersonageCardComponent
import CustomSearchBarComponent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.formation.app.ui.views.home.viewModel.HomeViewModel
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
import com.formation.app.ui.views.navigation.AppViewState

@Composable
fun HomeView(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val personages by homeViewModel.personagesFound.collectAsState()
    val name by homeViewModel.name.collectAsState()
    val isSearch by homeViewModel.isSearch.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        homeViewModel.fetchPersonages()
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
            setFunction = homeViewModel::setName,
            searchFunction = homeViewModel::searchByName
        )

        if (personages.isEmpty() && isSearch) {
            Text("Oups This personage doesn't exists")
        } else {
            LazyColumn {
                items(personages) {
                    CustomPersonageCardComponent(
                        it.name,
                        it.image,
                        it.status,
                        it.origin.name
                    ) { navController.navigate(AppViewState.PersonageDetails.route + "?id=${it.id}") }
                }
            }
        }
    }
}