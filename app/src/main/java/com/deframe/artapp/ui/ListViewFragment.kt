package com.deframe.artapp.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import com.deframe.artapp.helper.Constants
import com.deframe.artapp.helper.Museum
import com.deframe.artapp.helper.MuseumListAdapter
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import org.json.JSONArray
import java.net.URL

/**
 * This class handles the museum list view screen
 */
class ListViewFragment : android.support.v4.app.Fragment() {

    private var museumList: JSONArray? = null

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


        //runs a thread to call the database
        var t = Thread(Runnable {
            getMuseumJSONArray()
        })
        t.start()
        t.join()

        //goes through json array to create Museum array list
        val items = getAllMuseumsList(museumList!!)

        val adapter = MuseumListAdapter(items)

        //add the adapter to recyclerview
        recyclerView.adapter = adapter
    }

    /**
     * Call to database to get all museums' JSONArray
     */
    fun getMuseumJSONArray(): Unit {
        val result = URL(Constants.API_TEST + "/museums").readText()
        val list = JSONArray(result)
        museumList = list
    }


    /**
     * Creates list of museum objects from JSONArray
     *
     * @param arr JSONArray from database
     * @return ArrayList of Museum objects
     */
    fun getAllMuseumsList(arr :JSONArray):ArrayList<Museum>{
        val list = ArrayList<Museum>()

        for (i in 0 until arr!!.length()) {
            var museumId = arr.getJSONObject(i).get("id").toString().toInt()
            var name = arr.getJSONObject(i).get("name").toString()
            var address =  museumList!!.getJSONObject(i).get("street").toString() + ", "+
                    museumList!!.getJSONObject(i).get("city").toString() + " " +
                    museumList!!.getJSONObject(i).get("zip").toString()
            var url = arr.getJSONObject(i).get("bannerUrl").toString()
            var museumJSONObject = arr.getJSONObject(i)
            list.add(Museum(museumId, name,address,url,museumJSONObject))
        }
        return list
    }
}
