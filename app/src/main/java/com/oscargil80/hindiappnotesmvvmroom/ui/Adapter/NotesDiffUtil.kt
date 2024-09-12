package com.oscargil80.hindiappnotesmvvmroom.ui.Adapter

import androidx.recyclerview.widget.DiffUtil
import com.oscargil80.hindiappnotesmvvmroom.Model.Notes

class NotesDiffUtill(
    val oldList: List<Notes>,
    val newList: List<Notes>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}