package jp.co.yumemi.android.codecheck

import jp.co.yumemi.android.codecheck.index.IndexViewModel
import jp.co.yumemi.android.codecheck.infra.SearchUseCaseQueryServiceImpl
import jp.co.yumemi.android.codecheck.show.ShowViewModel
import jp.co.yumemi.android.codecheck.utils.GithubRepoUiState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {

    /*** ViewModel ***/
    viewModel { IndexViewModel(get()) }
    viewModel { (githubRepo: GithubRepoUiState) -> ShowViewModel(githubRepo) }

    /*** UseCase ***/
    single<SearchUseCase> { SearchUseCaseImpl(get()) }

    /*** QueryService ***/
    single<SearchUseCaseQueryService> { SearchUseCaseQueryServiceImpl() }
}
