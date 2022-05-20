package com.formation.app.ui.views.personagedetails.view

import CustomCardComponent
import CustomIconTextComponent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Public
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.formation.app.common.ui.theme.MainGreen
import com.formation.app.common.ui.theme.MainPink
import com.formation.app.ui.views.navigation.AppViewState
import com.formation.app.ui.views.personagedetails.viewModel.PersonageDetailsViewModel

@Composable
fun PersonageDetailsView(
    navController: NavHostController,
    id: String,
    personageDetailsViewModel: PersonageDetailsViewModel = hiltViewModel(),
) {
    val personage by personageDetailsViewModel.personage.collectAsState()
    val episodes by personageDetailsViewModel.episodes.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        personageDetailsViewModel.fetchPersonage(id)
    })

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Text(
            text = "${personage?.name}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.MainGreen,
            textAlign = TextAlign.Center
        )
        Row(horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = rememberImagePainter(
                    data = Uri.parse("${personage?.image}")
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.MainPink, CircleShape),
                contentScale = ContentScale.Fit,
            )
            Column(modifier = Modifier.padding(8.dp)) {
                if (personage?.status != "unknown") {
                    Text("This ${personage?.gender} ${personage?.species} is ${personage?.status}.")
                } else {
                    Text("This is a ${personage?.gender} ${personage?.species}.")
                }
                personage?.origin?.name?.let { CustomIconTextComponent(it, Icons.Default.Public) }
                personage?.location?.name?.let { CustomIconTextComponent(it, Icons.Default.NearMe) }
            }
        }
        Text("Episodes",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.MainGreen,
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(top = 20.dp))
        LazyColumn {
            items(episodes) {
                CustomCardComponent(
                    it.name,
                    null,
                    it.episode,
                ) { navController.navigate(AppViewState.EpisodeDetails.route + "?id=${it.id}") }
            }
        }
    }
}