package com.formation.app.ui.views.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.formation.app.common.ui.theme.MainGreen
import com.formation.app.ui.views.episode.view.EpisodeView
import com.formation.app.ui.views.episodeDetails.view.EpisodeDetailsView
import com.formation.app.ui.views.home.view.HomeView
import com.formation.app.ui.views.location.view.LocationView
import com.formation.app.ui.views.locationDetails.view.LocationDetailsView
import com.formation.app.ui.views.personagedetails.view.PersonageDetailsView

sealed class AppViewState(val route: String, val name: String, val icon: ImageVector) {
    object Home : AppViewState("home", name = "Home", icon = Icons.Default.Person)
    object PersonageDetails :
        AppViewState("personageDetails", name = "PersonageDetails", icon = Icons.Default.Person)

    object LocationDetails :
        AppViewState("locationDetails", name = "LocationDetails", icon = Icons.Default.Place)

    object Location : AppViewState("location", name = "Location", icon = Icons.Default.Place)
    object Episode : AppViewState("episode", name = "Episode", icon = Icons.Default.Movie)
    object EpisodeDetails :
        AppViewState("episodeDetails", name = "EpisodeDetails", icon = Icons.Default.Movie)
}

@Composable
fun AppBottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val views = remember {
        listOf(AppViewState.Home, AppViewState.Location, AppViewState.Episode)
    }

    BottomNavigation(
        backgroundColor = Color.MainGreen,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
    ) {
        views.forEach { view ->
            BottomNavigationItem(
                selected = currentRoute == view.route,
                onClick = {
                    navController.navigate(view.route)
                },
                icon = {
                    Icon(imageVector = view.icon, contentDescription = "")
                }
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppControllerView() {
    val navController = rememberNavController()
    val currentRoute = remember {
        mutableStateOf(AppViewState.Home.route)
    }
    val routeWithoutNavBar = remember {
        listOf(AppViewState.PersonageDetails.route,
            AppViewState.LocationDetails.route,
            AppViewState.EpisodeDetails.route)
    }

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, _, _ ->
            currentRoute.value =
                navController.currentBackStackEntry?.destination?.route ?: AppViewState.Home.route
        }
    }

    Scaffold(
        bottomBar = {
            if (!routeWithoutNavBar.contains(currentRoute.value.substringBefore('?'))) {
                AppBottomBar(navController)
            }
        }) {
        NavHost(navController = navController, startDestination = AppViewState.Home.route) {
            composable(AppViewState.Home.route) {
                HomeView(navController)
            }
            composable(AppViewState.Episode.route) {
                EpisodeView(navController)
            }
            composable(AppViewState.Location.route) {
                LocationView(navController)
            }
            composable(
                AppViewState.PersonageDetails.route + "?id={id}"
            ) { backStackEntry ->
                PersonageDetailsView(navController, backStackEntry.arguments?.getString("id")!!)
            }
            composable(
                AppViewState.LocationDetails.route + "?id={id}"
            ) { backStackEntry ->
                LocationDetailsView(navController, backStackEntry.arguments?.getString("id")!!)
            }
            composable(
                AppViewState.EpisodeDetails.route + "?id={id}"
            ) { backStackEntry ->
                EpisodeDetailsView(navController, backStackEntry.arguments?.getString("id")!!)
            }
        }
    }
}