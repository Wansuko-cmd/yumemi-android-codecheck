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

    private val args: ShowFragmentArgs by navArgs()
    private val githubRepo: GithubRepoUiState by lazy { args.githubRepo }
    private val showViewModel: ShowViewModel by viewModel { parametersOf(githubRepo) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentShowBinding.bind(view)

        launchInLifecycleScope(Lifecycle.State.STARTED) {
            showViewModel.uiState.collect {
                it.githubRepoUiState.consume(
                    success = {
                        binding.apply {
                            ownerIconView.load(githubRepo.ownerIconUrl)
                            nameView.text = githubRepo.name
                            languageView.text = githubRepo.language
                            starsView.text = "${githubRepo.stargazersCount} stars"
                            watchersView.text = "${githubRepo.watchersCount} watchers"
                            forksView.text = "${githubRepo.forksCount} forks"
                            openIssuesView.text = "${githubRepo.openIssuesCount} open issues"
                        }
                    }
                )
            }
        }
        Log.d("検索した日時", Date(System.currentTimeMillis()).toString())
    }
}
