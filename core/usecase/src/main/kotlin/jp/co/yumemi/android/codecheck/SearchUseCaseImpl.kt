package jp.co.yumemi.android.codecheck

class SearchUseCaseImpl(
    private val searchUseCaseQueryService: SearchUseCaseQueryService,
) : SearchUseCase {
    override suspend fun getGithubRepos(
        queryString: String,
    ): Maybe<List<GithubRepoUseCaseModel>, SearchUseCaseException> =
        searchUseCaseQueryService
            .get(queryString)
            .mapBoth(
                success = { githubRepos -> githubRepos.map { it.toUseCaseModel() } },
                failure = { it.toSearchUseCaseException() }
            )

    private fun SearchUseCaseQueryServiceException.toSearchUseCaseException() = when (this) {
        is SearchUseCaseQueryServiceException.ConnectionError ->
            SearchUseCaseException.SystemError(
                message = this.message,
                cause = this,
            )
    }
}
