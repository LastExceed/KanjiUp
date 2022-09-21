package com.github.lastexceed.kanjiup

import androidx.compose.runtime.MutableState

class VocabTestViewModel(
	private val vocabToTest: List<VocabItem>,
	private val mode: MutableState<Mode>,
) {
	private val currentVocab: VocabItem

	init {
		if (vocabToTest.isEmpty()) error("list empty")
		currentVocab = vocabToTest.first()
	}

	fun returnToMenu() {
		mode.value = Mode.Menu
	}
}

data class VocabItem(val show: String, val answer: String)