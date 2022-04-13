package jp.co.yumemi.android.codecheck

interface SearchGithubReposUseCaseQueryService {
    suspend fun get(queryString: String): Maybe<List<GithubRepo>, SearchGithubReposUseCaseQueryServiceException>
}

sealed class SearchGithubReposUseCaseQueryServiceException : Throwable() {
    class ConnectionError(override val message: String) : SearchGithubReposUseCaseQueryServiceException()
}
