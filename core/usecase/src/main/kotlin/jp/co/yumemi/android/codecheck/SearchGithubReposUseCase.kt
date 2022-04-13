package jp.co.yumemi.android.codecheck

interface SearchGithubReposUseCase {
    suspend fun get(
        queryString: String,
    ): Maybe<List<GithubRepoUseCaseModel>, SearchGithubReposUseCaseException>
}

sealed class SearchGithubReposUseCaseException : Exception() {
    data class ConnectionException(
        override val message: String,
    ) : SearchGithubReposUseCaseException()
}
