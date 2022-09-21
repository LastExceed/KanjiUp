package com.github.lastexceed.kanjiup

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

@Composable
fun Menu() {
	val mode = rememberSaveable { mutableStateOf(Mode.Menu) }

	when (mode.value) {
		Mode.Menu -> MenuButtons(mode)
		Mode.VocabLearning -> ReviewCard()
	}
}

enum class Mode() {
	Menu,
	VocabLearning,
}

@Composable
fun MenuButtons(mode: MutableState<Mode>) {
	Row(Modifier.fillMaxSize()) {
		RectangleButton(
			onClick = {
				mode.value = Mode.VocabLearning
			},
			backgroundColor = MaterialTheme.colors.primary
		) {
			Text("Learn 漢字")
		}

	}
}