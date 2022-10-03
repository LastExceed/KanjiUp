package com.github.lastexceed.kanjiup

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class VocabTestViewModel() {
	private val vocabToTest: Iterator<VocabItem> = listOf(
		VocabItem("犬", "inu"),
		VocabItem("猫", "neko"),
		VocabItem("兎", "usagi"),
		VocabItem("鳥", "tori"),
		VocabItem("虎", "tora")
	).iterator()

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