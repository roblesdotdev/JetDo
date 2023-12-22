package com.roblesdotdev.jetdo.core.utils

import android.content.Context
import androidx.annotation.StringRes

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
