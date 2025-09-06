package com.example.islami.ui.quran.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.databinding.ItemQuranSurahBinding
import com.example.islami.ui.quran.model.QuranSurah

class QuranAdapter(
    val items: List<QuranSurah>,
    val onSurahClick: (surah: QuranSurah) -> Unit
) : RecyclerView.Adapter<QuranAdapter.QuranViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuranViewHolder {
        val binding = ItemQuranSurahBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return QuranViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: QuranViewHolder,
        position: Int
    ) {
        holder.bind(items[position], onSurahClick)
    }

    override fun getItemCount(): Int = items.size

    class QuranViewHolder(
        val binding: ItemQuranSurahBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: QuranSurah, onSurahClick: (surah: QuranSurah) -> Unit) {
            binding.surahNumber.text = item.number.toString()
            binding.surahNameInEnglish.text = item.englishName
            binding.surahNameInArabic.text = item.arabicName
            binding.surahVerses.text = "${item.numberOfVerses} Verses"

            binding.root.setOnClickListener {
                onSurahClick(item)
            }
        }
    }
}