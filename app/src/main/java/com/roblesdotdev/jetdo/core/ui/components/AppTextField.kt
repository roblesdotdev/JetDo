package com.roblesdotdev.jetdo.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
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
    focusRequester: FocusRequester = FocusRequester(),
    errorMessage: String? = null,
    singleLine: Boolean = true,
    maxLines: Int = 1
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
    Column {
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
            keyboardOptions = keyboardOptions,
            isError = (errorMessage != null),
            singleLine = singleLine,
            maxLines = maxLines
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                Modifier.padding(start = 16.dp, top = 4.dp),
                color = MaterialTheme.colorScheme.error
            )
        }
    }
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

@Preview(
    name = "Error Night mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Error Light mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun AppTextFieldErrorPreview() {
    JetDoTheme {
        Surface {
            AppTextField(
                text = "",
                labelText = "Email",
                placeholderText = "Enter your email",
                onTextChanged = {},
                errorMessage = "Input error message"
            )
        }
    }
}
