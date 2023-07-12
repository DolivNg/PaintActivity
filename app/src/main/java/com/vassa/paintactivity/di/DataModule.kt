package com.vassa.paintactivity.di


import android.app.Application
import androidx.room.Room
import com.vassa.paintactivity.data.DataBaseApp
import com.vassa.paintactivity.data.repository.DataBaseRepositoryImpl
import com.vassa.paintactivity.domain.repositories.DataBaseRepository
import org.koin.dsl.module
/**
 * @author Vassa
 * Version 1.2
 * 06.07.2023
 * */
val dataModule = module {

    fun provideDatabase(application: Application): DataBaseApp {
        return Room.databaseBuilder(application, DataBaseApp::class.java, "game.db")
            .createFromAsset("database/paintDataBase.db")
            .build()
    }

//    fun provideCountriesDao(database: CountriesDatabase): CountriesDao {
//        return  database.countriesDao
//    }

    single { provideDatabase(get()) }
    //single { provideCountriesDao(get()) }


    single<DataBaseRepository>{
        DataBaseRepositoryImpl(context = get(), get())
    }

}