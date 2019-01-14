package com.deframe.artapp.helper


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONArray
import java.net.URL

@Parcelize
data class Painting(val url: String, val Obj: String) : Parcelable {

    companion object {

        /**
         * Gets list of museum objects, url from JSONArray
         *
         * @param arr JSONArray from database
         * @return ArrayList of Museum objects
         */
        fun getGallery(arr :JSONArray?):ArrayList<Painting>{
            val list = ArrayList<Painting>()

            for (i in 0 until arr!!.length()) {

                var url = arr!!.getJSONObject(i).get("url").toString()
                var museumJSONObject = arr!!.getJSONObject(i)
                list.add(Painting(url, museumJSONObject.toString()))
            }
            return list
        }

        /**
         * Gets painting
         */
        fun getPaintings(museumId: String): ArrayList<Painting> {
            var featuredImgArr: JSONArray? = null
            //runs thread to call featrued images database
            var t = Thread(Runnable {
                var url = Constants.API_TEST+ "/museums/" + museumId + "/gallery"
                val result = URL(url).readText()
                featuredImgArr = JSONArray(result)
            })

            t.start()
            t.join()

            return getGallery(featuredImgArr)

        }
    }
}
