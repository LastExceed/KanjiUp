package com.github.lastexceed.kanjiup

import android.os.*
import androidx.activity.*
import androidx.activity.compose.*
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import com.github.lastexceed.kanjiup.ui.theme.*

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			KanjiUpTheme {
				Menu()
			}
		}
	}
}

enum class Mode() {
	VocabLearning,
	Menu,
}

@Composable
fun Menu() {
	var mode = rememberSaveable { mutableStateOf(Mode.Menu) }

	when (mode.value) {
		Mode.Menu -> MenuButtons(mode)
		Mode.VocabLearning -> ReviewCard()
	}
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

@Preview(showBackground = true)
@Composable
fun ReviewCard() {
	Column(Modifier.fillMaxSize()) {
		var buttonWasClicked by remember { mutableStateOf(false) }
		Column(
			Modifier
				.fillMaxSize()
				.weight(1f)
				.absolutePadding(top = 200.dp)
		) {
			Text(
				"柴",
				Modifier.fillMaxWidth(),
				textAlign = TextAlign.Center,
				fontSize = 200.sp,
			)
			AnimatedVisibility(visible = buttonWasClicked, enter = fadeIn()) {
				Text(
					"Shiba",
					Modifier
						.fillMaxWidth()
						.alpha(1f),
					textAlign = TextAlign.Center,
					fontSize = 50.sp,
					color = MaterialTheme.colors.primary,
				)
			}
		}

		val modifier = Modifier
			.fillMaxWidth()
			.height(75.dp)
		if (!buttonWasClicked) {
			RectangleButton(
				modifier = modifier,
				onClick = { buttonWasClicked = !buttonWasClicked },
				backgroundColor = MaterialTheme.colors.primary
			) {
				Icon(
					Icons.Rounded.Visibility,
					"reveal answer",
					Modifier.fillMaxSize()
				)
			}
		} else {
			JudgmentBar(modifier = modifier)
		}
	}
}

@Composable
fun JudgmentBar(modifier: Modifier) {
	Row(
		modifier,
		horizontalArrangement = Arrangement.SpaceEvenly
	) {
		RectangleButton(
			modifier = Modifier.weight(1.0F),
			onClick = { /*TODO*/ },
			backgroundColor = Color(0xff93c47d),
		) {
			Icon(
				Icons.Rounded.Check,
				"grade item correct",
				Modifier.fillMaxSize(),
				//tint = Color(0xff202020)
			)
		}
		RectangleButton(
			modifier = Modifier.weight(1.0F),
			onClick = { /*TODO*/ },
			backgroundColor = Color(0xffe06666)
		) {
			Icon(
				Icons.Rounded.Close,
				"grade item wrong",
				Modifier.fillMaxSize(),
				//tint = Color(0xff202020)
			)
		}
	}
}

@Composable
fun RectangleButton(
	modifier: Modifier = Modifier,
	onClick: () -> Unit,
	backgroundColor: Color,
	content: @Composable RowScope.() -> Unit,
) {
	Button(
		onClick = onClick,
		shape = RectangleShape,
		modifier = modifier
			.fillMaxSize(),
		colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)//ff3030))
	) {
		content()
	}
}
