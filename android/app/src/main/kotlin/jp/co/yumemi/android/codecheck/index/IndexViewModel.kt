/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck.index

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.android.codecheck.SearchUseCase
import jp.co.yumemi.android.codecheck.mapBoth
import jp.co.yumemi.android.codecheck.utils.GithubRepoUiState.Companion.toUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class IndexViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(IndexUiState())
    val uiState get() = _uiState.asStateFlow()

    fun fetch(queryString: String) {
        viewModelScope.launch { _uiState.emit(IndexUiState()) }
        fetchGithubRepo(queryString)
    }

    private fun fetchGithubRepo(queryString: String) {
        viewModelScope.launch {
            searchUseCase
                .getGithubRepos(queryString)
                .mapBoth(
                    success = { githubRepos -> githubRepos.map { it.toUiState() } },
                    failure = { IndexErrorUiState(it.message.orEmpty()) }
                )
        }
    }
}
