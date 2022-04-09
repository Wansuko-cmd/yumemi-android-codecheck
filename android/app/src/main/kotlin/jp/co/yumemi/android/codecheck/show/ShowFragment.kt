/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck.show

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import jp.co.yumemi.android.codecheck.R
import jp.co.yumemi.android.codecheck.TopActivity.Companion.lastSearchDate
import jp.co.yumemi.android.codecheck.databinding.FragmentShowBinding
import jp.co.yumemi.android.codecheck.utils.GithubRepoUiState

class ShowFragment : Fragment(R.layout.fragment_show) {

    private val args: ShowFragmentArgs by navArgs()
    private val githubRepo: GithubRepoUiState by lazy { args.githubRepo }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("検索した日時", lastSearchDate.toString())

        val binding = FragmentShowBinding.bind(view)

        binding.ownerIconView.load(githubRepo.ownerIconUrl)
        binding.nameView.text = githubRepo.name
        binding.languageView.text = githubRepo.language
        binding.starsView.text = "${githubRepo.stargazersCount} stars"
        binding.watchersView.text = "${githubRepo.watchersCount} watchers"
        binding.forksView.text = "${githubRepo.forksCount} forks"
        binding.openIssuesView.text = "${githubRepo.openIssuesCount} open issues"
    }
}
