package com.example.daytworoomresearch.feature.person

import android.content.Context
import com.example.daytworoomresearch.feature.shared.model.Person
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class PersonPresenter : MvpBasePresenter<PersonView> (){
    private var personInteractor : PersonInteractor? = null

    override fun attachView(view: PersonView) {
        super.attachView(view)
        personInteractor = PersonInteractor()
    }

    override fun detachView() {
        personInteractor = null
        super.detachView()
    }


    fun insertPerson (context : Context, person : Person){
        personInteractor?.insertPerson(context, person)
        getAllPerson(context)
    }

    fun getAllPerson (context : Context){
        ifViewAttached { view ->
            view.getAllPerson(personInteractor!!.getAllPerson(context))
        }
    }

    fun getPerson (context: Context,position: Int){
        ifViewAttached { view ->
            val person = personInteractor?.getPerson(context, position)
            view.getPerson(person!!)
        }
    }

    fun updatePerson (context:Context, name: String, address : String , phone : String, position : Int){
        personInteractor?.updatePerson(context,name, address,phone, position )
        getAllPerson(context)
    }

    fun deletePerson (context : Context, position : Int ){
        personInteractor?.deletePerson(context, position)
        ifViewAttached { view ->
            view.showMessage("Deleted successfully.")
        }
        getAllPerson(context)
    }
}