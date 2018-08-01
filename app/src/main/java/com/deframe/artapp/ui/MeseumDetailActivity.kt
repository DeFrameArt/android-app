package com.deframe.artapp.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.deframe.artapp.R
import com.deframe.artapp.R.id.list
import com.deframe.artapp.helper.Constants
import com.deframe.artapp.helper.Constants.Companion.API_TEST
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_museum_details.*
import kotlinx.android.synthetic.main.fragment_map.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

/**
 * This class handles the museum detail screen
 */
class MuseumDetailActivity : AppCompatActivity() {

    /**
     *Handles onCreate actions of the activity
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum_details)
        val intent = intent
        var string = intent.getStringExtra("info")
        var museum = JSONObject(string)

        button_map.setOnClickListener {
            val floorplanIntent = Intent(this, FloorplanActivity::class.java)
            floorplanIntent.putExtra("id", 1)
            startActivity(floorplanIntent)
        }

        //info preview card on click listener, starts museum detail activity
        button_frida.setOnClickListener {
            //Create a Activity
            Toast.makeText(this, " Under Construction ", Toast.LENGTH_LONG).show()
            //val botIntent = Intent(this, BotFragment::class.java)
            //museumDetailIntent.putExtra("info", selectedMuseum.toString())
            //startActivity(botIntent)
        }

        //set museum information text
        museum_name.text = museum.get("name").toString()
        address_street.text = museum.get("street").toString()
        address_city.text = museum.get("city").toString() + ", " + museum.get("state").toString() + " " + museum.get("zip").toString()
        address_country.text = museum.get("country").toString()

        //load museum banner & logo
        Picasso.get()
                .load(museum.get("bannerUrl").toString())
                .fit()
                .centerCrop()
                .into(museumbgimage);
        Picasso.get()
                .load(museum.get("logoUrl").toString())
                .fit()
                .centerCrop()
                .into(museumlogo);

        //Load featured images
        var featuredImgArr: JSONArray? = null

        val museumId = museum.get("id").toString()

        //runs thread to call featrued images database
        var t = Thread(Runnable {
            var url = Constants.API_TEST+ "/museums/" + museumId + "/featuredimages"
            val result = URL(url).readText()
            featuredImgArr = JSONArray(result)
        })

        t.start()
        t.join()

        //load featured images to the bottom row
        Picasso.get()
                .load(getImgUrl(featuredImgArr, 0))
                .fit()
                .centerCrop()
                .into(collectionImg1)

        Picasso.get()
                .load(getImgUrl(featuredImgArr, 1))
                .fit()
                .centerCrop()
                .into(collectionImg2)

        Picasso.get()
                .load(getImgUrl(featuredImgArr, 2))
                .fit()
                .centerCrop()
                .into(collectionImg3)

        //load featured collections' names to bottom row
        var collectionName1 = getfeaturedImgTxt(featuredImgArr, 0)
        var collectionName2 = getfeaturedImgTxt(featuredImgArr, 1)
        var collectionName3 = getfeaturedImgTxt(featuredImgArr, 2)
        collection1.text = collectionName1
        collection2.text = collectionName2
        collection3.text = collectionName3

        //set onClickListeners to collections
        collectionImg1.setOnClickListener {
            launchCollectionPage(collectionName1, museumId)
        }

        collectionImg2.setOnClickListener {
            launchCollectionPage(collectionName2, museumId)
        }

        collectionImg3.setOnClickListener {
            launchCollectionPage(collectionName3, museumId)
        }
    }

    /**
     * starts new collection intent
     *
     * @param collectionName
     * @param museumId
     */
    fun launchCollectionPage(collctionName: String, museumId: String): Unit {
        val collectionIntent = Intent(this, CollectionActivity::class.java)
        collectionIntent.putExtra("collection", collctionName)
        collectionIntent.putExtra("id", museumId)
        startActivity(collectionIntent)
    }

    /**
     * @param list JSON array of featued images
     * @param index of interested JSON object
     * @return url of specified image
     */
    fun getImgUrl(list: JSONArray?, index: Int): String {
        return list!!.getJSONObject(index).get("url").toString()
    }

    /**
     * @param list JSON array of featued images
     * @param index of interested JSON object
     * @return name of specified collection
     */
    fun getfeaturedImgTxt(list: JSONArray?, index: Int): String {
        return list!!.getJSONObject(index).get("name").toString()
    }
}
