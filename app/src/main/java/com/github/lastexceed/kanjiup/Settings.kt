package com.github.lastexceed.kanjiup

import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun SettingsPage() {
	val isChecked = rememberSaveable { mutableStateOf(false) }

	Checkbox(checked = isChecked.value, onCheckedChange = { isChecked.value = !isChecked.value })
}
