package com.nilu.popupwindowinrecyclerviewitem
import android.view.Gravity
import android.graphics.drawable.BitmapDrawable
import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.nilu.popupwindowinrecyclerviewitem.R


class PopupWindowHelper(private val ctx: Context) {
    private val tipWindow: PopupWindow?
    private val contentView: View
    private val inflater: LayoutInflater

    internal val isTooltipShown: Boolean
        get() = tipWindow != null && tipWindow.isShowing


    init {
        tipWindow = PopupWindow(ctx)

        inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        contentView = inflater.inflate(R.layout.popup_window, null)
    }

    internal fun showToolTip(anchor: View, event: MotionEvent) {

        tipWindow!!.height = LinearLayout.LayoutParams.WRAP_CONTENT
        tipWindow.width = LinearLayout.LayoutParams.WRAP_CONTENT

        tipWindow.isOutsideTouchable = true
        tipWindow.isTouchable = true
        tipWindow.isFocusable = true
        tipWindow.setBackgroundDrawable(BitmapDrawable())

        tipWindow.contentView = contentView

        val screenPos = IntArray(2)
        anchor.getLocationOnScreen(screenPos)

        val anchorRect =
            Rect(screenPos[0], screenPos[1], screenPos[0] + anchor.width, screenPos[1] + anchor.height)

        contentView.measure(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val contentViewHeight = contentView.measuredHeight
        val contentViewWidth = contentView.measuredWidth

        val positionX = anchorRect.centerX() - contentViewWidth / 2
        val positionY = anchorRect.bottom - anchorRect.height() / 2

        tipWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, event.x.toInt(), positionY)

    }

    internal fun dismissTooltip() {
        if (tipWindow != null && tipWindow.isShowing)
            tipWindow.dismiss()
    }


}
