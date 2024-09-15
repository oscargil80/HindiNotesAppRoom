package com.oscargil80.hindiappnotesmvvmroom.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.oscargil80.hindiappnotesmvvmroom.Model.Notes
import com.oscargil80.hindiappnotesmvvmroom.R
import com.oscargil80.hindiappnotesmvvmroom.Util.traerFecha
import com.oscargil80.hindiappnotesmvvmroom.ViewModel.NotesViewModel
import com.oscargil80.hindiappnotesmvvmroom.databinding.FragmentEditNotesBinding
import java.util.*

class EditNotesFragment : Fragment() {
    val oldNotes by navArgs<EditNotesFragmentArgs>()
    private  var _binding: FragmentEditNotesBinding? = null
    private val binding get() = _binding!!

    private var priority: String = "1"
    val d = Date()
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.edtTitle.setText(oldNotes.data.title)
        binding.edtSubTitle.setText(oldNotes.data.subTitle)
        binding.edtNotes.setText(oldNotes.data.notes)

        render()
        priority = oldNotes.data.priority
        when (oldNotes.data.priority) {
            "1" ->    binding.pGreen.setImageResource(R.drawable.ic_done)
            "2" ->    binding.pYellow.setImageResource(R.drawable.ic_done)
            "3" ->    binding.pRed.setImageResource(R.drawable.ic_done)
        }

        binding.pGreen.apply {
            setOnClickListener {
                priority = "1"
                render()
                setImageResource(R.drawable.ic_done)
            }
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
        binding.btnEditNotes.setOnClickListener {  updateNotes(it)     }
    }

    private fun render() {
        binding.pGreen.setImageResource(0)
        binding.pYellow.setImageResource(0)
        binding.pRed.setImageResource(0)
    }

    private fun updateNotes(view: View) {
        val title = binding.edtTitle.text.toString()
        val subTitle = binding.edtSubTitle.text.toString()
        val notes = binding.edtNotes.text.toString()
        val notesDate = traerFecha(d)
        val data = Notes(
            oldNotes.data.id, title, subTitle, notes, notesDate.toString(), priority
        )
        viewModel.updateNotes(data)

        Toast.makeText(requireContext(), "Nota Actualizada Correctamente", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            val bottomSheet =
                BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                atras(requireView())
                bottomSheet.dismiss()
                Toast.makeText(requireContext(), "Elemento Borrado", Toast.LENGTH_SHORT).show();
            }

            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun atras(view: View) {
        view.findNavController().popBackStack(R.id.homeFragment, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}