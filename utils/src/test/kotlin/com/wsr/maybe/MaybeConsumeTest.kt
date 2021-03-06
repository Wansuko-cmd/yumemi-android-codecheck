@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package com.wsr.maybe

import com.google.common.truth.Truth.assertThat
import jp.co.yumemi.android.codecheck.Maybe
import jp.co.yumemi.android.codecheck.consume
import junit.framework.TestCase.fail
import org.junit.Test

class MaybeConsumeTest {

    @Test
    fun 型がSuccessの時はsuccessを実行() {
        val mockedMaybe = Maybe.Success("mockedSuccess")
        mockedMaybe.consume(
            success = { assertThat(it).isEqualTo("mockedSuccess") },
            failure = { fail("failureの方を実行") },
        )
    }

    @Test
    fun 型がFailureの時はfailureを実行() {
        val mockedMaybe = Maybe.Failure("mockedFailure")
        mockedMaybe.consume(
            success = { fail("successの方を実行") },
            failure = { assertThat(it).isEqualTo("mockedFailure") },
        )
    }
}
