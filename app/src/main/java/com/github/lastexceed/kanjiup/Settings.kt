package com.github.lastexceed.kanjiup

import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun SettingsPage() {
	var isChecked by rememberSaveable { mutableStateOf(false) }

	Checkbox(checked = isChecked, onCheckedChange = { isChecked = !isChecked })
}
