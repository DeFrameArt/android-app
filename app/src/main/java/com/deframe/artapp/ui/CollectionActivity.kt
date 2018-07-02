package com.deframe.artapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.deframe.artapp.R
import com.deframe.artapp.R.id.*
import com.deframe.artapp.helper.Constants
import com.deframe.artapp.helper.Constants.Companion.API_TEST
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_collection.*
import kotlinx.android.synthetic.main.activity_museum_details.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.util.*

/**
 * This class handles the collection screen
 *
 * @property collectionArr stores the original JSON Array
 * @property paintingList stores the list of paintings in the selected museum
 */
class CollectionActivity : AppCompatActivity() {
    var collectionArr: JSONArray = JSONArray()
    var paintingList: LinkedList<JSONObject> = LinkedList()

    /**
     * Handles onCreate actions of the activity
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)

        val intent = intent
        val collectionName = intent.getStringExtra("collection")
        val museumId = intent.getStringExtra("id")

        //runs thread to call database
        var t = Thread(Runnable {
            var url = Constants.API_TEST + "/museums/" + museumId + "/gallery"
            val result = URL(url).readText()
            collectionArr = JSONArray(result)

        })
        t.start()
        t.join()

        //get the paintings from the selected collection
        paintingList = getPaintings(collectionArr, collectionName)

        //loads the first painting in the list to the screen
        loadMainPainting(paintingList.get(0))

        //load first 3 paintings and add onclick listeners
        loadCollectionPreview(paintingList)
    }

    /**
     * @param arr json array of paintings from that museum
     * @param collName name of collection selected
     * @return list of paintings that belong to the specified collection name
     */
    fun getPaintings(arr: JSONArray, collName: String): LinkedList<JSONObject> {
        var list: LinkedList<JSONObject> = LinkedList<JSONObject>()
        for (i in 0 until arr.length()) {
            if (arr.getJSONObject(i).get("featuretype").equals(collName)) {
                list.add(arr.getJSONObject(i))
            }
        }
        return list
    }

    /**
     * Loads the painting's details to the screen
     * @param obj json object of specified painting
     */
    fun loadMainPainting(obj: JSONObject) {
        painting_title.text = obj.get("name").toString()
        artist.text = obj.get("artist").toString()
        description.text = obj.get("description").toString()
        date.text = obj.get("year").toString()
        Picasso.get()
                .load(obj.get("url").toString())
                .fit()
                .centerCrop()
                .into(painting)
    }

    /**
     * Loads the bottom collection list preview images and set listeners
     * @param objList json object list of collection's paintings
     */
    fun loadCollectionPreview(objList: LinkedList<JSONObject>): Unit {

        //Load 1st painting's image
        Picasso.get()
                .load(objList.get(0).get("url").toString())
                .fit()
                .centerCrop()
                .into(painting1)
        //Set 1st painting's image's onclick listener, main painting switches to the one clicked
        painting1.setOnClickListener {
            loadMainPainting(paintingList.get(0))
        }
        //Load 2nd painting's image
        Picasso.get()
                .load(objList.get(1).get("url").toString())
                .fit()
                .centerCrop()
                .into(painting2)
        //Set 2nd painting's image's onclick listener, main painting switches to the one clicked
        painting2.setOnClickListener {
            loadMainPainting(paintingList.get(1))
        }
        //Load 3rd painting's image
        Picasso.get()
                .load(objList.get(2).get("url").toString())
                .fit()
                .centerCrop()
                .into(painting3)
        //Set 3rd painting's image's onclick listener, main painting switches to the one clicked
        painting3.setOnClickListener {
            loadMainPainting(paintingList.get(2))
        }
    }
}
