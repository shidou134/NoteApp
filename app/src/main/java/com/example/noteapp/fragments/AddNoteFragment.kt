package com.example.noteapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteapp.databinding.FragmentAddNoteBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import com.example.noteapp.viewmodel.NoteViewModelFactory


class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
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
        binding = FragmentAddNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddNoteBinding.bind(view)

        binding.btnAddNote.setOnClickListener {
            val note =
                Note(binding.edtTitle.text.toString(), binding.edtDescription.text.toString())
            if (binding.edtTitle.text.toString()
                    .isNotEmpty() && binding.edtDescription.text.toString().isNotEmpty()
            ) {
                viewModel.insertNote(note)
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Fill in the blank", Toast.LENGTH_LONG).show()
            }
        }
    }
}