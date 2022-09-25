package com.github.lastexceed.kanjiup

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Menu(navController: NavController) {
	Scaffold(
		//topBar = {
		//	TopAppBar {
		//		IconButton(onClick = {
		//			if (navController.currentBackStackEntry != null) {
		//				navController.navigateUp()
		//			}
		//		}) {
		//			Icon(
		//				imageVector = Icons.Rounded.ArrowBack,
		//				contentDescription = "arrow back",
		//				modifier = Modifier.align(alignment = Alignment.CenterVertically)
		//			)
		//		}
		//	}
		//}
	) { contentPadding ->
		MenuButtons(Modifier.padding(contentPadding)) { destination: String ->
			navController.navigate(destination)
		}
	}
}


@Composable
fun MenuButtons(modifier: Modifier, onClick: (String) -> Unit) {
	Column(
		modifier
			.fillMaxSize()
			.padding(50.dp),
		verticalArrangement = Arrangement.spacedBy(20.dp)
	) {
		RectangleButton(
			onClick = { onClick("vocab_learning") },
			modifier = Modifier.size(width = 300.dp, height = 50.dp),
			backgroundColor = MaterialTheme.colors.primary
		) {
			Text("Learn 漢字")
		}

		RectangleButton(
			onClick = { onClick("settings_page") },
			modifier = Modifier.size(width = 300.dp, height = 50.dp),
			backgroundColor = MaterialTheme.colors.primary
		) {
			Text("Settings")
		}
	}
}

@Preview
@Composable
fun MenuButtonsPreview() {
	MenuButtons(onClick = { }, modifier = Modifier)
}