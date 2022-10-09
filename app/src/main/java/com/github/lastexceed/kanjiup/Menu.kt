package com.github.lastexceed.kanjiup

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.*

@Composable
fun Menu(navController: NavController) {
	Column(
		Modifier
			.fillMaxSize()
			.padding(50.dp),
		verticalArrangement = Arrangement.spacedBy(20.dp)
	) {
		RectangleButton(
			onClick = { navController.navigate("vocab_learning") },
			modifier = Modifier.size(width = 300.dp, height = 50.dp),
			backgroundColor = MaterialTheme.colors.primary
		) {
			Text("Learn 漢字")
		}

		RectangleButton(
			onClick = { navController.navigate("settings_page") },
			modifier = Modifier.size(width = 300.dp, height = 50.dp),
			backgroundColor = MaterialTheme.colors.primary
		) {
			Text("Settings")
		}
	}
}

//@Preview
//@Composable
//fun MenuButtonsPreview() {
//	Menu(onButtonClicked = { })
//}