package com.oscargil80.hindiappnotesmvvmroom.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oscargil80.hindiappnotesmvvmroom.Model.Notes
import com.oscargil80.hindiappnotesmvvmroom.R


// private val itemClickListener: OnAnimalClickListener

class NotesAdapter(
   private var notesList: List<Notes>,
   private val onClickListener: OnNotesClickListener // En este caso se llama una interface es decir va a ser de tipo interface
    ) : RecyclerView.Adapter<NotesViewHolder>() {

    //var notesList: List<Notes> = listOf()


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

        holder.render(item, onClickListener)//, onItemSeleted)
    }
    override fun getItemCount(): Int = notesList.size
}
