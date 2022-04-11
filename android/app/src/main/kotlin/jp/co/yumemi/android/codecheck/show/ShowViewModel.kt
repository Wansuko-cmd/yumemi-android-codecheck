package jp.co.yumemi.android.codecheck.show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.android.codecheck.utils.GithubRepoUiState
import jp.co.yumemi.android.codecheck.utils.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShowViewModel(githubRepoUiState: GithubRepoUiState) : ViewModel() {
    private val _uiState = MutableStateFlow(ShowUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copyWithGithubRepo(State.Success(githubRepoUiState)) }
        }
    }
}