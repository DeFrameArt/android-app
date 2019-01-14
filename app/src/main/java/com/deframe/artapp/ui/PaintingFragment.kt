package com.deframe.artapp.ui

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.graphics.Palette
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.deframe.artapp.R
import com.deframe.artapp.helper.*
import org.json.JSONObject
import java.net.URL
import android.text.Editable
import android.text.TextWatcher
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_collection.*


/**
 * This class handles the museum list view screen
 */
class PaintingFragment : Fragment() {

    companion object {
        val TAG: String = PaintingFragment::class.java.simpleName
        const val EXTRA_SUNSET_PHOTO = "PaintingFragment.EXTRA_SUNSET_PHOTO"
        fun newInstance() = PaintingFragment()
    }

    private lateinit var imageView: ImageView
    private lateinit var painting: Painting
    private lateinit var jsonObject: JSONObject
    private lateinit var painting_title: TextView
    private lateinit var artist: TextView
    private lateinit var description: TextView
    private lateinit var date: TextView
    /**
     * Handles onCreate actions of the activity
     *
     * @param savedInstanceState
     * @param inflater
     * @param container parent view group
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        var view = inflater.inflate(R.layout.activity_painting, container, false)
        activity?.title = arguments!!.getString("museumName").toString()
        painting = arguments!!.getParcelable(PaintingFragment.EXTRA_SUNSET_PHOTO)
        imageView = view.findViewById(R.id.image)
        jsonObject = JSONObject(painting.Obj)
        painting_title = view.findViewById(R.id.painting_title)
        artist = view.findViewById(R.id.artist)
        description = view.findViewById(R.id.description)
        date = view.findViewById(R.id.date)

        loadMainPainting(jsonObject)

        return view
    }

    override fun onStart() {
        super.onStart()


        Picasso.get()
                .load(painting.url)
                .placeholder(R.drawable.placeholder)
                .fit()
                .error(R.drawable.error)
                .fit()
                .priority(Picasso.Priority.HIGH)
                .into(imageView)

        Picasso.get()
                .load(painting.url)
                .placeholder(R.drawable.placeholder)
                .fit()
                .error(R.drawable.error)
                .fit()
                .priority(Picasso.Priority.LOW)
                .into(imageView)

        Picasso.get()
                .load(painting.url)
                .placeholder(R.drawable.placeholder)
                .fit()
                .error(R.drawable.error)
                .fit()
                .priority(Picasso.Priority.NORMAL)
                .into(imageView)

    }

    fun onPalette(palette: Palette?) {
        if (null != palette) {
            val parent = imageView.parent.parent as ViewGroup
            parent.setBackgroundColor(palette.getDarkVibrantColor(Color.GRAY))
        }
    }

    /**
     * Loads the painting's details to the screen
     * @param obj json object of specified painting
     */
    private fun loadMainPainting(obj: JSONObject) {
        painting_title.text = obj.get("name").toString()
        artist.text = obj.get("artist").toString()
        description.text = obj.get("description").toString()
        date.text = obj.get("year").toString()
    }
}