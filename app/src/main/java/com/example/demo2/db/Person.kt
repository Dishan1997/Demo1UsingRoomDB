package com.example.demo2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_data_table")
data class Person(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var id: Int,

    @ColumnInfo(name = "Name")
    var name: String,

    @ColumnInfo(name = "Weight")
    val weight: Int,

    @ColumnInfo(name = "Height")
    val height: Int,

    @ColumnInfo(name = "BMI")
    val bmi: Float
)
