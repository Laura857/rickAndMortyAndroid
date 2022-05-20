package com.formation.app.ui.views.episodeDetails.view

import CustomPersonageLittleCardComponent
import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.formation.app.ui.views.episodeDetails.viewDetails.EpisodeDetailsViewModel
import com.formation.app.ui.views.navigation.AppViewState

@Composable
fun EpisodeDetailsView(
    navController: NavHostController,
    id: String,
    episodeDetailsViewModel: EpisodeDetailsViewModel = hiltViewModel(),
) {
    val episode by episodeDetailsViewModel.episode.collectAsState()
    val personages by episodeDetailsViewModel.personages.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        episodeDetailsViewModel.fetchEpisodes(id)
    })

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Text(
            text = "${episode?.name}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.MainGreen,
            textAlign = TextAlign.Center
        )
        Text("Air date : ${episode?.air_date}")
        Text("Episode : ${episode?.episode}")

        Text("Characters",
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
                items(personages) {
                    CustomPersonageLittleCardComponent(
                        it.name,
                        it.gender,
                        it.image) { navController.navigate(AppViewState.PersonageDetails.route + "?id=${it.id}") }
                }
            }
        )
    }
}