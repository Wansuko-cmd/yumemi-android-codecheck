@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package jp.co.yumemi.android.codecheck.utils

import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
import org.junit.Test

class StateConsumeTest {

    @Test
    fun 型がSuccessの時はsuccessを実行() {
        val mockedState = State.Success("mockedSuccess")
        mockedState.consume(
            success = { assertThat(it).isEqualTo("mockedSuccess") },
            failure = { fail("failureの方を実行") },
            loading = { fail("loadingの方を実行") },
        )
    }

    @Test
    fun 型がFailureの時はfailureを実行() {
        val mockedState = State.Failure("mockedFailure")
        mockedState.consume(
            success = { fail("successの方を実行") },
            failure = { assertThat(it).isEqualTo("mockedFailure") },
            loading = { fail("loadingの方を実行") },
        )
    }

    @Test
    fun 型がLoadingの時はloadingを実行() {
        val mockedState = State.Loading
        mockedState.consume(
            success = { fail("successの方を実行") },
            failure = { fail("failureの方を実行") },
            loading = { assertTrue(true) },
        )
    }
}
