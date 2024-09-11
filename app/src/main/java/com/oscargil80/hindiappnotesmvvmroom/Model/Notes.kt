package com.oscargil80.hindiappnotesmvvmroom.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

// poner data class a ver si funciona igual
@Entity(tableName = "Notes")
 class Notes(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String,
    var subTitle: String,
    var notes: String,
    var date: String,
    var priority: String,
    )