package com.github.lastexceed.kanjiup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

val vocabDecks: Map<String, List<VocabItem>> =
	mapOf(
		"animals" to listOf(
			VocabItem("犬", "inu"),
			VocabItem("猫", "neko"),
			VocabItem("兎", "usagi"),
			VocabItem("鳥", "tori"),
			VocabItem("虎", "tora")
		),
		"fruits" to listOf(
			VocabItem("林檎", "ringo"),
			VocabItem("梨", "nashi"),
			VocabItem("バナナ", "banana"),
			VocabItem("メロン", "melon"),
			VocabItem("葡萄", "budou")
		)
	)

@Composable
fun VocabDeckSelection(
	viewModel: VocabTestViewModel = viewModel(),
	navigateToVocabLearning: () -> Unit,
) {
	LazyColumn(modifier = Modifier.padding(10.dp)) {
		items(items = vocabDecks.keys.toList()) { deckName ->
			VocabDeckCard(
				deckName
			) { name: String ->
				viewModel.setVocabDeck(
					vocabDecks[name]?.iterator() ?: error("deck $name not found")
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
		modifier = Modifier.clickable { onClick(deckName) },
		backgroundColor = MaterialTheme.colors.primary,
	) {
		Text(text = deckName)
	}
}