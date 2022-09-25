package com.github.lastexceed.kanjiup

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class VocabTestViewModel(
	private val vocabToTest: Iterator<VocabItem>,
) {
	val currentVocab: MutableState<VocabItem?> =
		mutableStateOf(if (vocabToTest.hasNext()) vocabToTest.next() else null)

	private fun goToNextVocab() {
		val x = if (vocabToTest.hasNext()) vocabToTest.next() else null
		currentVocab.value = x
	}

	fun onAnswerWrong() {
		goToNextVocab()
	}

	fun onAnswerCorrect() {
		println("correct")
		goToNextVocab()
	}
}

data class VocabItem(val show: String, val answer: String)