package com.roblesdotdev.jetdo.login.ui

import com.roblesdotdev.jetdo.core.utils.UIText
import com.roblesdotdev.jetdo.login.domain.model.Credentials

/*
 * A sealed class defining all possible states of our login screen.
 *
 * @property[credentials] The current credentials entered by the user.
 * @property[inputEnabled] If true, the buttons on the login screen can accept clicks.
 */
sealed class LoginViewState(
    open val credentials: Credentials,
    open val inputsEnabled: Boolean = true
) {
    /*
     *  The initial state of the screen with nothing input.
     */
    data object Initial : LoginViewState(
        credentials = Credentials()
    )

    /*
     * The state of the application when user is entering information.
     */
    data class Active(
        override val credentials: Credentials,
        val emailInputErrorMessage: UIText? = null,
        val passwordInputErrorMessage: UIText? = null
    ) : LoginViewState(
        credentials = credentials
    )

    /*
     * The state of the screen as the user is attempting to login.
     */
    data class Submitting(
        override val credentials: Credentials
    ) : LoginViewState(
        credentials = credentials,
        inputsEnabled = false
    )

    /*
     * The state of the screen when there is an error logging in.
     */
    data class SubmissionError(
        override val credentials: Credentials,
        val errorMessage: UIText
    ) : LoginViewState(
        credentials = credentials
    )

    /*
     * The state of the screen when logging is completed.
     */
    data object Completed : LoginViewState(
        credentials = Credentials(),
        inputsEnabled = false
    )
}
