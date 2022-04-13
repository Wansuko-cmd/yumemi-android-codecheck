@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package jp.co.yumemi.android.codecheck

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GithubRepoUseCaseModelTest {
    @Test
    fun GithubRepoから対応するUseCaseModelに変換して返す() {

        val mockedName = Name("mockedName")
        val mockedOwnerIconUrl = OwnerIconUrl("mockedOwnerIconUrl")
        val mockedLanguage = Language("mockedLanguage")
        val mockedStargazersCount = StargazersCount(0)
        val mockedWatchersCount = WatchersCount(0)
        val mockedForksCount = ForksCount(0)
        val mockedOpenIssuesCount = OpenIssuesCount(0)

        val mockedGithubRepo = GithubRepo(
            name = mockedName,
            ownerIconUrl = mockedOwnerIconUrl,
            language = mockedLanguage,
            stargazersCount = mockedStargazersCount,
            watchersCount = mockedWatchersCount,
            forksCount = mockedForksCount,
            openIssuesCount = mockedOpenIssuesCount,
        )

        val expected = GithubRepoUseCaseModel(
            name = mockedName.value,
            ownerIconUrl = mockedOwnerIconUrl.value,
            language = mockedLanguage.value,
            stargazersCount = mockedStargazersCount.value,
            watchersCount = mockedWatchersCount.value,
            forksCount = mockedForksCount.value,
            openIssuesCount = mockedOpenIssuesCount.value,
        )

        assertThat(mockedGithubRepo.toUseCaseModel()).isEqualTo(expected)
    }
}
