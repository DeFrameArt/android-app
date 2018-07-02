package com.deframe.artapp.helper

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.deframe.artapp.R
import java.util.*

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
            txtName.text = data.getName()
            txtAddress.text = data.getAddress()

            //set the onclick listener for the singlt list item
            itemView.setOnClickListener({
                Log.e("ItemClicked", data.getName())
            })
        }
    }
}
