package com.oscargil80.hindiappnotesmvvmroom.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.oscargil80.hindiappnotesmvvmroom.Model.Notes


@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=3")
    fun getHighNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=2")
    fun getMediunNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=1")
    fun getLowNotes(): LiveData<List<Notes>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes:Notes)

    @Query("DELETE FROM Notes WHERE id=:id ")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes)


}