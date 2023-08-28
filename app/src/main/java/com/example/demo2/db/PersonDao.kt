package com.example.demo2.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {
    @Insert
    suspend fun insertData(person : Person)

    @Query("select * from person_data_table")
    fun getAllPersonData() : List<Person>

}
