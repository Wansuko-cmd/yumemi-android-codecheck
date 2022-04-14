@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package com.wsr.maybe

import com.google.common.truth.Truth.assertThat
import jp.co.yumemi.android.codecheck.Maybe
import jp.co.yumemi.android.codecheck.map
import junit.framework.TestCase.fail
import org.junit.Test

class MaybeMapTest {

    /*** mapBoth関数 ***/
    @Test
    fun 型がSuccessの時はラムダ式を実行して結果を返す() {
        val mockedMaybe = Maybe.Success("mockedSuccess")
        val updatedMockedMaybe = mockedMaybe.map { "updatedSuccess" }

        val expected = Maybe.Success("updatedSuccess")

        assertThat(updatedMockedMaybe).isEqualTo(expected)
    }

    @Test
    fun 型がFailureの時はそのままの結果を返す() {
        val mockedMaybe = Maybe.Failure("mockedFailure")
        val updatedMockedMaybe = mockedMaybe.map { fail("mapの中身を実行") }

        val expected = Maybe.Failure("mockedFailure")

        assertThat(updatedMockedMaybe).isEqualTo(expected)
    }
}
