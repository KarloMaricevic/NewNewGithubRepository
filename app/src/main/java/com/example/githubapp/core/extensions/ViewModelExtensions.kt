package com.example.githubapp.core.extensions

import android.content.Context
import androidx.lifecycle.AndroidViewModel

val AndroidViewModel.context
    get() = getApplication() as Context
