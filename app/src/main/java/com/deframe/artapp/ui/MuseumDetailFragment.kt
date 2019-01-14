package com.deframe.artapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
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
import kotlinx.android.synthetic.main.activity_museum_details.*


/**
 * This class handles the museum list view screen
 */
class MuseumDetailFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var imageGalleryAdapter: ImageGalleryAdapter
    private var museum : JSONObject? = null
            companion object {
        val TAG: String = MuseumDetailFragment::class.java.simpleName
        fun newInstance() = MuseumDetailFragment()
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
        var view = inflater.inflate(R.layout.activity_museum_details, container, false)
        var string = arguments!!.getString("info")
        museum = JSONObject(string)
        var buttonMap: Button =  view.findViewById(R.id.button_map)
        var buttonFrida: Button =  view.findViewById(R.id.button_frida)
        var museum_name: TextView = view.findViewById(R.id.museum_name)
        var address_street: TextView = view.findViewById(R.id.address_street)
        var address_city: TextView = view.findViewById(R.id.address_city)
        var address_country: TextView = view.findViewById(R.id.address_country)
        var museumbgimage: ImageView =  view.findViewById(R.id.museumbgimage)
        var museumlogo: ImageView =  view.findViewById(R.id.museumlogo)

        buttonMap.setOnClickListener {
            Toast.makeText(this.context, " Under Construction ", Toast.LENGTH_LONG).show()
            //val floorplanIntent = Intent(this, FloorplanActivity::class.java)
            //floorplanIntent.putExtra("id", 1)
            //startActivity(floorplanIntent)
        }

        //info preview card on click listener, starts museum detail activity
        buttonFrida.setOnClickListener {
            //Create a Activity
            Toast.makeText(this.context, " Under Construction ", Toast.LENGTH_LONG).show()
            //val botIntent = Intent(this, BotFragment::class.java)
            //museumDetailIntent.putExtra("info", selectedMuseum.toString())
            //startActivity(botIntent)
        }

        //set museum information text
        activity?.title = museum!!.get("name").toString()
        museum_name.text = museum!!.get("name").toString()
        address_street.text = museum!!.get("street").toString()
        address_city.text = museum!!.get("city").toString() + ", " + museum!!.get("state").toString() + " " + museum!!.get("zip").toString()
        address_country.text = museum!!.get("country").toString()

        //load museum banner & logo
        Picasso.get()
                .load(museum!!.get("bannerUrl").toString())
                .fit()
                .centerCrop()
                .into(museumbgimage);
        Picasso.get()
                .load(museum!!.get("logoUrl").toString())
                .fit()
                .centerCrop()
                .into(museumlogo);

        //Load the Collection view
        val layoutManager = GridLayoutManager(this.requireContext(), 2)
        val museumId = museum!!.get("id").toString()
        recyclerView = view.findViewById(R.id.rv_images)
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = layoutManager
        imageGalleryAdapter = ImageGalleryAdapter(this.requireContext(), Painting.getPaintings(museumId))
        return view
    }

    override fun onStart() {
        super.onStart()
        recyclerView.adapter = imageGalleryAdapter
    }

    private inner class ImageGalleryAdapter(val context: Context, val paintings: ArrayList<Painting>)
        : RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageGalleryAdapter.MyViewHolder {
            val context = parent.context
            val inflater = LayoutInflater.from(context)
            val photoView = inflater.inflate(R.layout.item_image, parent, false)
            return MyViewHolder(photoView)
        }

        override fun onBindViewHolder(holder: ImageGalleryAdapter.MyViewHolder, position: Int) {
            val sunsetPhoto = paintings[position]
            val imageView = holder.photoImageView

            Picasso.get()
                    .load(sunsetPhoto.url)
                    .placeholder(R.drawable.placeholder)
                    .fit()
                    .error(R.drawable.error)
                    .fit()
                    .tag(context)
                    .into(imageView)

        }

        override fun getItemCount(): Int {
            return paintings.size
        }


        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

            var photoImageView: ImageView = itemView.findViewById(R.id.iv_photo)

            init {
                itemView.setOnClickListener(this)
            }

            override fun onClick(view: View) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val painting = paintings[position]
                    val activity = view.getContext() as AppCompatActivity
                    val myFragment = PaintingFragment()
                    val bundle = Bundle()
                    bundle.putParcelable (PaintingFragment.EXTRA_SUNSET_PHOTO, painting)
                    bundle.putString("JsonObj", painting.Obj)
                    bundle.putString("museumName", museum!!.get("name").toString())
                    myFragment.arguments = bundle
                    activity.supportFragmentManager.beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit()


                }

            }

        }

    }
}