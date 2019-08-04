package com.nilu.popupwindowinrecyclerviewitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nilu.popupwindowinrecyclerviewitem.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myRecyclerView.layoutManager=LinearLayoutManager(this)
        myRecyclerView.setHasFixedSize(true)
        myRecyclerView.adapter= DataAdapter(this)
    }
}
