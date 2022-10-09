package com.github.lastexceed.kanjiup

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

//sealed class Screen(val route: String, @StringRes val resourceId: Int) {
//	object Profile : Screen("profile", R.string.profile)
//	object FriendsList : Screen("friendslist", R.string.friends_list)
//}

@Composable
fun GlobalNavHost() {
	val navController = rememberNavController()

	NavHost(navController, startDestination = "main_menu") {
		composable("main_menu") {
			MainMenu { destination -> navController.navigate(destination) }
		}
		composable("vocab_learning") { VocabLearning() }
		composable("settings_page") { SettingsPage() }
		//composable("settings") { Settings(navController) }
	}
}