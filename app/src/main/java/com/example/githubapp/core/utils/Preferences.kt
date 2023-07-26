package com.example.githubapp.core.utils

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Stores and reads from shared preferences
 *
 * Supported types:
 * - [String], [Int], [Boolean], [Float], [Long]
 * - @throws [UnsupportedOperationException] for unknown types
 *
 * Usage:
 *
 * - Make object or class that extends [Preference]:
 *  `object MyPreference : Preference<String>(name = "auth_token", default = "{}")`
 *
 * - Write to preferences:
 *  `PreferenceProvider[MyPreference] = "newValue"`
 *
 * - Read from preferences:
 *  `val value = PreferenceProvider[MyPreference]`
 *
 */
@Suppress("UNCHECKED_CAST")
class Preferences(
    @ApplicationContext context: Context,
) {

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    operator fun <R> get(preference: Preference<R>): R? =
        prefs.all.getOrElse(preference.name) { preference.default } as R?

    operator fun <R> set(
        key: Preference<R>,
        value: Any?,
    ) {
        val editor = prefs.edit()
        when (value) {
            is String? -> editor.putString(key.name, value)
            is Int -> editor.putInt(key.name, value)
            is Boolean -> editor.putBoolean(key.name, value)
            is Float -> editor.putFloat(key.name, value)
            is Long -> editor.putLong(key.name, value)
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
        editor.apply()
    }
}

open class Preference<R>(
    val name: String,
    val default: R?,
)
