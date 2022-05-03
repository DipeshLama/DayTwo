package com.example.daytworoomresearch.feature.shared.repository

import android.content.Context
import com.example.daytworoomresearch.database.AppDatabase
import com.example.daytworoomresearch.feature.shared.model.Person

class PersonRepository {

    private fun getAppDatabase(context : Context) =AppDatabase.getAppDatabase(context)

    fun insertPerson (context : Context, person : Person){
        getAppDatabase(context)?.getPersonInfo()?.insertPerson(person)
    }

    fun getAllPerson (context : Context) : List<Person> {
        return getAppDatabase(context)?.getPersonInfo()?.getAllPerson()!!
    }

    fun getPersonById (context : Context, position: Int) : Person {
        val user = getAllPerson(context)
        return getAppDatabase(context)?.getPersonInfo()?.getPerson(user[position].id)!!
    }

    fun updatePerson (context : Context, name: String, address: String, phone: String, position: Int){
        val userList = getAllPerson(context)
        val id = userList[position].id
        val person = Person()
        person.id = id
        person.name = name
        person.address = address
        person.phoneNumber = phone
        getAppDatabase(context)?.getPersonInfo()?.updatePerson(person)
    }

    fun deletePerson (context: Context, position : Int){
        val user = getAllPerson(context)
        getAppDatabase(context)?.getPersonInfo()?.deletePerson(user[position].id)
    }
}