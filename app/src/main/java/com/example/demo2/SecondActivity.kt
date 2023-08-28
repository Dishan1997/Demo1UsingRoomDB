package com.example.demo2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo2.db.PersonDao
import com.example.demo2.db.PersonDatabase
import com.example.demoproject1.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondActivity : AppCompatActivity() {

    private lateinit var secondActivityAdapter: SecondActivityRecycler
    private lateinit var recyclerView : RecyclerView
    private lateinit var dao : PersonDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        dao = PersonDatabase.getInstance(application).personDao()

        recyclerView = findViewById(R.id.myRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            val persons = dao.getAllPersonData()

            withContext(Dispatchers.Main) {
                if (!persons.isNullOrEmpty()) {
                    secondActivityAdapter = SecondActivityRecycler(persons)
                    recyclerView.adapter = secondActivityAdapter
                }
            }
        }

    }
}