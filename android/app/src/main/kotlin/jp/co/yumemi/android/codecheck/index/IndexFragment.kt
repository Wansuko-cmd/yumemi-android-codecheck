/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck.index

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.co.yumemi.android.codecheck.R
import jp.co.yumemi.android.codecheck.databinding.FragmentIndexBinding
import jp.co.yumemi.android.codecheck.utils.GithubRepoUiState
import jp.co.yumemi.android.codecheck.utils.consume
import jp.co.yumemi.android.codecheck.utils.ext.launchInLifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class IndexFragment : Fragment(R.layout.fragment_index) {

    private val indexViewModel by viewModel<IndexViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentIndexBinding.bind(view)

        binding.indexSearchInputText
            .setOnEditorActionListener { editText, action, _ ->
                if (action == EditorInfo.IME_ACTION_SEARCH) {
                    editText.text.toString().let {
                        indexViewModel.fetch(it)
                    }
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }

        val indexEpoxyController = IndexEpoxyController(::gotoRepositoryFragment)

        binding.indexRecyclerView.apply {
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
            adapter = indexEpoxyController.adapter
        }

        launchInLifecycleScope(Lifecycle.State.STARTED) {
            indexViewModel.uiState.collect { indexUiState ->
                indexUiState.githubRepos.consume(
                    success = { indexEpoxyController.setData(it) },
                    failure = { Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show() },
                    loading = {},
                )
            }
        }
    }

    fun gotoRepositoryFragment(githubRepo: GithubRepoUiState) {
        val action = IndexFragmentDirections
            .actionRepositoriesFragmentToRepositoryFragment(githubRepo = githubRepo)
        findNavController().navigate(action)
    }
}
