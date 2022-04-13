package jp.co.yumemi.android.codecheck.utils

import jp.co.yumemi.android.codecheck.Maybe

sealed class State<out T, out E> {
    object Loading : State<Nothing, Nothing>()
    data class Success<T>(val value: T) : State<T, Nothing>()
    data class Failure<E>(val value: E) : State<Nothing, E>()
}

inline fun <T, E> State<T, E>.consume(
    success: (T) -> Unit = {},
    failure: (E) -> Unit = {},
    loading: () -> Unit = {},
) {
    when (this) {
        is State.Success -> success(value)
        is State.Failure -> failure(value)
        is State.Loading -> loading()
    }
}

fun <T, E> Maybe<T, E>.asState(): State<T, E> = when (this) {
    is Maybe.Success -> State.Success(value)
    is Maybe.Failure -> State.Failure(value)
}
