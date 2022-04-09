package jp.co.yumemi.android.codecheck.utils

import android.os.Parcelable
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
) : Parcelable
