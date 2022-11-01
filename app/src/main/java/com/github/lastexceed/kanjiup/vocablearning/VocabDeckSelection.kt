package com.github.lastexceed.kanjiup.vocablearning

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.lastexceed.kanjiup.VocabItem
import com.github.lastexceed.kanjiup.VocabTestViewModel

val vocabDecks: Map<String, List<VocabItem>> =
	mapOf(
		"animals" to listOf(
			VocabItem("犬", "inu"),
			VocabItem("猫", "neko"),
			VocabItem("兎", "usagi"),
			VocabItem("鳥", "tori"),
			VocabItem("虎", "tora"),
		),
		"fruits" to listOf(
			VocabItem("林檎", "ringo"),
			VocabItem("梨", "nashi"),
			VocabItem("バナナ", "banana"),
			VocabItem("メロン", "melon"),
			VocabItem("葡萄", "budou, ")
		)
	)

@Composable
fun VocabDeckSelection(
	viewModel: VocabTestViewModel = viewModel(),
	navigateToVocabLearning: () -> Unit,
) {
	LazyColumn(
		Modifier
			.fillMaxSize()
			.padding(50.dp),
		verticalArrangement = Arrangement.spacedBy(20.dp)
	) {
		items(items = vocabDecks.keys.toList()) { deckName ->
			VocabDeckCard(
				deckName
			) { name: String ->
				viewModel.setVocabDeck(
					vocabDecks[name] ?: error("deck $name not found")
				)
				navigateToVocabLearning()
			}
		}
	}
}

@Composable
fun VocabDeckCard(
	deckName: String,
	onClick: (String) -> Unit,
) {
	Card(
		modifier = Modifier
			.fillMaxSize()
			.clickable { onClick(deckName) },
		backgroundColor = MaterialTheme.colors.primary,
	) {
		Text(
			text = deckName
		)
	}
}

@Preview
@Composable
fun VocabDeckCardPreview() = VocabDeckCard(deckName = "Animals", onClick = {})
