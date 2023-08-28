package com.example.demo2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo2.db.Person
import com.example.demoproject1.R

class SecondActivityRecycler(private val personList : List<Person>): RecyclerView.Adapter<SecondActivityRecycler.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val personDetails = inflater.inflate(R.layout.design_second, parent, false)
        return MyViewHolder(personDetails)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curentPerson = personList[position]
        holder.name.text = "Name is: " + curentPerson.name
        holder.weight.text = "Weight: "+ curentPerson.weight.toString()
        holder.height.text = "  Height: "+curentPerson.height.toString()
        holder.bmi.text = "BMI: " +curentPerson.bmi.toString()
    }


    inner class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.txtNameRe)
        val weight = view.findViewById<TextView>(R.id.txtweightRe)
        val height = view.findViewById<TextView>(R.id.txtHeightRe)
        val bmi = view.findViewById<TextView>(R.id.txtBmiRe)
    }

}