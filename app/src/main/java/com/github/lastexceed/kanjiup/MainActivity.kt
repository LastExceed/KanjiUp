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
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import com.github.lastexceed.kanjiup.ui.theme.*

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			KanjiUpTheme {
				Everything()
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun Everything() {
	Column(Modifier.fillMaxSize()) {
		var buttonWasClicked by remember { mutableStateOf(false) }
		Column(
			Modifier
				.fillMaxSize()
				.weight(1f)
				.absolutePadding(top = 200.dp)) {
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
		if (!buttonWasClicked) {
			Button(onClick = { buttonWasClicked = !buttonWasClicked }, Modifier.align(Alignment.CenterHorizontally)) {
				Text("reveal")
			}
		} else {
			Row(Modifier.fillMaxWidth().height(75.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
				Button(
					onClick = { /*TODO*/ },
					shape = RectangleShape,
					modifier = Modifier.weight(1f).fillMaxSize(),
					colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff93c47d))//20bb20))
				) {
					Icon(Icons.Rounded.Check, "ok", Modifier.fillMaxSize(), tint = Color(0xff202020))
				}
				Button(
					onClick = { /*TODO*/ },
					shape = RectangleShape,
					modifier = Modifier.weight(1f).fillMaxSize(),
					colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffe06666))//ff3030))
				) {
					Icon(Icons.Rounded.Close, "bad", Modifier.fillMaxSize(), tint = Color(0xff202020))
				}
			}
		}
	}
}