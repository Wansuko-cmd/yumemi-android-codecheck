package jp.co.yumemi.android.codecheck.index

import jp.co.yumemi.android.codecheck.SearchGithubReposUseCaseException
import jp.co.yumemi.android.codecheck.utils.GithubRepoUiState
import jp.co.yumemi.android.codecheck.utils.State

data class IndexUiState(
    val githubRepos: State<List<GithubRepoUiState>, IndexErrorUiState> = State.Loading,
) {
    fun copyWithGithubRepos(
        githubRepos: State<List<GithubRepoUiState>, IndexErrorUiState>,
    ) = this.copy(githubRepos = githubRepos)
}

data class IndexErrorUiState(
    val onSearchGithub: SearchGithubReposUseCaseException,
)
