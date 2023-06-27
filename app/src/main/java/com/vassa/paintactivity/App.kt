package com.vassa.paintactivity

import android.app.Application
import com.vassa.paintactivity.di.appModule
import com.vassa.paintactivity.di.dataModule
import com.vassa.paintactivity.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}