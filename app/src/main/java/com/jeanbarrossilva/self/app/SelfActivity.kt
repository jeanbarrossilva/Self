package com.jeanbarrossilva.self.app

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

internal class SelfActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_self)
    }
}
