package com.oscargil80.hindiappnotesmvvmroom.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oscargil80.hindiappnotesmvvmroom.Model.Notes
import com.oscargil80.hindiappnotesmvvmroom.R
import com.oscargil80.hindiappnotesmvvmroom.ViewModel.NotesViewModel
import com.oscargil80.hindiappnotesmvvmroom.databinding.FragmentHomeBinding
import com.oscargil80.hindiappnotesmvvmroom.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter
    lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* staggeredGridLayoutManager =
          StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)*/

        //get all notes
        viewModel.getNotes().observe(viewLifecycleOwner) { notesList -> setUpRecycler(notesList) }
        // filter all Notes
        binding.btnAllNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList -> setUpRecycler(notesList) }
        }
        // filter Mediun Notes
        binding.filterMediun.setOnClickListener {
            viewModel.getMediunNotes().observe(viewLifecycleOwner) { notesList ->  setUpRecycler(notesList)  }
        }
        // filter Low Notes
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->  setUpRecycler(notesList)    }
        }
        // filter High Notes
        binding.filterHigh.setOnClickListener {
            val view = viewModel.getHighNotes()
            view.observe(viewLifecycleOwner) { notesList ->
                setUpRecycler(notesList)
            }
        }

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
    }

    private fun setUpRecycler(notesList: List<Notes>) {
        staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
        oldMyNotes = notesList as ArrayList<Notes>
        adapter = NotesAdapter(notesList)
        binding.rcvAllNotes.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.app_bar_search)

        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Note Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                NotesFiltering(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(newText: String?) {
        val newFilteredList = arrayListOf<Notes>()
        for (i in oldMyNotes) {
            if (i.title.contains(newText!!) || i.subTitle.contains(newText!!)) {
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }
}


