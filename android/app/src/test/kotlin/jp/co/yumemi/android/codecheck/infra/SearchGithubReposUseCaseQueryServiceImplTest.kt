@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package jp.co.yumemi.android.codecheck.infra

import com.google.common.truth.Truth.assertThat
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import jp.co.yumemi.android.codecheck.Maybe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchGithubReposUseCaseQueryServiceImplTest {

    @Test
    fun queryStringを渡すと検索にかかったGithubReposを返す() = runTest {
        val mockedGithubRepos = GithubRepoSerializable(
            items = List(5) { index ->
                GithubRepoItem(
                    name = "mockedName$index",
                    owner = GithubRepoOwner("mockedAvatarUrl$index"),
                    language = "mockedLanguage$index",
                    stargazersCount = 0,
                    watchersCount = 0,
                    forksCount = 0,
                    openIssuesCount = 0,
                )
            }
        )

        val mockEngine = MockEngine {
            respond(
                content = ByteReadChannel(Json.encodeToString(mockedGithubRepos)),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val mockedQueryString = "mockedQueryString"
        val target = SearchGithubReposUseCaseQueryServiceImpl(mockEngine)

        val actual = target.get(mockedQueryString)
        val expected = Maybe.Success(mockedGithubRepos.toGithubRepos())

        assertThat(actual).isEqualTo(expected)
    }
}
