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
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import com.github.lastexceed.kanjiup.ui.theme.*

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			KanjiUpTheme {
				RewiewCard()
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun RewiewCard() {
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

		val modifier = Modifier.fillMaxWidth().height(75.dp)
		if (!buttonWasClicked) {
			RectangleButton(
				modifier = modifier,
				onClick = { buttonWasClicked = !buttonWasClicked },
				backgroundColor = MaterialTheme.colors.primary,
				icon = Icons.Rounded.Visibility,
				contentDescription = "reveal Answer"
			)
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
			icon = Icons.Rounded.Check,
			"grade item correct"
		)
		RectangleButton(
			modifier = Modifier.weight(1.0F),
			onClick = { /*TODO*/ },
			backgroundColor = Color(0xffe06666),
			Icons.Rounded.Close,
			"grade item wrong"
		)
	}
}

@Composable
fun RectangleButton(
	modifier: Modifier = Modifier,
	onClick: () -> Unit,
	backgroundColor: Color,
	icon: ImageVector,
	contentDescription: String? = null
) {
	Button(
		onClick = onClick,
		shape = RectangleShape,
		modifier = modifier
			.fillMaxSize(),
		colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)//ff3030))
	) {
		Icon(
			icon,
			contentDescription,
			Modifier.fillMaxSize(),
			//tint = Color(0xff202020)
		)
	}
}