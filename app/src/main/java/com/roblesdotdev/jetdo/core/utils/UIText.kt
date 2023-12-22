package com.roblesdotdev.jetdo.core.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/*
 * Contains all different ways text can be represented on the UI.
 */
sealed class UIText {
    data class StringText(val value: String) : UIText()

    data class ResourceText(@StringRes val value: Int) : UIText()
}

fun UIText.getString(context: Context): String {
    return when (this) {
        is UIText.StringText -> this.value
        is UIText.ResourceText -> context.getString(this.value)
    }
}

/**
 * A helper function that allows to get strings from a [Composable] context.
 */
@Composable
fun UIText.getString(): String {
    return this.getString(LocalContext.current)
}
