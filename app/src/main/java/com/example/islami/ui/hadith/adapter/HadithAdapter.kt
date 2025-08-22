package com.example.islami.ui.hadith.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.databinding.ItemHadithBinding
import com.example.islami.ui.hadith.model.Hadith

class HadithAdapter(private val hadithList: List<Hadith>) :
    RecyclerView.Adapter<HadithAdapter.HadithViewHolder>() {

    class HadithViewHolder(val binding: ItemHadithBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hadith: Hadith) {
            binding.hadithTitle.text = hadith.title
            binding.hadithContent.text = hadith.content
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HadithViewHolder = HadithViewHolder(
        ItemHadithBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: HadithViewHolder, position: Int) {
        holder.bind(hadithList[position])
    }

    override fun getItemCount() = hadithList.size
}