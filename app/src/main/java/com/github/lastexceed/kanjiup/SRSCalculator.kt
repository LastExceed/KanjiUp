package com.github.lastexceed.kanjiup

import java.time.Instant
import kotlin.math.pow

fun ReviewData.updateReviewCorrect(): ReviewData {
	return this.apply {
		correctStreak++
		correctAmount++
		incorrectStreak = 0
		reviewCount++

		val now = Instant.now()
		lastReviewAt = now
		nextReviewAt = calculateNextReviewTime(now)
	}
}

fun ReviewData.updateReviewWrong(): ReviewData {
	return this.apply {
		correctStreak = 0
		incorrectStreak++
		incorrectAmount++
		reviewCount++

		val now = Instant.now()
		lastReviewAt = now
		nextReviewAt = now
	}
}

private fun ReviewData.calculateNextReviewTime(now: Instant): Instant {
	return now.plusSeconds(
		3600 * 24 * 2.0.pow(correctStreak.toDouble())
			.toLong()
	)
}
