package com.github.lastexceed.kanjiup

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Menu(navController: NavController) {
	Scaffold(
		topBar = {
			TopAppBar {
				IconButton(onClick = {
					if (navController.currentBackStackEntry != null) {
						navController.navigateUp()
					}
				}) {
					Icon(
						imageVector = Icons.Rounded.ArrowBack,
						contentDescription = "arrow back",
						modifier = Modifier.align(alignment = Alignment.CenterVertically)
					)
				}
			}
		}
	) { contentPadding ->
		MenuButtons(navController, Modifier.padding(contentPadding))
	}
}

@Composable
fun MenuButtons(navController: NavController, modifier: Modifier) {
	Row(
		modifier
			.fillMaxSize()
			.padding(start = 50.dp, end = 50.dp)
			.padding(horizontal = 50.dp)
	) {
		RectangleButton(
			onClick = {
				navController.navigate("vocab_learning")
			},
			backgroundColor = MaterialTheme.colors.primary
		) {
			Text("Learn 漢字")
		}
	}
}