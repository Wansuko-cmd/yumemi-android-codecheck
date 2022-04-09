package jp.co.yumemi.android.codecheck

interface SearchUseCase {
    suspend fun getGithubRepos(
        queryString: String,
    ): Maybe<List<GithubRepoUseCaseModel>, SearchUseCaseException>
}

sealed class SearchUseCaseException : Throwable() {
    class SystemError(
        override val message: String,
        override val cause: Throwable,
    ) : SearchUseCaseException()
}
