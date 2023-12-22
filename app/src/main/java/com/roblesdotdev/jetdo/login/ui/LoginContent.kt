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
import androidx.compose.material3.Text
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
import com.roblesdotdev.jetdo.core.utils.UIText
import com.roblesdotdev.jetdo.core.utils.getString
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
    val isSubmitting = state is LoginViewState.Submitting
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
                onTextChanged = onEmailChanged,
                errorMessage = (state as? LoginViewState.Active)?.emailInputErrorMessage?.getString(),
                enabled = !isSubmitting
            )

            Spacer(modifier = Modifier.height(8.dp))

            PasswordField(
                text = state.credentials.password.value,
                onTextChanged = onPasswordChanged,
                errorMessage = (state as? LoginViewState.Active)?.passwordInputErrorMessage?.getString(),
                enabled = !isSubmitting
            )

            if (state is LoginViewState.SubmissionError) {
                Text(
                    text = state.errorMessage.getString(),
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            LoginButton(
                onClick = onLoginClicked,
                enabled = !isSubmitting
            )

            Spacer(modifier = Modifier.height(8.dp))

            SignUpButton(
                onClick = onSignUpClicked,
                enabled = !isSubmitting
            )

            Spacer(modifier = Modifier.height(12.dp))
        }
        if (isSubmitting) {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun SignUpButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    SecondaryButton(
        text = "sign up",
        onClick = onClick,
        enabled = enabled
    )
}

@Composable
private fun LoginButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    PrimaryButton(
        text = "login",
        onClick = onClick,
        enabled = enabled
    )
}

@Composable
private fun PasswordField(
    text: String,
    onTextChanged: (String) -> Unit,
    errorMessage: String?,
    enabled: Boolean
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
        visualTransformation = PasswordVisualTransformation(),
        errorMessage = errorMessage,
        enabled = enabled
    )
}

@Composable
private fun EmailField(
    text: String,
    onTextChanged: (String) -> Unit,
    enabled: Boolean,
    errorMessage: String?
) {
    AppTextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = "Email",
        placeholderText = "Enter your email",
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        errorMessage = errorMessage,
        enabled = enabled
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
                password = Password(value = "testPassword")
            )

            return sequenceOf(
                LoginViewState.Initial,
                LoginViewState.Active(activeCredentials),
                LoginViewState.Active(
                    Credentials(),
                    emailInputErrorMessage = UIText.ResourceText(R.string.error_empty_email),
                    passwordInputErrorMessage = UIText.ResourceText(R.string.error_empty_password)
                ),
                LoginViewState.Submitting(activeCredentials),
                LoginViewState.SubmissionError(
                    credentials = activeCredentials,
                    errorMessage = UIText.ResourceText(R.string.error_login_failure)
                ),
                LoginViewState.Completed
            )
        }
}
