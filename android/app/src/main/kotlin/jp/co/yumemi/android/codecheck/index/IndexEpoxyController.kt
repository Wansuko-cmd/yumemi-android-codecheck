package jp.co.yumemi.android.codecheck.index

import com.airbnb.epoxy.TypedEpoxyController
import jp.co.yumemi.android.codecheck.indexGithubRepoRow
import jp.co.yumemi.android.codecheck.utils.GithubRepoUiState

class IndexEpoxyController : TypedEpoxyController<List<GithubRepoUiState>>() {
    override fun buildModels(githubRepos: List<GithubRepoUiState>) {
        githubRepos.forEach { githubRepo ->
            indexGithubRepoRow {
                id(this@IndexEpoxyController.modelCountBuiltSoFar)
                name(githubRepo.name)
            }
        }
    }
}
