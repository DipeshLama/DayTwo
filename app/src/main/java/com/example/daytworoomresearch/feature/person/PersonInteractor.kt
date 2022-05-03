package com.example.daytworoomresearch.feature.person

import android.content.Context
import com.example.daytworoomresearch.feature.shared.model.Person
import com.example.daytworoomresearch.feature.shared.repository.PersonRepository

class PersonInteractor {
    private val personRepository = PersonRepository()

    fun insertPerson (context : Context, person : Person) = personRepository.insertPerson(context,person)
    fun getAllPerson (context : Context) = personRepository.getAllPerson(context)
    fun getPerson (context: Context, position: Int) = personRepository.getPersonById(context, position )
    fun updatePerson (context : Context, name : String, address: String, phone : String, position: Int ) = personRepository.updatePerson(context, name, address,phone, position)
    fun deletePerson (context : Context , position : Int) = personRepository.deletePerson(context, position)
}