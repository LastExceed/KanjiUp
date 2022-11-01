package com.github.lastexceed.kanjiup

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import java.time.Instant
import kotlin.math.pow

class VocabTestViewModel() :
	ViewModel() {
	var currentVocabDeck: List<VocabItem>? = null

	private var currentVocabDeckiterator: ListIterator<VocabItem>? = null
	val currentVocab: MutableState<VocabItem?> =
		mutableStateOf(
			if (currentVocabDeckiterator?.hasNext() == true)
				currentVocabDeckiterator?.next() else null
		)

	fun setVocabDeck(deck: List<VocabItem>) {
		currentVocabDeck = deck
		currentVocabDeckiterator = deck.listIterator()
		goToNextVocab()
	}

	private fun goToNextVocab() {
		currentVocab.value =
			if (currentVocabDeckiterator?.hasNext() == true) currentVocabDeckiterator?.next() else null
	}

	fun onAnswerWrong() {
		currentVocab.value?.reviewData.apply {
			if (this == null) return@apply
			this.updateReviewWrong()
		}
		goToNextVocab()
	}

	fun onAnswerCorrect() {
		currentVocab.value?.reviewData.apply {
			if (this == null) return@apply
			this.updateReviewCorrect()
		}
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