/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck.show

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import coil.load
import jp.co.yumemi.android.codecheck.R
import jp.co.yumemi.android.codecheck.databinding.FragmentShowBinding
import jp.co.yumemi.android.codecheck.utils.GithubRepoUiState
import jp.co.yumemi.android.codecheck.utils.consume
import jp.co.yumemi.android.codecheck.utils.ext.launchInLifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.Date

class ShowFragment : Fragment(R.layout.fragment_show) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: ShowFragmentArgs by navArgs()
        val githubRepo: GithubRepoUiState by lazy { args.githubRepo }
        val showViewModel: ShowViewModel by viewModel { parametersOf(githubRepo) }

        val binding = FragmentShowBinding.bind(view)

        launchInLifecycleScope(Lifecycle.State.STARTED) {
            showViewModel.uiState.collect { showUiState ->
                showUiState.githubRepoUiState.consume(
                    success = { githubRepo -> binding.setGithubRepo(githubRepo) }
                )
            }
        }
        Log.d("検索した日時", Date(System.currentTimeMillis()).toString())
    }

    private fun FragmentShowBinding.setGithubRepo(githubRepo: GithubRepoUiState) {
        this.apply {
            showOwnerIconView.load(githubRepo.ownerIconUrl)

            showNameView.text = githubRepo.name

            showLanguageView.text = githubRepo.language
                ?: getString(R.string.show_language_not_found)

            showStarsView.text = githubRepo.stargazersCount
                ?.let { getString(R.string.show_stars_view_text, it) }
                ?: getString(R.string.show_stars_view_text_not_found)

            showWatchersView.text = githubRepo.watchersCount
                ?.let { getString(R.string.show_watchers_view_text, it) }
                ?: getString(R.string.show_watchers_view_text_not_found)

            showForksView.text = githubRepo.forksCount
                ?.let { getString(R.string.show_forks_view_text, it) }
                ?: getString(R.string.show_watchers_view_text_not_found)

            showOpenIssuesView.text = githubRepo.openIssuesCount
                ?.let { getString(R.string.show_open_issues_view_text, it) }
                ?: getString(R.string.show_open_issues_view_text_not_found)
        }
    }
}
