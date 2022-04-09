package jp.co.yumemi.android.codecheck.infra

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
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
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class SearchUseCaseQueryServiceImpl : SearchUseCaseQueryService {
    override suspend fun get(
        queryString: String,
    ): Maybe<List<GithubRepo>, SearchUseCaseQueryServiceException> = try {
        val client = HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                        coerceInputValues = true
                    }
                )
            }
        }
        client.get<GithubRepoSerializable>("https://api.github.com/search/repositories") {
            header("Accept", "application/vnd.github.v3+json")
            parameter("q", queryString)
        }
            .items
            .map { it.toGithubRepo() }
            .let { Maybe.Success(it) }
    } catch (e: Exception) {
        Maybe.Failure(SearchUseCaseQueryServiceException.ConnectionError(e.message.orEmpty()))
    }
}

@Serializable
data class GithubRepoSerializable(
    val items: List<GithubRepoItem>
)

@Serializable
data class GithubRepoItem(
    @SerialName("full_name") val name: String,
    @SerialName("owner") val owner: GithubRepoOwner,
    val language: String = "",
    @SerialName("stargazers_count") val stargazersCount: Long,
    @SerialName("watchers_count") val watchersCount: Long,
    @SerialName("forks_count") val forksCount: Long,
    @SerialName("open_issues_count") val openIssuesCount: Long,
) {
    fun toGithubRepo() = GithubRepo(
        name = Name(name),
        ownerIconUrl = OwnerIconUrl(owner.avatarUrl),
        language = Language(language),
        stargazersCount = StargazersCount(stargazersCount),
        watchersCount = WatchersCount(watchersCount),
        forksCount = ForksCount(forksCount),
        openIssuesCount = OpenIssuesCount(openIssuesCount),
    )
}

@Serializable
data class GithubRepoOwner(
    @SerialName("avatar_url") val avatarUrl: String,
)