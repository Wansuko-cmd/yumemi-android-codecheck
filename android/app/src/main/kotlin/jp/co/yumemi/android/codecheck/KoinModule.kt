package jp.co.yumemi.android.codecheck

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import jp.co.yumemi.android.codecheck.index.IndexViewModel
import jp.co.yumemi.android.codecheck.infra.SearchGithubReposUseCaseQueryServiceImpl
import jp.co.yumemi.android.codecheck.show.ShowViewModel
import jp.co.yumemi.android.codecheck.utils.GithubRepoUiState
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {

    /*** ViewModel ***/
    viewModel { IndexViewModel(androidApplication(), get()) }
    viewModel { (githubRepo: GithubRepoUiState) -> ShowViewModel(githubRepo) }

    /*** UseCase ***/
    single<SearchGithubReposUseCase> { SearchGithubReposUseCaseImpl(get()) }

    /*** QueryService ***/
    single<SearchGithubReposUseCaseQueryService> { SearchGithubReposUseCaseQueryServiceImpl(get()) }

    /*** Engine ***/
    single<HttpClientEngine> { Android.create() }
}
