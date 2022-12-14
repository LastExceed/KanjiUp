package com.github.lastexceed.kanjiup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.github.lastexceed.kanjiup.vocablearning.VocabDeckSelection

//sealed class Screen(val route: String, @StringRes val resourceId: Int) {
//	object Profile : Screen("profile", R.string.profile)
//	object FriendsList : Screen("friendslist", R.string.friends_list)
//}

@Composable
fun GlobalNavHost() {
	val navController = rememberNavController()

	NavHost(navController, startDestination = Route.MainMenu.raw) {
		composable(Route.MainMenu.raw) {
			MainMenu { destination -> navController.navigate(destination.raw) }
		}

		navigation(
			startDestination = Route.VocabDeckSelection.raw,
			route = Route.VocabLearningRoute.raw
		) {
			composable(Route.VocabDeckSelection.raw) { navBackStackEntry ->
				val navigationGraphEntry: NavBackStackEntry = remember(navBackStackEntry) {
					navController.getBackStackEntry(Route.VocabLearningRoute.raw)
				}
				val navigationGraphScopedViewModel: VocabTestViewModel =
					viewModel(viewModelStoreOwner = navigationGraphEntry)
				VocabDeckSelection(navigationGraphScopedViewModel) {
					navController.navigate(Route.VocabLearning.raw)
				}
			}
			composable(Route.VocabLearning.raw) { navBackStackEntry ->
				val navigationGraphEntry = remember(navBackStackEntry) {
					navController.getBackStackEntry(Route.VocabLearningRoute.raw)
				}
				val navigationGraphScopedViewModel: VocabTestViewModel =
					viewModel(navigationGraphEntry)
				VocabLearning(navigationGraphScopedViewModel) {
					navController.navigate(Route.VocabDeckSelection.raw) {
						popUpTo(Route.VocabDeckSelection.raw) { inclusive = false }
						launchSingleTop = true
					}
				}
			}
		}
		composable(Route.Settings.raw) { Settings() }
		//composable("settings") { Settings(navController) }
		composable(Route.Networking.raw) { NetworkTest() }
	}
}

enum class Route(val raw: String) {
	MainMenu("main_menu"),
	VocabLearningRoute("vocab_learning_route"),
	VocabDeckSelection("vocab_deck_selection"),
	VocabLearning("vocab_learning"),
	Settings("settings"),
	Networking("networking"),
}