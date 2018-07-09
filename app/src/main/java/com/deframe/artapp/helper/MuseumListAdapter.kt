package com.deframe.artapp.helper

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.deframe.artapp.R
import com.deframe.artapp.R.id.list_item_museumImage
import com.deframe.artapp.R.id.preview_img
import com.deframe.artapp.ui.ListViewFragment
import com.deframe.artapp.ui.MuseumDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_map.*
import java.util.*
import android.support.v4.content.ContextCompat.startActivity
import android.widget.AdapterView
import com.deframe.artapp.ui.MainActivity
import android.os.Bundle




/**
 * This class is the custom adapter of the museum list screen
 */
class MuseumListAdapter(val museumList: ArrayList<Museum>) : RecyclerView.Adapter<MuseumListAdapter.ViewHolder>() {
    /**
     * Handles onCreate actions of the activity
     *
     * @param parent parent view group
     * @param viewType
     * @return MuseumListAdapter.ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuseumListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_museum_item, parent, false)
        return ViewHolder(v)
    }

    /**
     * Handles onCreate actions of the activityBinds
     *
     * @param holder
     * @param position position of the item
     */
    override fun onBindViewHolder(holder: MuseumListAdapter.ViewHolder, position: Int) {
        holder.bindItems(museumList[position])
    }

    /**
     *
     * @return number of museum
     */
    override fun getItemCount(): Int {
        return museumList.size
    }



    /**
     * This inner class is the custom view holder of the museum list screen
     *
     * @param itemView
     * @return RecyclerView.ViewHolder
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(data: Museum) {
            val txtName = itemView.findViewById<TextView>(R.id.list_item_museumName)
            val txtAddress = itemView.findViewById<TextView>(R.id.list_item_museumAddress)
            val img = itemView.findViewById<ImageView>(R.id.list_item_museumImage)
            txtName.text = data.getName()
            txtAddress.text = data.getAddress()

            Picasso.get()
                    .load(data.getUrl())
                    .fit()
                    .centerCrop()
                    .into(img);


            //set the onclick listener for the singlt list item
            itemView.setOnClickListener({
                val museumDetailIntent = Intent(itemView.context, MuseumDetailActivity::class.java)
                val bundle = Bundle()
                museumDetailIntent.putExtra("info",data.getJson().toString())
                startActivity(itemView.context,museumDetailIntent,bundle)
            })
        }
    }
}
