package com.app.stockapp.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<T> : ViewModel() {

    abstract fun defaultState(): T

    protected val _uiState = MutableStateFlow(defaultState())
    val uiState: StateFlow<T> = _uiState

    protected fun newState(stateCopy: (T) -> T) {
        val oldState = _uiState.value!!
        _uiState.value = stateCopy(oldState)
    }

    protected fun requireState(block: (T) -> Unit): Unit = block(_uiState.value!!)

    protected fun requireState(): T = _uiState.value!!
}
