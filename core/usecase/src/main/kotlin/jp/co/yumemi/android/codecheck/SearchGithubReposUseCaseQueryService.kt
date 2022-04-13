package jp.co.yumemi.android.codecheck

interface SearchGithubReposUseCaseQueryService {
    suspend fun get(queryString: String): Maybe<List<GithubRepo>, SearchGithubReposUseCaseQueryServiceException>
}

sealed class SearchGithubReposUseCaseQueryServiceException : Exception() {
    class ConnectionException(override val message: String) : SearchGithubReposUseCaseQueryServiceException()
    class SystemError(
        override val message: String,
        override val cause: Throwable,
    ) : SearchGithubReposUseCaseQueryServiceException()
}
