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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.roblesdotdev.jetdo.R
import com.roblesdotdev.jetdo.core.ui.components.AppTextField
import com.roblesdotdev.jetdo.core.ui.components.PrimaryButton
import com.roblesdotdev.jetdo.core.ui.components.SecondaryButton
import com.roblesdotdev.jetdo.core.ui.theme.JetDoTheme
import com.roblesdotdev.jetdo.login.domain.model.Credentials
import com.roblesdotdev.jetdo.login.domain.model.Email
import com.roblesdotdev.jetdo.login.domain.model.Password

@Composable
fun LoginContent(
    state: LoginViewState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onSignUpClicked: () -> Unit
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

            EmailField(
                text = state.credentials.email.value,
                onTextChanged = onEmailChanged
            )

            Spacer(modifier = Modifier.height(8.dp))

            PasswordField(
                text = state.credentials.password.value,
                onTextChanged = onPasswordChanged
            )

            Spacer(modifier = Modifier.height(48.dp))

            LoginButton(onClick = onLoginClicked)

            Spacer(modifier = Modifier.height(8.dp))

            SignUpButton(onClick = onSignUpClicked)

            Spacer(modifier = Modifier.height(12.dp))
        }
        if (!state.inputsEnabled) {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun SignUpButton(onClick: () -> Unit) {
    SecondaryButton(text = "sign up", onClick = onClick)
}

@Composable
private fun LoginButton(onClick: () -> Unit) {
    PrimaryButton(text = "login", onClick = onClick)
}

@Composable
private fun PasswordField(
    text: String,
    onTextChanged: (String) -> Unit
) {
    AppTextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = "Password",
        placeholderText = "Enter your password",
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
private fun EmailField(
    text: String,
    onTextChanged: (String) -> Unit
) {
    AppTextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = "Email",
        placeholderText = "Enter your email",
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
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
fun LoginContentPreview(
    @PreviewParameter(provider = LoginViewStateProvider::class)
    loginViewState: LoginViewState
) {
    JetDoTheme {
        LoginContent(
            state = loginViewState,
            onEmailChanged = {},
            onPasswordChanged = {},
            onLoginClicked = {},
            onSignUpClicked = {}
        )
    }
}

class LoginViewStateProvider : PreviewParameterProvider<LoginViewState> {
    override val values: Sequence<LoginViewState>
        get() {
            val activeCredentials = Credentials(
                email = Email(value = "test@email.com"),
                password = Password(value = "testpassword")
            )

            return sequenceOf(
                LoginViewState.Initial,
                LoginViewState.Active(activeCredentials),
                LoginViewState.Submitting(activeCredentials),
                LoginViewState.SubmissionError(
                    credentials = activeCredentials,
                    errorMessage = "Something went wrong"
                ),
                LoginViewState.Completed
            )
        }
}
