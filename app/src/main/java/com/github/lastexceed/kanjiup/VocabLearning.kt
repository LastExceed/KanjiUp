package com.github.lastexceed.kanjiup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VocabLearning() {
	ReviewCard()
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