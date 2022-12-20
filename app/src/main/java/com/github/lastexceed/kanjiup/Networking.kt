package com.github.lastexceed.kanjiup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GrantNetworkingPermission() {
	val internetPermissionState = rememberPermissionState(android.Manifest.permission.INTERNET)
	if (internetPermissionState.status.isGranted) {
		Text("Internet permission Granted")
	} else {
		Column {
			val textToShow = if (internetPermissionState.status.shouldShowRationale) {
				"Internet Access is used to store and sync your learning progress. Please grant the permission."
			} else {
				"Internet Access not available, can't login or sync data"
			}

			Text(textToShow)
			Spacer(modifier = Modifier.height(8.dp))
			Button(onClick = { internetPermissionState.launchPermissionRequest() }) {
				Text("Request permission")
			}
		}
	}
}

@Composable
fun NetworkTest() {
	Column {
		RectangleButton(
			onClick = {
				sendPostRequest(LoginData("test@mail.com", "1234"))
			},
			backgroundColor = MaterialTheme.colors.primary
		) {
			Text("Network Test")
		}
	}
}

val gson = Gson()

fun sendPostRequest(loginData: LoginData) {
	val jsonString = gson.toJson(loginData)

	val url = URL("https://127.0.0.1:80/login")
	val response = StringBuffer()

	with(url.openConnection() as HttpURLConnection) {
		requestMethod = "POST"

		val outputStreamWriter = OutputStreamWriter(this.outputStream);
		outputStreamWriter.write(jsonString);
		outputStreamWriter.flush();

		println("URL : $url")
		println("Response Code : $responseCode")

		BufferedReader(InputStreamReader(inputStream)).use {


			var inputLine = it.readLine()
			while (inputLine != null) {
				response.append(inputLine)
				inputLine = it.readLine()
			}
			println("Response : $response")
		}
	}
}

class LoginData(
	val email: String,
	val password: String,
)