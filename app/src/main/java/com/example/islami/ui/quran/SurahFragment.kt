package com.example.islami.ui.quran

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islami.databinding.FragmentSurahBinding

private const val SURAH_INDEX = "surah_index"

class SurahFragment : Fragment() {
    private var _binding: FragmentSurahBinding? = null
    private val binding get() = _binding!!
    private var surahIndex: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            surahIndex = it.getInt(SURAH_INDEX, -1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSurahBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun surahFragmentWithArgument(surahIndex: Int) =
            SurahFragment().apply {
                arguments = Bundle().apply {
                    putInt(SURAH_INDEX, surahIndex)
                }
            }
    }
}