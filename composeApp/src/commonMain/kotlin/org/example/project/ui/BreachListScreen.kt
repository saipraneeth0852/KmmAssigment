// File: composeApp/src/commonMain/kotlin/org/example/project/ui/BreachListScreen.kt
package org.example.project.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.data.Breach
import org.example.project.viewmodel.BreachListState
import org.example.project.viewmodel.BreachViewModel

@Composable
fun BreachListScreen(viewModel: BreachViewModel) {
    val state by viewModel.state.collectAsState()

    when (val currentState = state) {
        is BreachListState.Loading -> LoadingScreen()
        is BreachListState.Success -> BreachList(currentState.breaches)
        is BreachListState.Error -> ErrorScreen(currentState.message)
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message, color = MaterialTheme.colors.error)
    }
}

@Composable
fun BreachList(breaches: List<Breach>) {
    if (breaches.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No breaches found")
        }
    } else {
        LazyColumn {
            items(breaches) { breach ->
                BreachItem(breach)
            }
        }
    }
}

@Composable
fun BreachItem(breach: Breach) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = breach.Name, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = breach.Title, style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Domain: ${breach.Domain}", style = MaterialTheme.typography.body2)
            Text(text = "Breach Date: ${breach.BreachDate}", style = MaterialTheme.typography.body2)
        }
    }
}