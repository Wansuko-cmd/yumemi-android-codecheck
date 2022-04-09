package jp.co.yumemi.android.codecheck

data class Repository(
    val name: Name,
    val ownerIconUrl: OwnerIconUrl,
    val language: Language,
    val stargazersCount: StargazersCount,
    val watchersCount: WatchersCount,
    val forksCount: ForksCount,
    val openIssuesCount: OpenIssuesCount,
)

@JvmInline
value class Name(val value: String)

@JvmInline
value class OwnerIconUrl(val value: String)

@JvmInline
value class Language(val value: String)

@JvmInline
value class StargazersCount(val value: Long)

@JvmInline
value class WatchersCount(val value: Long)

@JvmInline
value class ForksCount(val value: Long)

@JvmInline
value class OpenIssuesCount(val value: Long)
