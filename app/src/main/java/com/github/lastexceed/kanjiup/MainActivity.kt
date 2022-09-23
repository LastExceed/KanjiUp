package com.github.lastexceed.kanjiup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.lastexceed.kanjiup.ui.theme.KanjiUpTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			KanjiUpTheme {
				Menu()
			}
		}
	}
}
