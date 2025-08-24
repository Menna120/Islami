package com.example.islami.ui.sebiha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islami.databinding.FragmentSebihaBinding

class SebihaFragment : Fragment() {

    private var _binding: FragmentSebihaBinding? = null
    private val binding get() = _binding!!
    private val tasbihList = listOf(
        "سبحان الله",
        "الحمد الله",
        "لا إله إلا الله",
        "الله أكبر",
        "لا حول ولا قوة إلا بالله",
        "أستغفر الله",
        "الْلَّهُم صَلِّ وَسَلِم وَبَارِك عَلَى سَيِّدِنَا مُحَمَّد",
        "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ ، سُبْحَانَ اللَّهِ الْعَظِيمِ"
    )
    private var tasbihIndex = 0
    private var tasbihCount = 33

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSebihaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi()

        binding.sebihaBody.setOnClickListener {
            tasbihCount--
            if (tasbihCount == 0) {
                tasbihCount = 33
                tasbihIndex = (tasbihIndex + 1) % tasbihList.size
            }
            updateUi()

            binding.sebihaBody.animate().apply {
                duration = 500
                rotationBy(15f)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUi() {
        binding.tasbihText.text = tasbihList[tasbihIndex]
        binding.tasbihText.text = tasbihCount.toString()
    }
}
