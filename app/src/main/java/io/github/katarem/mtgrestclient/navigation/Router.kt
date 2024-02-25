package io.github.katarem.mtgrestclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.katarem.mtgrestclient.screens.HomeScreen
import io.github.katarem.mtgrestclient.screens.UserDetails
import io.github.katarem.mtgrestclient.screens.UserLoginScreen
import io.github.katarem.mtgrestclient.viewmodel.MTGViewModel
import io.github.katarem.mtgrestclient.viewmodel.UserViewModel

@Composable
fun Router(){

    val navController = rememberNavController()
    val userModel: UserViewModel = viewModel()
    val mtgModel: MTGViewModel = viewModel()
    NavHost(navController = navController,startDestination = Routes.LOGIN){

        composable(Routes.LOGIN){
            UserLoginScreen(navController = navController, userModel)
        }

        composable(Routes.HOMESCREEN){
            val user = userModel.user.collectAsState()
            HomeScreen(navController = navController, user.value, mtgModel)
        }

        composable(Routes.USERDETAILS){
            val deckCount = mtgModel.listaDecks.collectAsState().value.size
            UserDetails(navController = navController, userModel, deckCount)
        }

    }



}