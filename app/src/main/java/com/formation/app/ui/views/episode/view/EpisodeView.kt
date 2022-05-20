package com.formation.app.ui.views.episode.view

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
import com.formation.app.ui.views.episode.viewModel.EpisodeViewModel
import com.formation.app.ui.views.navigation.AppViewState

@Composable
fun EpisodeView(
    navController: NavHostController,
    episodeViewModel: EpisodeViewModel = hiltViewModel(),
) {
    val episodes by episodeViewModel.episodesFound.collectAsState()
    val name by episodeViewModel.name.collectAsState()
    val isSearch by episodeViewModel.isSearch.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        episodeViewModel.fetchEpisodes()
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
            setFunction = episodeViewModel::setName,
            searchFunction = episodeViewModel::searchByName
        )

        if (episodes.isEmpty() && isSearch) {
            Text("Oups This episode doesn't exists")
        } else {
            LazyColumn {
                items(episodes) {
                    CustomCardComponent(
                        it.name,
                        null,
                        it.episode
                    ) { navController.navigate(AppViewState.EpisodeDetails.route + "?id=${it.id}") }
                }
            }
        }
    }
}