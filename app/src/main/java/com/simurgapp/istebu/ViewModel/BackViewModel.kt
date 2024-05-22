package com.simurgapp.istebu.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BackViewModel {
    private var _isBack = MutableStateFlow(0)
    val isBack: StateFlow<Int> = _isBack
    fun increment() {
        _isBack.value += 1

    }
    fun decrement() {
        _isBack.value -= 1
    }
    fun reset() {
        _isBack.value = 0
    }





}