// File: composeApp/src/commonMain/kotlin/org/example/project/App.kt
package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.ui.BreachListScreen
import org.example.project.viewmodel.BreachViewModel

@Composable
fun App(viewModel: BreachViewModel = BreachViewModel()) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Security Breach List",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                BreachListScreen(viewModel)
            }
        }
    }
}