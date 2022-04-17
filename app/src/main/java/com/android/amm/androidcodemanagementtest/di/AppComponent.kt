package com.android.amm.androidcodemanagementtest.di

import com.android.amm.androidcodemanagementtest.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetModule::class,
        PersistenceModule::class,
        ViewModelFactoryModule::class,
        ActivityBindingModule::class
    ]
)

interface AppComponent: AndroidInjector<MainApplication> {
    @Component.Factory
    interface Factory {
        fun application(@BindsInstance app: MainApplication): AppComponent
    }
}