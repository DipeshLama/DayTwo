package com.example.daytworoomresearch.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.daytworoomresearch.databinding.PersonListLayoutBinding
import com.example.daytworoomresearch.feature.shared.listener.OnClickListener
import com.example.daytworoomresearch.feature.shared.model.Person
import com.example.daytworoomresearch.feature.shared.viewholder.PersonViewHolder

class PersonAdapter (var personList : List<Person>, var listener: OnClickListener): RecyclerView.Adapter<PersonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PersonListLayoutBinding.inflate(inflater, parent, false)
        return PersonViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(personList[position])
    }

    override fun getItemCount(): Int {
        return personList.size
    }
}