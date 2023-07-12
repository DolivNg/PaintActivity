package com.vassa.paintactivity.di


import com.vassa.paintactivity.domain.usecase.GlobalProfileUseCase
import com.vassa.paintactivity.domain.usecase.LocalProfileUseCase
import com.vassa.paintactivity.domain.usecase.PackUseCase
import com.vassa.paintactivity.domain.usecase.WordUseCase
import org.koin.dsl.module
/**
 *@author Vassa
 * version 1.0
 * 08.07.2023
 * */
val domainModule = module {
    factory<GlobalProfileUseCase> {
        GlobalProfileUseCase(get())
    }

    factory<PackUseCase> {
        PackUseCase(get())
    }
    factory<WordUseCase> {
        WordUseCase(get())
    }
    factory<LocalProfileUseCase> {
        LocalProfileUseCase(get())
    }
}