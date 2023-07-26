package com.example.githubapp.core.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri

inline fun Context.openWebPage(url: String, onCantHandleAction: () -> Unit = {}) {
    val webpage: Uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, webpage)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    try {
        startActivity(intent)
    } catch (t: Throwable) {
        onCantHandleAction()
    }
}
