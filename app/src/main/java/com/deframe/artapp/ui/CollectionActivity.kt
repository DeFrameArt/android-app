package com.deframe.artapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.deframe.artapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_collection.*
import kotlinx.android.synthetic.main.activity_museum_details.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.util.*

class CollectionActivity : AppCompatActivity() {
    var collectionArr : JSONArray = JSONArray()
    var paintingList : LinkedList<JSONObject> = LinkedList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)

        val intent = intent
        var collectionName = intent.getStringExtra("collection")
        var museumId =  intent.getStringExtra("id")

        var t = Thread(Runnable {
            var url = "http://deframe-test-api.us-east-1.elasticbeanstalk.com:80/museums/" + museumId + "/gallery"
            val result = URL(url).readText()
            collectionArr = JSONArray(result)

        })

        t.start()
        t.join()

        paintingList = getPaintings(collectionArr, collectionName)



        loadMainPainting(paintingList.get(0))

        //load first 3 paintings and add onclick listeners
        loadCollectionPreview(paintingList)
    }


    fun getPaintings (arr : JSONArray, collName : String): LinkedList<JSONObject>{
        var list :  LinkedList<JSONObject> = LinkedList<JSONObject>()
        for (i in 0 until arr.length() ) {
            if(arr.getJSONObject(i).get("featuretype").equals(collName)){
                list.add(arr.getJSONObject(i))
            }
        }

        return list


    }

    fun loadMainPainting(obj : JSONObject) {


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

    fun loadCollectionPreview(objList : LinkedList<JSONObject>) :Unit {

        //1
        Picasso.get()
                .load(objList.get(0).get("url").toString())
                .fit()
                .centerCrop()
                .into(painting1)

        painting1.setOnClickListener {
            loadMainPainting(paintingList.get(0))
        }
        //2
        Picasso.get()
                .load(objList.get(1).get("url").toString())
                .fit()
                .centerCrop()
                .into(painting2)

        painting2.setOnClickListener {
            loadMainPainting(paintingList.get(1))
        }
        //3
        Picasso.get()
                .load(objList.get(2).get("url").toString())
                .fit()
                .centerCrop()
                .into(painting3)
        painting3.setOnClickListener {
            loadMainPainting(paintingList.get(2))
        }
    }
}
