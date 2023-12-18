package com.roblesdotdev.jetdo.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.roblesdotdev.jetdo.R
import com.roblesdotdev.jetdo.core.ui.theme.JetDoTheme

@Composable
fun AppTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelText: String? = null,
    placeholderText: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    focusRequester: FocusRequester = FocusRequester()
) {
    val labelComposable: (@Composable () -> Unit)? = labelText?.let {
        @Composable {
            Text(text = labelText)
        }
    }
    val placeholderComposable: (@Composable () -> Unit)? = placeholderText?.let {
        @Composable {
            Text(text = placeholderText)
        }
    }
    OutlinedTextField(
        value = text,
        onValueChange = onTextChanged,
        label = labelComposable,
        placeholder = placeholderComposable,
        modifier = modifier
            .heightIn(dimensionResource(id = R.dimen.text_field_height))
            .fillMaxWidth()
            .focusRequester(focusRequester),
        visualTransformation = visualTransformation,
        enabled = enabled,
        keyboardOptions = keyboardOptions
    )
}

@Preview(
    name = "Night mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun AppTextFieldPreview() {
    JetDoTheme {
        Surface {
            AppTextField(
                text = "",
                labelText = "Email",
                placeholderText = "Enter your email",
                onTextChanged = {}
            )
        }
    }
}
