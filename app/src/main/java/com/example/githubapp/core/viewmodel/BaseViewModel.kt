package com.example.githubapp.core.viewmodel

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<E> : ViewModel() {

    abstract fun onEvent(event: E)
}
