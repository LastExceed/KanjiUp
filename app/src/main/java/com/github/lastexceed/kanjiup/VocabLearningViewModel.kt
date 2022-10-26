package com.github.lastexceed.kanjiup

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import java.time.Instant
import kotlin.math.pow

class VocabTestViewModel() : ViewModel() {
	private var currentVocabDeck: Iterator<VocabItem>? = null
	val currentVocab: MutableState<VocabItem?> =
		mutableStateOf(if (currentVocabDeck?.hasNext() == true) currentVocabDeck?.next() else null)

	fun setVocabDeck(deck: Iterator<VocabItem>) {
		currentVocabDeck = deck
		goToNextVocab()
	}

	private fun goToNextVocab() {
		currentVocab.value =
			if (currentVocabDeck?.hasNext() == true) currentVocabDeck?.next() else null
	}

	fun onAnswerWrong() {
		currentVocab.value?.reviewData.apply {
			if (this == null) return@apply
			correctStreak = 0
			incorrectStreak++
			incorrectAmount++
			reviewCount++

			val now = Instant.now()
			lastReviewAt = now
			nextReviewAt = now
		}

		goToNextVocab()
	}

	fun onAnswerCorrect() {
		currentVocab.value?.reviewData.apply {
			if (this == null) return@apply
			correctStreak++
			correctAmount++
			incorrectStreak = 0
			reviewCount++

			val now = Instant.now()
			lastReviewAt = now
			nextReviewAt = now.plusSeconds(
				3600 * 24 * 2.0.pow(correctStreak.toDouble())
					.toLong()
			) //TODO make actual, also always round to nearest hour and not use full days to allow reviews to happen at the same time

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