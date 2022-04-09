package jp.co.yumemi.android.codecheck

interface SearchUseCaseQueryService {
    suspend fun get(queryString: String): Maybe<List<GithubRepo>, SearchUseCaseQueryServiceException>
}

sealed class SearchUseCaseQueryServiceException : Throwable() {
    class ConnectionError(override val message: String) : SearchUseCaseQueryServiceException()
}
