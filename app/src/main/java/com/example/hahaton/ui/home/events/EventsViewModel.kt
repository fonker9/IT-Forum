package com.example.hahaton.ui.home.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hahaton.data.model.Event
import com.example.hahaton.data.remote.FirebaseEventDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EventsViewModel(private val repo: FirebaseEventDataSource) : ViewModel() {

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events

    init {
        viewModelScope.launch {
            repo.getEventsRealTime().collect { _events.value = it }
        }
    }
}