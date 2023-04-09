package com.jeanbarrossilva.self.feature.wheel.scope.todo.ui.input.text

internal abstract class TextFieldValidator {
    private var isEnabled = false

    protected abstract val message: String

    protected abstract fun onValidate(value: String): Boolean

    fun enable(value: String): TextFieldState {
        isEnabled = true
        return validate(value)
    }

    fun validate(value: String): TextFieldState {
        return when {
            !isEnabled -> TextFieldState.Idle
            onValidate(value) -> TextFieldState.Valid
            else -> TextFieldState.Invalid(message)
        }
    }
}
