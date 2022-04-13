package jp.co.yumemi.android.codecheck

data class GithubRepoUseCaseModel(
    val name: String,
    val ownerIconUrl: String?,
    val language: String?,
    val stargazersCount: Long?,
    val watchersCount: Long?,
    val forksCount: Long?,
    val openIssuesCount: Long?,
)

fun GithubRepo.toUseCaseModel() = GithubRepoUseCaseModel(
    name = name.value,
    ownerIconUrl = ownerIconUrl?.value,
    language = language?.value,
    stargazersCount = stargazersCount?.value,
    watchersCount = watchersCount?.value,
    forksCount = forksCount?.value,
    openIssuesCount = openIssuesCount?.value,
)
