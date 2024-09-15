package com.oscargil80.hindiappnotesmvvmroom.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oscargil80.hindiappnotesmvvmroom.Model.Notes
import com.oscargil80.hindiappnotesmvvmroom.R


class NotesAdapter(
    var notesList: List<Notes>,
    ) : RecyclerView.Adapter<NotesViewHolder>() {

    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NotesViewHolder(layoutInflater.inflate(R.layout.item_notes, parent, false))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val item = notesList[position]
        holder.render(item)//, onClickListener, onItemSeleted)
    }
    override fun getItemCount(): Int = notesList.size
}
