package com.example.islami.ui.quran.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.R

class SurahListItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val dividerPaint = Paint()
    private val dividerHeight: Int = context.resources.getDimensionPixelSize(R.dimen.divider_height)
    private val lastItemBottomMargin: Int =
        context.resources.getDimensionPixelSize(R.dimen.last_item_bottom_margin)

    init {
        dividerPaint.color =
            ContextCompat.getColor(context, R.color.white)
        dividerPaint.strokeWidth = dividerHeight.toFloat()
        dividerPaint.isAntiAlias = true
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        if (position == RecyclerView.NO_POSITION) {
            return
        }

        if (position < itemCount - 1) {
            outRect.bottom = dividerHeight
        } else if (position == itemCount - 1) {
            outRect.bottom = lastItemBottomMargin
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val parentLeftPadding = parent.paddingLeft
        val parentRightPadding = parent.paddingRight
        val parentWidth = parent.width

        val contentWidth = parentWidth - parentLeftPadding - parentRightPadding
        val dividerActualWidth = contentWidth * 0.65f

        val dividerMarginHorizontal = (contentWidth - dividerActualWidth) / 2f

        val newLeft = parentLeftPadding + dividerMarginHorizontal
        val newRight = parentWidth - parentRightPadding - dividerMarginHorizontal

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            val itemCount = parent.adapter?.itemCount ?: 0

            if (position == RecyclerView.NO_POSITION || position == itemCount - 1) {
                continue
            }

            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val yPosition =
                top + (dividerHeight / 2f)

            c.drawLine(
                newLeft,
                yPosition,
                newRight,
                yPosition,
                dividerPaint
            )
        }
    }
}
