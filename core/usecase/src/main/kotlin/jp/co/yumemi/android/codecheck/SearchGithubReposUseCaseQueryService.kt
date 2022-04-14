package jp.co.yumemi.android.codecheck

interface SearchGithubReposUseCaseQueryService {
    suspend fun get(queryString: String): Maybe<List<GithubRepo>, SearchGithubReposUseCaseQueryServiceException>
}

sealed class SearchGithubReposUseCaseQueryServiceException : Exception() {
    data class ConnectionException(override val message: String) : SearchGithubReposUseCaseQueryServiceException()
    data class ServerException(override val message: String) : SearchGithubReposUseCaseQueryServiceException()
    data class SystemError(
        override val message: String,
        override val cause: Throwable,
    ) : SearchGithubReposUseCaseQueryServiceException()
}
