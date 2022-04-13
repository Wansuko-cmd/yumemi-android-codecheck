package jp.co.yumemi.android.codecheck

class SearchGithubReposUseCaseImpl(
    private val searchUseCaseQueryService: SearchGithubReposUseCaseQueryService,
) : SearchGithubReposUseCase {
    override suspend fun get(
        queryString: String,
    ): Maybe<List<GithubRepoUseCaseModel>, SearchGithubReposUseCaseException> =
        searchUseCaseQueryService
            .get(queryString)
            .mapBoth(
                success = { githubRepos -> githubRepos.map { it.toUseCaseModel() } },
                failure = { it.toSearchGithubReposUseCaseException() }
            )

    private fun SearchGithubReposUseCaseQueryServiceException.toSearchGithubReposUseCaseException() = when (this) {
        is SearchGithubReposUseCaseQueryServiceException.ConnectionError ->
            SearchGithubReposUseCaseException.SystemError(
                message = this.message,
                cause = this,
            )
    }
}
