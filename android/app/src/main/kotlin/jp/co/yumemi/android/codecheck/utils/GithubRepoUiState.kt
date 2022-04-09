package jp.co.yumemi.android.codecheck.utils

import android.os.Parcelable
import jp.co.yumemi.android.codecheck.GithubRepoUseCaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepoUiState(
    val name: String,
    val ownerIconUrl: String,
    val language: String,
    val stargazersCount: Long,
    val watchersCount: Long,
    val forksCount: Long,
    val openIssuesCount: Long,
) : Parcelable {
    companion object {
        fun GithubRepoUseCaseModel.toUiState() = GithubRepoUiState(
            name = name,
            ownerIconUrl = ownerIconUrl,
            language = language,
            stargazersCount = stargazersCount,
            watchersCount = watchersCount,
            forksCount = forksCount,
            openIssuesCount = openIssuesCount,
        )
    }
}
