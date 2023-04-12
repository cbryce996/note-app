package com.example.notes.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.presentation.events.AccountEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    //private val accountService: AccountService,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        savedStateHandle.get<Long>("accountId")?.let { accountId ->
            if (accountId != null) {
                viewModelScope.launch {
                    // TODO: Implement account services
                }
            }
        }
    }

    fun onEvent(event: AccountEvent) {
        // TODO: Add event handling
    }
}