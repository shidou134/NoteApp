package com.example.noteapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.adapter.NoteAdapter
import com.example.noteapp.databinding.FragmentNoteBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import com.example.noteapp.viewmodel.NoteViewModelFactory


class  NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private var list: List<Note> = mutableListOf()
    private val onItemClick: (Note) -> Unit = {
        val action = NoteFragmentDirections.actionNoteFragmentToUpdateNoteFragment(it)
        findNavController().navigate(action)
    }
    private val onItemDelete: (Note) -> Unit = {
        viewModel.deleteNote(it)
    }
    private val viewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModelFactory(requireActivity().application)
        )[NoteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNoteBinding.bind(view)

        val adapter = NoteAdapter(list, onItemClick, onItemDelete)
        binding.rvNote.adapter = adapter

        viewModel.getAllNote().observe(viewLifecycleOwner) { data ->
            adapter.setNote(data)
        }

        binding.btnOpenAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_addNoteFragment)
        }
    }
}