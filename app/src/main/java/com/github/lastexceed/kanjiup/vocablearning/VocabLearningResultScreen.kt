package com.github.lastexceed.kanjiup.vocablearning

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.lastexceed.kanjiup.RectangleButton
import com.github.lastexceed.kanjiup.ReviewData
import com.github.lastexceed.kanjiup.VocabItem
import kotlin.random.Random

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
