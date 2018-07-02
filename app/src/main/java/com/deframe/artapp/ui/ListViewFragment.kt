package com.deframe.artapp.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.deframe.artapp.R
import com.deframe.artapp.R.id.list_museum
import com.deframe.artapp.helper.Museum
import com.deframe.artapp.helper.MuseumListAdapter

/**
 * This class handles the museum list view screen
 */
class ListViewFragment : android.support.v4.app.Fragment() {

    companion object {
        val TAG: String = ListViewFragment::class.java.simpleName
        fun newInstance() = ListViewFragment()
    }

    /**
     * Handles onCreate actions of the activity
     *
     * @param savedInstanceState
     * @param inflater
     * @param container parent view group
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_list)
        super.onCreate(savedInstanceState)
        var view = inflater.inflate(R.layout.fragment_list, container, false)
        return view
    }

    /**
     * Handles onViewCreated actions of the activity
     *
     * @param savedInstanceState
     * @param view
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var recyclerView: RecyclerView = view.findViewById(R.id.list_museum)

        val myLayoutManager = LinearLayoutManager(context)
        myLayoutManager.orientation = LinearLayout.VERTICAL
        recyclerView.layoutManager = myLayoutManager

        val items = ArrayList<Museum>()

        //add dummy data
        items.add(Museum("ICA", "123 Washington Street"))
        items.add(Museum("Philadelphia Museum of Art", "321 Chestnut Street"))

        val adapter = MuseumListAdapter(items)

        //add the adapter to recyclerview
        recyclerView.adapter = adapter
    }
}
