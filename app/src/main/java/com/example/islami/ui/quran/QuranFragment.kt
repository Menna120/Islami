package com.example.islami.ui.quran

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islami.R
import com.example.islami.databinding.FragmentQuranBinding
import com.example.islami.ui.quran.adapter.QuranAdapter
import com.example.islami.ui.quran.adapter.SurahListItemDecoration
import com.example.islami.ui.quran.model.QuranSurah.Companion.quranSurah
import com.example.islami.ui.surah.SurahFragment.Companion.surahFragmentWithArgument

class QuranFragment : Fragment() {

    private var _binding: FragmentQuranBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.quranRecyclerView.adapter = QuranAdapter(quranSurah) { surah ->
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_layout, surahFragmentWithArgument(surah))
                .addToBackStack(null)
                .commit()
        }

        val itemDecoration = SurahListItemDecoration(requireContext())
        binding.quranRecyclerView.addItemDecoration(itemDecoration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}