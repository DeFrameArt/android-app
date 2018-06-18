package com.deframe.artapp.ui

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.deframe.artapp.R
import com.deframe.artapp.helper.Museum
import com.deframe.artapp.helper.MuseumListAdapter

class Test : AppCompatActivity() {

    /**
     *
     * @param savedInstanceState
     * @return Unit
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* setContentView(R.layout.fragment_list)

        var recyclerView: RecyclerView = findViewById(R.id.list_museum)


        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val items = ArrayList<Museum>()

        //adding some dummy data to the list
        items.add(Museum("ICA", "123 Washington Street"))
        items.add(Museum("Philadelphia Museum of Art", "321 Chestnut Street"))

        val adapter = MuseumListAdapter(items)

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter

*/
        setContentView(R.layout.fragment_list)

        var recyclerView: RecyclerView = findViewById(R.id.list_museum)


        val myLayoutManager = LinearLayoutManager(this)
        myLayoutManager.orientation = LinearLayout.VERTICAL
        recyclerView.layoutManager = myLayoutManager

        // recyclerView.layoutManager = LinearLayoutManager(getActivity()!!, LinearLayout.VERTICAL, true)
        val items = ArrayList<Museum>()

        //adding some dummy data to the list
        items.add(Museum("ICA", "123 Auburn Street"))
        items.add(Museum("Philadelphia Museum of Art", "321 Chestnut Street"))

        val adapter = MuseumListAdapter(items)

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter
        recyclerView.adapter.notifyDataSetChanged()
    }




}
