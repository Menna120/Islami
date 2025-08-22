package com.example.islami.ui.surah

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islami.databinding.FragmentSurahBinding
import com.example.islami.ui.quran.model.QuranSurah
import com.example.islami.ui.surah.adapter.VersesAdapter

private const val SURAH_NUMBER = "surah_number"
private const val SURAH_ENGLISH_NAME = "surah_english_name"
private const val SURAH_ARABIC_NAME = "surah_arabic_name"

class SurahFragment : Fragment() {
    private var _binding: FragmentSurahBinding? = null
    private val binding get() = _binding!!
    private var surahNumber: Int? = null
    private var surahEnglishName: String? = null
    private var surahArabicName: String? = null
    private lateinit var verses: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            surahNumber = it.getInt(SURAH_NUMBER, -1)
            surahEnglishName = it.getString(SURAH_ENGLISH_NAME)
            surahArabicName = it.getString(SURAH_ARABIC_NAME)
        }

        verses = suraVerses()
    }

    private fun suraVerses(): List<String> =
        requireActivity()
            .assets
            .open("quran/$surahNumber.txt")
            .bufferedReader()
            .use { reader ->
                reader.readText()
                    .trim()
                    .split('\n')
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }
            }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSurahBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigateUpButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.surahNameInEnglish.text = surahEnglishName
        binding.surahNameInArabic.text = surahArabicName

        binding.versesRecyclerView.adapter = VersesAdapter(verses)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun surahFragmentWithArgument(surah: QuranSurah) =
            SurahFragment().apply {
                arguments = Bundle().apply {
                    putInt(SURAH_NUMBER, surah.number)
                    putString(SURAH_ENGLISH_NAME, surah.englishName)
                    putString(SURAH_ARABIC_NAME, surah.arabicName)
                }
            }
    }
}