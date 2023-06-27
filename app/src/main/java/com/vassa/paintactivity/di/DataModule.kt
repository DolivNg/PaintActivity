package com.vassa.paintactivity.di


import android.app.Application
import org.koin.dsl.module

val dataModule = module {

    /*fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "question.db")
            .createFromAsset("database/questionDB.db")
            .build()
    }*/

//    fun provideCountriesDao(database: CountriesDatabase): CountriesDao {
//        return  database.countriesDao
//    }

    /*single { provideDatabase(get()) }
    //single { provideCountriesDao(get()) }


    single<QuestionRepository>{
        QuestionRepositoryImpl(context = get(), db = get())
    }*/

}