package com.deframe.artapp.helper

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.deframe.artapp.R
import com.deframe.artapp.R.id.list_item_museumImage
import com.deframe.artapp.R.id.preview_img
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_map.*
import java.util.*
import android.support.v4.content.ContextCompat.startActivity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*
import com.deframe.artapp.ui.*

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
            val txtName = itemView!!.findViewById<TextView>(R.id.list_item_museumName)
            val txtAddress = itemView!!.findViewById<TextView>(R.id.list_item_museumAddress)
            val img = itemView!!.findViewById<ImageView>(R.id.list_item_museumImage)
            txtName.text = data.getName()
            txtAddress.text = data.getAddress()

            Picasso.get()
                    .load(data.getUrl())
                    .fit()
                    .centerCrop()
                    .into(img)

            //Call the fragment
            itemView.setOnClickListener{
                val activity = itemView.getContext() as AppCompatActivity
                val myFragment = MuseumDetailFragment()
                val bundle = Bundle()
                bundle.putString("info", data.getJson().toString())
                myFragment.arguments = bundle
                activity.supportFragmentManager.beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit()

            }

        }

        /**
         * Immediately execute transactions with FragmentManager#executePendingTransactions.
         */
        /*
        private fun switchFragment(navPosition: BottomNavigationPosition): Boolean {
            val fragment = supportFragmentManager.findFragment(navPosition)
            if (fragment.isAdded) return false
            detachFragment()
            attachFragment(fragment, navPosition.getTag())
            supportFragmentManager.executePendingTransactions()
            return true
        }
        */
    }
}
