package com.roblesdotdev.jetdo.login.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roblesdotdev.jetdo.R
import com.roblesdotdev.jetdo.core.ui.components.AppTextField
import com.roblesdotdev.jetdo.core.ui.components.PrimaryButton
import com.roblesdotdev.jetdo.core.ui.components.SecondaryButton
import com.roblesdotdev.jetdo.core.ui.theme.JetDoTheme

@Composable
fun LoginContent(
    state: LoginViewState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = dimensionResource(id = R.dimen.screen_padding),
                    end = dimensionResource(id = R.dimen.screen_padding)
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            AppTextField(
                text = state.email,
                onTextChanged = {},
                labelText = "Email",
                placeholderText = "Enter your email",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            AppTextField(
                text = state.password,
                onTextChanged = {},
                labelText = "Password",
                placeholderText = "Enter your password",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(text = "login", onClick = { /*TODO*/ })

            Spacer(modifier = Modifier.height(8.dp))

            SecondaryButton(text = "sign up", onClick = { /*TODO*/ })

            Spacer(modifier = Modifier.height(12.dp))
        }
        if (state.isSubmitting) {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
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
fun LoginContentPreview() {
    JetDoTheme {
        LoginContent(state = LoginViewState(isSubmitting = true))
    }
}
