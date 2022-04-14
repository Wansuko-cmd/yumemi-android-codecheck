@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package jp.co.yumemi.android.codecheck

import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.fail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchGithubReposUseCaseImplTest {

    @MockK
    private lateinit var queryService: SearchGithubReposUseCaseQueryService
    private lateinit var target: SearchGithubReposUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        target = SearchGithubReposUseCaseImpl(queryService)
    }

    @Test
    fun queryStringを渡すと検索にかかったGithubRepoを返す() = runTest {

        val mockedQueryString = "mockedQueryString"

        val mockedGithubRepos = List(5) { index ->
            GithubRepo(
                name = Name("mockedName$index"),
                ownerIconUrl = OwnerIconUrl("mockedOwnerIconUrl$index"),
                language = Language("mockedLanguage$index"),
                stargazersCount = StargazersCount(0),
                watchersCount = WatchersCount(0),
                forksCount = ForksCount(0),
                openIssuesCount = OpenIssuesCount(0),
            )
        }

        coEvery {
            queryService.get(mockedQueryString)
        } returns Maybe.Success(mockedGithubRepos)

        val actual = target.get(mockedQueryString)
        val expected = mockedGithubRepos
            .map { it.toUseCaseModel() }
            .let { Maybe.Success(it) }

        assertThat(actual).isEqualTo(expected)

        coVerify(exactly = 1) { queryService.get(mockedQueryString) }
        confirmVerified(queryService)
    }

    @Test
    fun 取得するときにExceptionが帰ればSearchGithubRepoUseCaseExceptionに切り替えて返す() = runTest {
        val mockedQueryString = "mockedQueryString"

        coEvery {
            queryService.get(mockedQueryString)
        } returns Maybe.Failure(SearchGithubReposUseCaseQueryServiceException.ConnectionException(""))

        val actual = target.get(mockedQueryString)
        val expected = Maybe.Failure(SearchGithubReposUseCaseException.ConnectionException(""))

        assertThat(actual).isEqualTo(expected)
        coVerify(exactly = 1) { queryService.get(mockedQueryString) }
        confirmVerified(queryService)
    }

    @Test
    fun 取得する時にErrorが帰ればその値をThrowする() = runTest {
        val mockedQueryString = "mockedQueryString"
        val mockedMessage = "mockedMessage"

        coEvery {
            queryService.get(mockedQueryString)
        } returns Maybe.Failure(
            SearchGithubReposUseCaseQueryServiceException.SystemError(mockedMessage, Exception())
        )

        try {
            target.get(mockedQueryString)
            fail()
        } catch (e: Exception) {
            assertThat(e).hasMessageThat().isEqualTo(mockedMessage)
        }
    }
}
