package com.vassa.paintactivity.di


import android.app.Application
import androidx.room.Room
import com.vassa.paintactivity.data.DataBaseApp
import com.vassa.paintactivity.data.repository.DataBaseRepositoryImpl
import com.vassa.paintactivity.data.repository.SocketRepositoryImpl
import com.vassa.paintactivity.domain.repositories.DataBaseRepository
import com.vassa.paintactivity.domain.repositories.SocketRepository
import io.socket.client.IO
import io.socket.client.Socket
import org.koin.dsl.module
import java.net.URISyntaxException


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

    fun provideSocket(): Socket {
        return  IO.socket("http://10.1.1.7:3000")// http://192.168.88.26:3000
    }

//    fun provideCountriesDao(database: CountriesDatabase): CountriesDao {
//        return  database.countriesDao
//    }
    single {provideSocket()}
    single { provideDatabase(get()) }
    //single { provideCountriesDao(get()) }

    single<SocketRepository>{
        SocketRepositoryImpl(get())
    }


    single<DataBaseRepository>{
        DataBaseRepositoryImpl(context = get(), get())
    }

}