package com.example.daytworoomresearch.feature.shared.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.daytworoomresearch.R
import com.example.daytworoomresearch.databinding.PersonListLayoutBinding
import com.example.daytworoomresearch.feature.shared.listener.OnClickListener
import com.example.daytworoomresearch.feature.shared.model.Person

class PersonViewHolder(val binding: PersonListLayoutBinding, listener : OnClickListener) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.imvDelete.setOnClickListener {
            listener.delete(adapterPosition)
        }

        itemView.setOnClickListener {
            listener.onPersonClick(adapterPosition)
        }
    }

    fun bind (person : Person){
        binding.person = person
        binding.executePendingBindings()
    }
}