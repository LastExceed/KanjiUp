package com.github.lastexceed.kanjiup

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainMenu(onButtonClicked: (Route) -> Unit) {
	Column(
		Modifier
			.fillMaxSize()
			.padding(50.dp),
		verticalArrangement = Arrangement.spacedBy(20.dp)
	) {
		RectangleButton(
			onClick = { onButtonClicked(Route.VocabLearningRoute) },
			modifier = Modifier.size(width = 300.dp, height = 50.dp),
			backgroundColor = MaterialTheme.colors.primary
		) {
			Text("Learn 漢字")
		}

		RectangleButton(
			onClick = { onButtonClicked(Route.Settings) },
			modifier = Modifier.size(width = 300.dp, height = 50.dp),
			backgroundColor = MaterialTheme.colors.primary
		) {
			Text("Settings")
		}

		RectangleButton(
			onClick = { onButtonClicked(Route.Networking) },
			modifier = Modifier.size(width = 300.dp, height = 50.dp),
			backgroundColor = MaterialTheme.colors.primary
		) {
			Text("Networking Test")
		}
	}
}

@Preview
@Composable
fun MenuButtonsPreview() {
	MainMenu(onButtonClicked = { })
}