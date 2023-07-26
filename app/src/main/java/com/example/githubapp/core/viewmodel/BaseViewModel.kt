package com.example.githubapp.core.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel<E>(
    application: Application,
) : AndroidViewModel(application) {

    abstract fun onEvent(event: E)
}
