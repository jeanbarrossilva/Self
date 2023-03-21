package com.jeanbarrossilva.self.app

import android.app.Activity
import android.os.Bundle

internal class SelfActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_self)
    }
}
