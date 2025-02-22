package com.example.countriesapp.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.countriesapp.screens.DetailsScreen
import com.example.countriesapp.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryAppBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier
) {
    TopAppBar(
        title = { Text("CountryApp")},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = modifier,
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = ""
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CountryNavigation() {
    val navController = rememberNavController()

    val canNavigateBack = navController.currentBackStackEntry != null
    Scaffold(
        topBar = {
            CountryAppBar(
                currentScreen = "Movie App",
                canNavigateBack = true, //navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                modifier = Modifier
            )
        }
    ){  innerPadding ->
        NavHost(navController = navController,
            startDestination = CountryScreens.HomeScreen.name ,  modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {

            composable(CountryScreens.HomeScreen.name){
                //here we pass where this should lead us to
                HomeScreen(navController = navController)
            }

            composable(CountryScreens.DetailScreen.name+"/{country}",
                arguments = listOf(navArgument(name = "country") {type = NavType.StringType})) {
                    backStackEntry ->

                DetailsScreen(navController = navController,
                    backStackEntry.arguments?.getString("country"))
            }
        }
    }
}