package org.example.project.viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.data.Breach
import org.example.project.data.BreachApi

sealed class BreachListState {
    object Loading : BreachListState()
    data class Success(val breaches: List<Breach>) : BreachListState()
    data class Error(val message: String) : BreachListState()
}

class BreachViewModel : ViewModel() {
    private val api = BreachApi()
    private val _state = MutableStateFlow<BreachListState>(BreachListState.Loading)
    val state: StateFlow<BreachListState> = _state

    init {
        fetchBreaches()
    }

    private fun fetchBreaches() {
        viewModelScope.launch {
            _state.value = BreachListState.Loading
            try {
                println("ViewModel: Starting to fetch breaches...")
                val breaches = api.getBreaches()
                println("ViewModel: Fetched ${breaches.size} breaches")
                _state.value = BreachListState.Success(breaches)
            } catch (e: Exception) {
                println("ViewModel: Error fetching breaches: ${e.message}")
                e.printStackTrace()
                _state.value = BreachListState.Error("Failed to fetch breaches: ${e.message}")
            }
        }
    }
}