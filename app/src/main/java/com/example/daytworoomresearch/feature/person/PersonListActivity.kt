package com.example.daytworoomresearch.feature.person

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.room.Database
import com.example.daytworoomresearch.R
import com.example.daytworoomresearch.databinding.ActivityPersonListBinding
import com.example.daytworoomresearch.feature.shared.adapter.PersonAdapter
import com.example.daytworoomresearch.feature.shared.listener.OnClickListener
import com.example.daytworoomresearch.feature.shared.model.Person
import com.example.daytworoomresearch.utils.constants.DatabaseConstants
import com.example.daytworoomresearch.utils.constants.ErrorConstants
import com.example.daytworoomresearch.utils.constants.SuccessConstants
import com.google.android.material.textfield.TextInputEditText
import com.hannesdorfmann.mosby3.mvp.MvpActivity

class PersonListActivity : MvpActivity<PersonView,PersonPresenter>(),PersonView, OnClickListener {
    private lateinit var binding : ActivityPersonListBinding
    private lateinit var edtName : TextInputEditText
    private lateinit var edtAddress : TextInputEditText
    private lateinit var edtPhoneNumber : TextInputEditText
    private lateinit var btnOk : AppCompatButton
    private lateinit var btnCancel : AppCompatButton
    private var adapter : PersonAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        presenter.getAllPerson(this)
    }

    override fun createPresenter() = PersonPresenter()

    override fun getAllPerson(list: List<Person>) {
        populateRecyclerView(list)
    }

    override fun getPerson(person: Person) {
        edtName.setText(person.name)
        edtAddress.setText(person.address)
        edtPhoneNumber.setText(person.phoneNumber)
    }

    override fun showMessage(message: String) {
        toastMessage(message)
    }

    private fun populateRecyclerView (list : List<Person>){
        adapter = PersonAdapter(list, this)
        binding.ryvPersonList.adapter = adapter
    }

    private fun displayAddUpdateDialog (type : String, position : Int){
        val view = View.inflate(this, R.layout.add_update_dialog,null)
        val alertDialog = AlertDialog.Builder(this)

        edtName = view.findViewById(R.id.edtName)
        edtAddress = view.findViewById(R.id.edtAddress)
        edtPhoneNumber = view.findViewById(R.id.edtPhone)
        btnOk = view.findViewById(R.id.btnOk)
        btnCancel = view.findViewById(R.id.btnCancel)

        alertDialog.setView(view)
        val originalDialog = alertDialog.create()
        originalDialog.window?.setDimAmount(0.7f)
        originalDialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

        if(type == DatabaseConstants.update){
            presenter.getPerson(this, position)
        }

        btnOk.setOnClickListener {
            val name = edtName.text.toString()
            val address = edtAddress.text.toString()
            val phoneNumber = edtPhoneNumber.text.toString()
            if(isValid()){
                if(type == DatabaseConstants.add){
                    presenter.insertPerson(this, Person(name = name,address = address, phoneNumber = phoneNumber))
                    toastMessage(SuccessConstants.insertSuccess)
                }else{
                    presenter.updatePerson(this, name, address, phoneNumber, position)
                    toastMessage(SuccessConstants.updateSuccess)
                }
                originalDialog.dismiss()
            }
        }

        btnCancel.setOnClickListener{
            originalDialog.dismiss()
        }

        originalDialog.setCanceledOnTouchOutside(true)
        originalDialog.show()
        originalDialog.window?.setGravity(Gravity.CENTER)
    }

    private fun isValid () : Boolean{
        when {
            edtName.text?.isEmpty() == true -> {
                toastMessage(ErrorConstants.requireName)
                return false
            }
            edtAddress.text?.isEmpty() == true -> {
                toastMessage(ErrorConstants.requireAddress)
                return false
            }
            edtPhoneNumber.text?.isEmpty() == true -> {
                toastMessage(ErrorConstants.requirePhoneNumber)
                return false
            }
        }
        return true
    }

    private fun toastMessage (localizedMessage : String){
        Toast.makeText(this, localizedMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add ->{
                displayAddUpdateDialog(DatabaseConstants.add, -1)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun delete(position: Int) {
        presenter.deletePerson(this, position)
    }

    override fun onPersonClick(position: Int) {
        displayAddUpdateDialog(DatabaseConstants.update, position)
    }
}