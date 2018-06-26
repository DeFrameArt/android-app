package com.deframe.artapp.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.deframe.artapp.R
import com.deframe.artapp.R.id.list
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_museum_details.*
import kotlinx.android.synthetic.main.fragment_map.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL


class MuseumDetailActivity : AppCompatActivity() {

    /**
     *
     * @param savedInstanceState
     * @return Unit
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum_details)
        val intent = intent
        var string = intent.getStringExtra("info")
        var museum = JSONObject(string)
       museum_name.text =  museum.get("name").toString()
        address_street.text =  museum.get("street").toString()
        address_city.text = museum.get("city").toString() + ", " + museum.get("state").toString() +  " " + museum.get("zip").toString()
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
        var featuredImgArr : JSONArray?= null



        var t = Thread(Runnable {
           var museumId = museum.get("id").toString()
            var url = "http://deframe-test-api.us-east-1.elasticbeanstalk.com:80/museums/" + museumId + "/featuredimages"
            val result = URL(url).readText()
            featuredImgArr = JSONArray(result)

        })

        t.start()
        t.join()

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
        collection1.text = getfeaturedImgTxt(featuredImgArr, 0)
        collection2.text = getfeaturedImgTxt(featuredImgArr, 1)
        collection3.text = getfeaturedImgTxt(featuredImgArr, 2)
    }


    fun getImgUrl(list:JSONArray?, index : Int) : String {
        return list!!.getJSONObject(index).get("url").toString()
    }

    fun getfeaturedImgTxt(list:JSONArray?, index : Int) : String {
        return list!!.getJSONObject(index).get("name").toString()
    }



}
