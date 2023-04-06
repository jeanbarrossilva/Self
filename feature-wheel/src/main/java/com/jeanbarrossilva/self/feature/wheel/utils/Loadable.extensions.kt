package com.jeanbarrossilva.self.feature.wheel.utils

import com.jeanbarrossilva.loadable.Loadable
import java.io.Serializable
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/** Whether this is [loading][Loadable.Loading]. **/
@OptIn(ExperimentalContracts::class)
internal fun <T : Serializable?> Loadable<T>.isLoading(): Boolean {
    contract { returns(true) implies (this@isLoading is Loadable.Loading) }
    return this is Loadable.Loading
}
