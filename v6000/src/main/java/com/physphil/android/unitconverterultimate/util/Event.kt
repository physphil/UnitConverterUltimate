package com.physphil.android.unitconverterultimate.util

/**
 * Data that is exposed via a [LiveData] for single events.
 *
 * Following this approach:
 * https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 */
open class Event<T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getIfNotHandled(): T? =
        if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }

    fun peek(): T = content
}