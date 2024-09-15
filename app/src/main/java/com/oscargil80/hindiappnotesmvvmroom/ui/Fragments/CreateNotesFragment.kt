package com.oscargil80.hindiappnotesmvvmroom.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.oscargil80.hindiappnotesmvvmroom.Model.Notes
import com.oscargil80.hindiappnotesmvvmroom.R
import com.oscargil80.hindiappnotesmvvmroom.Util.traerFecha

import com.oscargil80.hindiappnotesmvvmroom.ViewModel.NotesViewModel
import com.oscargil80.hindiappnotesmvvmroom.databinding.FragmentCreateNotesBinding

import java.util.Date


class CreateNotesFragment : Fragment() {

    var _binding: FragmentCreateNotesBinding? = null
    val binding get() = _binding!!
    private var priority: String = "1"
    val d = Date()

    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pGreen.setImageResource(R.drawable.ic_done)
        binding.pGreen.setOnClickListener {
            priority = "1"
            render()
            binding.pGreen.setImageResource(R.drawable.ic_done)
        }
        binding.pYellow.setOnClickListener {
            priority = "2"
            render()
            binding.pYellow.setImageResource(R.drawable.ic_done)
        }
        binding.pRed.setOnClickListener {
            priority = "3"
            render()
            binding.pRed.setImageResource(R.drawable.ic_done)
        }
        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }
    }

    private fun createNotes(view: View) {
        val title = binding.edtTitle.text.toString()
        val subTitle = binding.edtSubTitle.text.toString()
        val notes = binding.edtNotes.text.toString()
        val notesDate = traerFecha(d)

        val data = Notes(  null,  title, subTitle, notes, notesDate.toString(),  priority)
         viewModel.addNotes(data)

        Toast.makeText(requireContext(), "Nota Creada Correstamente", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }

    private fun render() {
        binding.pGreen.setImageResource(0)
        binding.pYellow.setImageResource(0)
        binding.pRed.setImageResource(0)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}