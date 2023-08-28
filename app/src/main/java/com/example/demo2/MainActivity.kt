package com.example.demo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.demo2.db.Person
import com.example.demo2.db.PersonDao
import com.example.demo2.db.PersonDatabase
import com.example.demoproject1.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity() : AppCompatActivity() {

    lateinit var textResult : TextView
    lateinit var textInfo : TextView
    private var bmi2Digit : Float = 0.0f

    lateinit var height : String
    lateinit var name : String
    lateinit var weight : String
    var result : Float = 0.0f


    private lateinit var edHeight : EditText
    private lateinit var edWeight : EditText
    private lateinit var edName : EditText
    private lateinit var dao : PersonDao

    var cnt =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dao = PersonDatabase.getInstance(application).personDao()

        edHeight = findViewById<EditText>(R.id.edHeight)
        edWeight = findViewById<EditText>(R.id.edWeight)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        textResult = findViewById(R.id.txtResult)
        textInfo = findViewById(R.id.txtInfo)
        edName = findViewById<EditText>(R.id.edName)
        val btnHistory= findViewById<Button>(R.id.btnHistory)


        //starts the second activity
        btnHistory.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        btnSubmit.setOnClickListener {
            height = edHeight.text.toString()
            weight = edWeight.text.toString()
            name = edName.text.toString()

            if(validateInput(name, height, weight)) {

                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                bmi2Digit = String.format("%.2f", bmi).toFloat()
                when{
                    bmi2Digit < 18.5->{
                        textResult.text = "$bmi2Digit UnderWeight"
                    }
                    bmi2Digit in 18.5.. 24.99 ->{
                        textResult.text = "$bmi2Digit Healthy"
                    }
                    bmi2Digit in 25.00 .. 29.99->{
                        textResult.text = "$bmi2Digit Overweight"
                    }
                    bmi2Digit > 29.99->{
                        textResult.text = "$bmi2Digit Obese"
                    }
                }
                result = bmi2Digit
                textInfo.text = "(Normal Range is 18.5 - 24.9)"
            }
            else{
                textResult.text = ""
                textInfo.text = ""
            }

            if (validateInput(name, height, weight)) {
                CoroutineScope(Dispatchers.IO).launch {
                    savePersonData()
                }
            }
        }

    }

    private suspend fun savePersonData(){

        dao.insertData(
            Person(
                0,
                name.toString(),
                height.toInt(),
                weight.toInt(),
                result
            )
        )
    }


    private fun validateInput(name: String?, height: String?, weight: String?) : Boolean{
        return when{
            name.isNullOrEmpty() && height.isNullOrEmpty() && weight.isNullOrEmpty() ->{
                Toast.makeText(this, "Please Enter Name, Height & Weight", Toast.LENGTH_SHORT).show()
                return false
            }
            name.isNullOrEmpty() && height.isNullOrEmpty()->{
                Toast.makeText(this, "Please Enter Name & Height", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty() && weight.isNullOrEmpty() ->{
                Toast.makeText(this, "Please Enter Height & Weight", Toast.LENGTH_SHORT).show()
                return false
            }
            name.isNullOrEmpty() && weight.isNullOrEmpty() ->{
                Toast.makeText(this, "Please Enter Name & Weight", Toast.LENGTH_SHORT).show()
                return false
            }
            name.isNullOrEmpty() ->{
                Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this, "Please Enter Height", Toast.LENGTH_SHORT).show()
                return false
            }
            weight.isNullOrEmpty()->{
                Toast.makeText(this, "Please Enter Weight", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

}
