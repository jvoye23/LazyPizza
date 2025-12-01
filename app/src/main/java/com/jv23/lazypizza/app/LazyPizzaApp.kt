package com.jv23.lazypizza.app

import android.app.Application
import com.google.firebase.FirebaseApp
import com.jv23.lazypizza.BuildConfig
import com.jv23.lazypizza.app.di.appModule
import com.jv23.lazypizza.core.coreDataModule
import com.jv23.lazypizza.core.database.di.databaseModule
import com.jv23.lazypizza.order_menu.di.homeModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class LazyPizzaApp: Application() {
    val applicationScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        if (BuildConfig.DEBUG) {
            Timber.Forest.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger()
            androidContext(this@LazyPizzaApp)
            modules(
                appModule,
                coreDataModule,
                homeModule,
                databaseModule
            )
        }
    }
}