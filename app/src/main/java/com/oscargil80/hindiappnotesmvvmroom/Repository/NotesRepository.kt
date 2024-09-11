package com.oscargil80.hindiappnotesmvvmroom.Repository

import androidx.lifecycle.LiveData
import com.oscargil80.hindiappnotesmvvmroom.Dao.NotesDao
import com.oscargil80.hindiappnotesmvvmroom.Model.Notes

class NotesRepository(val dao:NotesDao) {

/*    fun getAllNotes(): LiveData<List<Notes>>{
        return dao.getNotes()
    }*/

    fun getAllNotes(): LiveData<List<Notes>> = dao.getNotes()


    fun insertNotes(notes: Notes) =  dao.insertNotes(notes)


    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
}