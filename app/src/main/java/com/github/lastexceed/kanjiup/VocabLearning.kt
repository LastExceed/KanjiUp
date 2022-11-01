package com.github.lastexceed.kanjiup

import android.webkit.WebSettings
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.random.Random

@Composable
fun VocabLearning(
	viewModel: VocabTestViewModel = viewModel(),
	navigateToDeckSelectionScreen: () -> Unit
) {
	val vocabItem = viewModel.currentVocab.value
	println(vocabItem?.answer)
	if (vocabItem == null) {
		ResultScreen(
			vocabDeck = viewModel.currentVocabDeck!!,
			navigateToDeckSelectionScreen = navigateToDeckSelectionScreen,
		)
	} else {
		ReviewCard(
			vocabItem,
			viewModel::onAnswerCorrect,
			viewModel::onAnswerWrong
		)
	}
}

//@Preview(showBackground = true)
@Composable
fun ReviewCard(
	vocabItem: VocabItem,
	onAnswerCorrect: () -> Unit,
	onAnswerWrong: () -> Unit
) {
	Column(Modifier.fillMaxSize()) {
		var buttonWasClicked by rememberSaveable { mutableStateOf(false) }

		Column(
			Modifier
				.fillMaxSize()
				.weight(1f)
				.absolutePadding(top = 200.dp)
		) {
			Text(
				vocabItem.show,
				Modifier.fillMaxWidth(),
				textAlign = TextAlign.Center,
				fontSize = 200.sp,
			)

			AnimatedVisibility(
				visible = buttonWasClicked,
				enter = fadeIn(),
				exit = ExitTransition.None
			) {
				Text(
					//workaround for solution being visible for 1 frame after evaluation of the previous vocab
					text = if (!buttonWasClicked) "" else vocabItem.answer,
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
			JudgmentBar(
				onAnswerCorrect = {
					buttonWasClicked = false
					onAnswerCorrect()
				},
				onAnswerWrong = {
					buttonWasClicked = false
					onAnswerWrong()
				},
				modifier
			)
		}
	}
}

@Composable
fun JudgmentBar(
	onAnswerCorrect: () -> Unit,
	onAnswerWrong: () -> Unit,
	modifier: Modifier
) {
	Row(
		modifier,
		horizontalArrangement = Arrangement.SpaceEvenly
	) {
		RectangleButton(
			modifier = Modifier.weight(1.0F),
			onClick = onAnswerCorrect,
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
			onClick = onAnswerWrong,
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

@Composable
fun ResultScreen(
	vocabDeck: List<VocabItem>,
	navigateToDeckSelectionScreen: () -> Unit,
) {
	Column(modifier = Modifier.fillMaxSize()) {
		ResultItemList(modifier = Modifier.weight(1F), vocabDeck = vocabDeck)
		RectangleButton(
			//modifier = Modifier.weight(0.1F),
			modifier = Modifier.height(75.dp),
			onClick = navigateToDeckSelectionScreen,
			backgroundColor = MaterialTheme.colors.primary
		) {
			Text(text = "Return To Menu", fontSize = MaterialTheme.typography.h4.fontSize)
		}
	}

}

@Composable
fun ResultItemList(
	modifier: Modifier = Modifier,
	vocabDeck: List<VocabItem>
) {
	LazyVerticalGrid(
		modifier = modifier,
		columns = GridCells.Adaptive(minSize = 10.dp),
		contentPadding = PaddingValues(10.dp),
		horizontalArrangement = Arrangement.SpaceEvenly,
	) {
		item(
			span = { GridItemSpan(maxCurrentLineSpan) }
		) {
			VocabCard(
				text = "Correct Items",
				backgroundColor = Color(0xff93c47d)
			)
		}

		items(
			items = vocabDeck.filter { it.reviewData.correctStreak > 0 },
			span = { (show) ->
				GridItemSpan((show.length * 2) + 2)
			}
		) { (show) -> VocabCard(text = show) }

		item(
			span = { GridItemSpan(maxLineSpan) }
		) {
			VocabCard(
				text = "Wrong Items",
				backgroundColor = Color(0xffe06666)
			)
		}

		items(
			items = vocabDeck.filter { it.reviewData.correctStreak == 0 },
			span = { (show) ->
				GridItemSpan((show.length * 2) + 2)
			}
		) { (show) -> VocabCard(text = show) }
	}
}

@Preview
@Composable
fun ResultScreenPreview() {
	val random = Random(1234)

	ResultScreen(
		vocabDeck = (1..170).map {
			VocabItem(
				show = "柴".repeat(random.nextInt(1, 4)),
				answer = "",
				reviewData = ReviewData(correctStreak = random.nextInt(0, 2)),
			)
		},  //.sortedBy { it.show.length },
		navigateToDeckSelectionScreen = {},
	)
}

@Composable
fun VocabCard(
	text: String,
	backgroundColor: Color = MaterialTheme.colors.background,
) {
	Card(
		backgroundColor = backgroundColor,
		modifier = Modifier.padding(5.dp),
	) {
		Text(
			//modifier = Modifier.fillMaxWidth(),
			text = text,
			style = TextStyle(
				fontSize = 20.sp,//MaterialTheme.typography.body2.fontSize,
				textAlign = TextAlign.Center
			)
		)
	}
}
