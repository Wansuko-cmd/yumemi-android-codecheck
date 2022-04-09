package jp.co.yumemi.android.codecheck

import jp.co.yumemi.android.codecheck.index.IndexViewModel
import jp.co.yumemi.android.codecheck.infra.SearchUseCaseQueryServiceImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {

    /*** ViewModel ***/
    viewModel { IndexViewModel(get()) }

    /*** UseCase ***/
    single<SearchUseCase> { SearchUseCaseImpl(get()) }

    /*** QueryService ***/
    single<SearchUseCaseQueryService> { SearchUseCaseQueryServiceImpl() }
}
