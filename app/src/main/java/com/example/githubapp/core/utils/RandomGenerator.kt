package com.example.githubapp.core.utils

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RandomGenerator @Inject constructor() {

    companion object {

        private val CHAR_POOL: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    }

    fun getString(length: Int) =
        (1..length)
            .map { CHAR_POOL.random() }
            .joinToString("")
}
