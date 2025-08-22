package com.example.islami.ui.hadith

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.islami.databinding.FragmentHadithBinding
import com.example.islami.ui.hadith.adapter.HadithAdapter
import com.example.islami.ui.hadith.model.Hadith

class HadithFragment : Fragment() {

    private var _binding: FragmentHadithBinding? = null
    private val binding get() = _binding!!
    private val hadithList = mutableListOf<Hadith>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHadithBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadHadith()

        binding.hadithRecyclerView.adapter = HadithAdapter(hadithList)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.hadithRecyclerView)

//        binding.hadithRecyclerView.layoutManager =
//            CarouselLayoutManager(HeroCarouselStrategy())
//                .apply {
//                    orientation = CarouselLayoutManager.HORIZONTAL
//                    setCarouselAlignment(CarouselLayoutManager.ALIGNMENT_CENTER)
//                }

//        val snapHelper = CarouselSnapHelper()
//        snapHelper.attachToRecyclerView(binding.hadithRecyclerView)
    }

    private fun loadHadith() {
        val hadithRaw =
            requireContext()
                .assets.open("hadith.txt")
                .bufferedReader()
                .use { it.readText() }
                .split("#")

        for (hadithRaw in hadithRaw) {
            val trimmedHadith = hadithRaw.trim()
            if (trimmedHadith.isNotEmpty()) {
                val lines = trimmedHadith.lines()
                val title = lines.firstOrNull()?.trim() ?: "الحديث"
                val content = lines.drop(1).joinToString("\n").trim()
                if (content.isNotEmpty()) {
                    hadithList.add(Hadith(title, content))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}