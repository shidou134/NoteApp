package com.example.noteapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp.databinding.FragmentUpdateNoteBinding
import com.example.noteapp.viewmodel.NoteViewModel
import com.example.noteapp.viewmodel.NoteViewModelFactory

class UpdateNoteFragment : Fragment() {

    private lateinit var binding: FragmentUpdateNoteBinding
    private val args: UpdateNoteFragmentArgs by navArgs()
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
        binding = FragmentUpdateNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentUpdateNoteBinding.bind(view)
        val note = args.Note
        binding.edtTitle.setText(note.title)
        binding.edtDescription.setText(note.description)

        binding.btnUpdateNote.setOnClickListener {
            note.title = binding.edtTitle.text.toString()
            note.description = binding.edtDescription.text.toString()
            if (note.title
                    .isNotEmpty() && note.description.isNotEmpty()
            ) {
                viewModel.updateNote(note)
                findNavController().popBackStack()
            }
        }
    }
}