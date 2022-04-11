package jp.co.yumemi.android.codecheck.show

import jp.co.yumemi.android.codecheck.utils.GithubRepoUiState
import jp.co.yumemi.android.codecheck.utils.State

data class ShowUiState(
    val githubRepoUiState: State<GithubRepoUiState, ShowErrorUiState> = State.Loading,
) {
    fun copyWithGithubRepo(
        githubRepoUiState: State<GithubRepoUiState, ShowErrorUiState>,
    ) = this.copy(githubRepoUiState = githubRepoUiState)
}

data class ShowErrorUiState(val message: String)