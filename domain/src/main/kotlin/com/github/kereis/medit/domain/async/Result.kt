package com.github.kereis.medit.domain.async

sealed class Result<out R, out E> {

    data class Success<out T>(val result: T) : Result<T, Nothing>()

    data class Failure<out R : Error>(val error: R) : Result<Nothing, R>()

    sealed class State : Result<Nothing, Nothing>() {

        object Loading : State()

        object Loaded : State()

        object Done : State()
    }

    fun handleResult(
        onSuccess: (R) -> Unit = {},
        onFailure: (E) -> Unit = {},
        onState: (State) -> Unit = {},
    ) {
        when (this) {
            is Success -> onSuccess(result)
            is Failure -> onFailure(error)
            is State -> onState(this)
        }
    }
}
