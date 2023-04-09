package com.jeanbarrossilva.self.feature.wheel.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

internal fun EditText.doOnTextChanged(block: (text: String) -> Unit) {
    addTextChangedListener(
        object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                removeTextChangedListener(this)
                s?.toString()?.run(block)
                addTextChangedListener(this)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    )
}
