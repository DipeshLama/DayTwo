package com.example.daytworoomresearch.feature.person

import com.example.daytworoomresearch.feature.shared.model.Person
import com.hannesdorfmann.mosby3.mvp.MvpView

interface PersonView : MvpView {
    fun getAllPerson (list : List <Person>)
    fun getPerson (person : Person)
    fun showMessage (message : String)
}