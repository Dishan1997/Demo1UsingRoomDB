package com.example.demo2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Person :: class], version = 1, exportSchema = false)
abstract class PersonDatabase :RoomDatabase (){

    abstract fun personDao() : PersonDao

    companion object{
        @Volatile
        private var INSTANCE: PersonDatabase? = null
        fun getInstance(context: Context) : PersonDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PersonDatabase :: class.java,
                        "person_data_table"
                    ).build()
                }
                return instance
            }
        }
    }

}
