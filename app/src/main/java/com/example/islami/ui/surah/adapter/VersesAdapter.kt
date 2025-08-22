package com.example.islami.ui.surah.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.databinding.ItemVerseBinding

class VersesAdapter(val verses: List<String>) :
    RecyclerView.Adapter<VersesAdapter.VerseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VerseViewHolder {
        val binding = ItemVerseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VerseViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: VerseViewHolder,
        position: Int
    ) {
        val verse = verses[position]
        holder.bind(verse, position + 1)
    }

    override fun getItemCount(): Int = verses.size

    class VerseViewHolder(val binding: ItemVerseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(verse: String, verseNumber: Int) {
            binding.verseTextView.text = "$verse [$verseNumber]"
        }
    }
}