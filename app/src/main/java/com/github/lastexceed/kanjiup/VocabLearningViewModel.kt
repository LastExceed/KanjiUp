package com.github.lastexceed.kanjiup

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import java.time.Instant

class VocabTestViewModel() : ViewModel() {
	private var vocabToTest: Iterator<VocabItem>? = null
	val currentVocab: MutableState<VocabItem?> =
		mutableStateOf(if (vocabToTest?.hasNext() == true) vocabToTest?.next() else null)

	fun setVocabDeck(deck: Iterator<VocabItem>) {
		vocabToTest = deck
		goToNextVocab()
	}

	private fun goToNextVocab() {
		currentVocab.value = if (vocabToTest?.hasNext() == true) vocabToTest?.next() else null
	}

	fun onAnswerWrong() {
		goToNextVocab()
	}

	fun onAnswerCorrect() {
		println("correct")
		goToNextVocab()
	}
}

data class VocabItem(
	val show: String,
	val answer: String,
	val reviewData: ReviewData = ReviewData()
)

data class ReviewData(
	var correctStreak: Int = 0,
	var correctAmount: Int = 0,

	var incorrectStreak: Int = 0,
	var incorrectAmount: Int = 0,

	var reviewCount: Int = 0,

	var lastReviewAt: Instant = Instant.now(),
	var nextReviewAt: Instant = Instant.now(),
)