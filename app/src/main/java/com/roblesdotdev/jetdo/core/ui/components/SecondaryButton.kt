package com.roblesdotdev.jetdo.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.roblesdotdev.jetdo.R
import com.roblesdotdev.jetdo.core.ui.theme.JetDoTheme

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    enabled: Boolean = true
) {
    val buttonColors = ButtonDefaults.textButtonColors(
        contentColor = contentColor
    )
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .height(dimensionResource(id = R.dimen.button_height))
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        colors = buttonColors
    ) {
        Text(text = text.uppercase())
    }
}

@Preview(name = "Night mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SecondaryButtonPreview() {
    JetDoTheme {
        Surface {
            SecondaryButton(text = "Click me", onClick = {})
        }
    }
}
