package jp.co.yumemi.android.codecheck.utils

import android.os.Parcelable
import jp.co.yumemi.android.codecheck.GithubRepoUseCaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepoUiState(
    val name: String,
    val ownerIconUrl: String,
    val language: String,
    val stargazersCount: String,
    val watchersCount: String,
    val forksCount: String,
    val openIssuesCount: String,
) : Parcelable {
    companion object {
        fun GithubRepoUseCaseModel.toUiState() = GithubRepoUiState(
            name = name,
            ownerIconUrl = ownerIconUrl,
            language = language,
            stargazersCount = stargazersCount.toString(),
            watchersCount = watchersCount.toString(),
            forksCount = forksCount.toString(),
            openIssuesCount = openIssuesCount.toString(),
        )
    }
}
