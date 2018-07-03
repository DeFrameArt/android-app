package com.deframe.artapp.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.deframe.artapp.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.GoogleMap
import kotlinx.android.synthetic.main.fragment_map.*
import android.view.InflateException
import com.deframe.artapp.R.id.*
import com.deframe.artapp.helper.Museum
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.deframe.artapp.helper.Constants
import com.deframe.artapp.helper.Constants.Companion.API_TEST

/**
 * This class handles the map fragment screen
 *
 * @property mMap stores the google map reference
 * @property rootView stores the view
 * @property museumList stores the original array of museums
 * @property selectedMuseum stores the last selected museum marker
 */
class MapViewFragment : Fragment(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null
    private var rootView: View? = null
    private var museumList: JSONArray? = null
    private var selectedMuseum: JSONObject? = null

    companion object {
        val TAG: String = MapViewFragment::class.java.simpleName
        fun newInstance() = MapViewFragment()
    }

    /**
     * Handles onCreate actions of the activity
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return root view of this fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (rootView != null) {
            var parent: ViewGroup? = null
            parent = rootView?.getParent() as? ViewGroup
            parent?.removeView(rootView)
        }
        try {
            rootView = inflater.inflate(R.layout.fragment_map, container, false)
        } catch (e: InflateException) {
            /* map is already there, just return view as it is  */
        }

        //runs a thread to call the database
        var t = Thread(Runnable {
            getAllMuseums()
        })
        t.start()
        t.join()

        return rootView
    }

    /**
     * Sync to google map
     *
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var supportMapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance()
            childFragmentManager.beginTransaction().replace(R.id.map, supportMapFragment).commit()
        }
        supportMapFragment?.getMapAsync(this)
    }

    /**
     * add markers to the map
     *
     * @param arr JSONArray containing museum information
     */
    fun addMarkers(arr: JSONArray): Unit {
        for (i in 0 until arr.length()) {
            var name = arr.getJSONObject(i).get("name").toString()
            var lng = arr.getJSONObject(i).get("lng").toString().toDouble()
            var lat = arr.getJSONObject(i).get("lat").toString().toDouble()
            var location = LatLng(lat, lng)

            //resize icon before adding as marker
            val imageBitmap = BitmapFactory.decodeResource(context?.getResources(),
                    R.drawable.ic_pin_map);
            var v = Bitmap.createScaledBitmap(imageBitmap, 63, 80, false)
            var marker = mMap?.addMarker((MarkerOptions()
                    .position(location)
                    .icon(BitmapDescriptorFactory.fromBitmap(v))))

            marker?.tag = i
        }
    }

    /**
     * Call to database to get all museums' JSONArray
     */
    fun getAllMuseums(): Unit {
        val result = URL(Constants.API_TEST + "/museums").readText()
        val list = JSONArray(result)
        museumList = list
    }

    /**
     * handles set up and actions of the map
     *
     * @param map
     */
    override fun onMapReady(map: GoogleMap?) {

        //set info card as invisble at first
        var infoCard = infoCard
        infoCard.visibility = View.INVISIBLE

        mMap = map

        //Camera zoom to Boston
        val myPlace = LatLng(42.36, -71.05)  //Boston
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(myPlace))
        mMap?.animateCamera(CameraUpdateFactory.zoomTo(15.0f))
        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace, 12.0f))
        mMap?.getUiSettings()?.setZoomControlsEnabled(true)

        //add museum markers to the map
        addMarkers(museumList!!)

        Toast.makeText(this.context, "OnMapReady end", Toast.LENGTH_LONG).show()

        //if marker gets clicked, info card shows up with selected museum's information
        mMap?.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean {

                infoCard.visibility = View.VISIBLE
                preview_museum.text = museumList!!.getJSONObject(marker.tag as Int).get("name").toString()
                preview_address.text = museumList!!.getJSONObject(marker.tag as Int).get("street").toString()
                preview_city.text = museumList!!.getJSONObject(marker.tag as Int).get("city").toString() + " " +
                        museumList!!.getJSONObject(marker.tag as Int).get("state").toString() + ", " +
                        museumList!!.getJSONObject(marker.tag as Int).get("zip").toString()
                selectedMuseum = museumList!!.getJSONObject(marker.tag as Int)
                Picasso.get()
                        .load(museumList!!.getJSONObject(marker.tag as Int).get("bannerUrl").toString())
                        .fit()
                        .centerCrop()
                        .into(preview_img);
                return false
            }
        })

        //info preview card on click listener, starts museum detail activity
        infoCard.setOnClickListener {
            val museumDetailIntent = Intent(this.context, MuseumDetailActivity::class.java)
            museumDetailIntent.putExtra("info", selectedMuseum.toString())
            startActivity(museumDetailIntent)
        }
    }
}



