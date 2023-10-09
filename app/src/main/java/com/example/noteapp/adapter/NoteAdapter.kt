package com.example.noteapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.NoteItemBinding
import com.example.noteapp.model.Note

class NoteAdapter(
    private var list: List<Note>,
    private val onClick: (Note) -> Unit,
    private val onDelete: (Note) -> Unit
) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(note: Note) {
            binding.tvItemTitle.text = note.title
            binding.tvItemDescription.text = note.description

            binding.imgDelete.setOnClickListener {
                onDelete(note)
            }

            binding.layoutItem.setOnClickListener {
                onClick(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNote(newData: List<Note>) {
        list = newData
        notifyDataSetChanged()
    }
}