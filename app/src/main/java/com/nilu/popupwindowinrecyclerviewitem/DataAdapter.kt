package com.nilu.popupwindowinrecyclerviewitem

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_layout.view.*
import android.view.MotionEvent
import com.nilu.popupwindowinrecyclerviewitem.R


class DataAdapter(context: Context) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    val mContext = context
    private var lastTouchDown: Long = 0
    private val CLICK_ACTION_THRESHHOLD = 200

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(mContext)
                .inflate(R.layout.row_layout, parent, false)

        view.setOnTouchListener { myView, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> lastTouchDown = System.currentTimeMillis()
                MotionEvent.ACTION_UP -> if (System.currentTimeMillis() - lastTouchDown < CLICK_ACTION_THRESHHOLD) {
                    val popupWindowHelper = PopupWindowHelper(mContext)
                    myView?.let {
                        popupWindowHelper.showToolTip(
                            it
                            , event
                        )
                    }
                }
            }
            true
        }
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 30
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvDescription.text = "Row Description $position"
        holder.tvTitle.text = "Row Title $position"

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tvTitle
        val tvDescription = itemView.tvDescription
    }
}