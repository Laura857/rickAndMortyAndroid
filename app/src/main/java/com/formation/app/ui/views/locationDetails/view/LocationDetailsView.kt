package com.formation.app.ui.views.locationDetails.view

import CustomPersonageLittleCardComponent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.formation.app.common.ui.theme.MainGreen
import com.formation.app.ui.views.locationDetails.viewModel.LocationDetailsViewModel
import com.formation.app.ui.views.navigation.AppViewState

@Composable
fun LocationDetailsView(
    navController: NavHostController,
    id: String,
    locationDetailsViewModel: LocationDetailsViewModel = hiltViewModel(),
) {
    val location by locationDetailsViewModel.location.collectAsState()
    val residents by locationDetailsViewModel.residents.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        locationDetailsViewModel.fetchLocation(id)
    })

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .padding(15.dp)
    ) {
        Text(
            text = "${location?.name}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.MainGreen,
            textAlign = TextAlign.Center
        )
        Text("Type ${location?.type}")
        Text("Dimension: ${location?.dimension}")

        Text("Residents",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.MainGreen,
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(top = 20.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            // content padding
            contentPadding = PaddingValues(20.dp),
            content = {
                items(residents) {
                    CustomPersonageLittleCardComponent(
                        it.name,
                        it.gender,
                        it.image) { navController.navigate(AppViewState.PersonageDetails.route + "?id=${it.id}") }
                }
            }
        )
    }
}