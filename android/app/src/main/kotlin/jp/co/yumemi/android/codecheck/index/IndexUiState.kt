package jp.co.yumemi.android.codecheck.index

import jp.co.yumemi.android.codecheck.utils.GithubRepoUiState
import jp.co.yumemi.android.codecheck.utils.State

data class IndexUiState(
    val githubRepos: State<List<GithubRepoUiState>, IndexErrorUiState> = State.Loading,
)

data class IndexErrorUiState(val message: String)
