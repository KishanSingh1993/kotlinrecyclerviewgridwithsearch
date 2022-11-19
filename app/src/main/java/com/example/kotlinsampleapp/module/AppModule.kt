package com.example.kotlinsampleapp.module

import com.example.kotlinsampleapp.MainViewModel
import com.example.kotlinsampleapp.utils.Utils
import org.koin.dsl.module.module


var appModule = module {
    single { Utils() }
    factory { MainViewModel(get()) }
}