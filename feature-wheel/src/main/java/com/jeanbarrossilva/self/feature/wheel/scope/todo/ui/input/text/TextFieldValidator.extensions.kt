package com.jeanbarrossilva.self.feature.wheel.scope.todo.ui.input.text

@Suppress("FunctionName")
internal fun EmptyTextFieldValidator(): TextFieldValidator {
    return TextFieldValidator("") {
        true
    }
}

internal fun TextFieldValidator(message: String, onValidate: (value: String) -> Boolean):
    TextFieldValidator {
    return object : TextFieldValidator() {
        override val message = message

        override fun onValidate(value: String): Boolean {
            return onValidate(value)
        }
    }
}
