package com.oscargil80.hindiappnotesmvvmroom.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.oscargil80.hindiappnotesmvvmroom.Database.NotesDatabase
import com.oscargil80.hindiappnotesmvvmroom.Model.Notes
import com.oscargil80.hindiappnotesmvvmroom.Repository.NotesRepository

class NotesViewModel(application: Application): AndroidViewModel(application) {

    val repository : NotesRepository

    init{
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun deleteNotes(id: Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }




}