package jp.co.yumemi.android.codecheck.infra

import android.os.Parcelable
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import jp.co.yumemi.android.codecheck.ForksCount
import jp.co.yumemi.android.codecheck.GithubRepo
import jp.co.yumemi.android.codecheck.Language
import jp.co.yumemi.android.codecheck.Maybe
import jp.co.yumemi.android.codecheck.Name
import jp.co.yumemi.android.codecheck.OpenIssuesCount
import jp.co.yumemi.android.codecheck.OwnerIconUrl
import jp.co.yumemi.android.codecheck.SearchUseCaseQueryService
import jp.co.yumemi.android.codecheck.SearchUseCaseQueryServiceException
import jp.co.yumemi.android.codecheck.StargazersCount
import jp.co.yumemi.android.codecheck.WatchersCount
import kotlinx.parcelize.Parcelize

class SearchUseCaseQueryServiceImpl : SearchUseCaseQueryService {
    override suspend fun get(
        queryString: String,
    ): Maybe<List<GithubRepo>, SearchUseCaseQueryServiceException> = try {
        val client = HttpClient(Android)
        client.get<List<GithubRepoParcelable>>("https://api.github.com/search/repositories") {
            header("Accept", "application/vnd.github.v3+json")
            parameter("q", queryString)
        }
            .map { it.toGithubRepo() }
            .let { Maybe.Success(it) }
    } catch (e: Exception) {
        Maybe.Failure(SearchUseCaseQueryServiceException.ConnectionError(e.message.orEmpty()))
    }
}

@Parcelize
data class GithubRepoParcelable(
    val name: String,
    val ownerIconUrl: String,
    val language: String,
    val stargazersCount: Long,
    val watchersCount: Long,
    val forksCount: Long,
    val openIssuesCount: Long,
) : Parcelable {
    fun toGithubRepo() = GithubRepo(
        name = Name(name),
        ownerIconUrl = OwnerIconUrl(ownerIconUrl),
        language = Language(language),
        stargazersCount = StargazersCount(stargazersCount),
        watchersCount = WatchersCount(watchersCount),
        forksCount = ForksCount(forksCount),
        openIssuesCount = OpenIssuesCount(openIssuesCount),
    )
}
