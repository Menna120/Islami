package com.example.islami.ui.hadith

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.islami.databinding.FragmentHadithBinding
import com.example.islami.ui.hadith.adapter.HadithAdapter
import com.example.islami.ui.hadith.model.Hadith
import kotlin.math.abs

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

        hadithList.addAll(loadHadith())

        binding.hadithViewPager.apply {
            adapter = HadithAdapter(hadithList)
            offscreenPageLimit = 3
            setPageTransformer(SliderTransformer())
        }
    }

    private fun loadHadith(): List<Hadith> {
        return requireContext().assets.open("hadith.txt")
            .bufferedReader()
            .use { it.readText() }
            .split("#")
            .mapNotNull { hadithRawItem ->
                hadithRawItem
                    .trim()
                    .takeIf { it.isNotEmpty() }
                    ?.lines()
                    ?.let { lines ->
                        val title = lines.firstOrNull()?.trim() ?: "الحديث"
                        val content = lines
                            .drop(1)
                            .joinToString("\n")
                            .trim()
                            .takeIf { it.isNotEmpty() }
                        content?.let { Hadith(title, it) }
                    }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class SliderTransformer : ViewPager2.PageTransformer {
    companion object {
        private const val MIN_SCALE = 0.9f
        private const val MIN_ALPHA = 0.8f
        private const val DEFAULT_ELEVATION_DP = 2f
        private const val SCALED_ELEVATION_DP = 8f
    }

    override fun transformPage(page: View, position: Float) {
        page.apply {
            val absPos = abs(position)
            translationX = -position * (width / 10)

            val (newAlpha, newScale, newElevationDp) = when {
                position < -1 || position > 1 -> Triple(0f, MIN_SCALE, DEFAULT_ELEVATION_DP)
                position == 0f -> Triple(1f, 1f, SCALED_ELEVATION_DP)
                else -> {
                    val alphaValue = 1 - absPos * (1 - MIN_ALPHA)
                    val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - absPos)
                    val elevation =
                        DEFAULT_ELEVATION_DP + (SCALED_ELEVATION_DP - DEFAULT_ELEVATION_DP) * (1 - absPos)
                    Triple(alphaValue, scaleFactor, elevation)
                }
            }

            alpha = newAlpha
            scaleX = newScale
            scaleY = newScale
            ViewCompat.setElevation(page, newElevationDp.dpToPx(context.resources.displayMetrics))
        }
    }
}

fun Float.dpToPx(displayMetrics: android.util.DisplayMetrics): Float {
    return this * displayMetrics.density
}
