package jp.co.yumemi.android.codecheck

interface SearchGithubReposUseCase {
    suspend fun get(
        queryString: String,
    ): Maybe<List<GithubRepoUseCaseModel>, SearchGithubReposUseCaseException>
}

sealed class SearchGithubReposUseCaseException : Exception() {
    class SystemError(
        override val message: String,
        override val cause: Throwable,
    ) : SearchGithubReposUseCaseException()
}
