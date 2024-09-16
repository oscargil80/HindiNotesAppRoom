package com.oscargil80.hindiappnotesmvvmroom.ui.Adapter

import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.oscargil80.hindiappnotesmvvmroom.Model.Notes
import com.oscargil80.hindiappnotesmvvmroom.R
import com.oscargil80.hindiappnotesmvvmroom.Util.Types
import com.oscargil80.hindiappnotesmvvmroom.Util.Types.*
import com.oscargil80.hindiappnotesmvvmroom.databinding.ItemNotesBinding
import com.oscargil80.hindiappnotesmvvmroom.ui.Fragments.HomeFragmentDirections


class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var binding = ItemNotesBinding.bind(view)

    fun render(
        notes: Notes,
        onClickListener:  (types: Types, position: Int, note: Notes) -> Unit
        //onItemSeleted: (Int) -> Unit,*/
    ) {
        binding.notesTitle.text = notes.title
        binding.notesSubTitle.text = notes.subTitle
        binding.notesDate.text = notes.date

        when (notes.priority) {
            "1" ->   binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            "2" ->   binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            "3" ->   binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
        }

          itemView.setOnClickListener {
                onClickListener(nota, adapterPosition, notes)
          }

        binding.viewPriority.setOnClickListener {
            onClickListener(posicion, adapterPosition, notes)
        }
    }

}
