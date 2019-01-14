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
class ChatListAdapter(val chatMessages : ArrayList<ChatMessage>, val chatMessage: ChatMessage) : RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {

    //val cnt : Int = 0
    /**
     * Handles onCreate actions of the activity
     *
     * @param parent parent view group
     * @param viewType
     * @return MuseumListAdapter.ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragement_chat_list, parent, false)
        return ViewHolder(v)
    }

    /**
     * Handles onCreate actions of the activityBinds
     *
     * @param holder
     * @param position position of the item
     */
    override fun onBindViewHolder(holder: ChatListAdapter.ViewHolder, position: Int) {
        holder.bindItems(chatMessages[position])
    }

    /**
     *
     * @return number of museum
     */
   override fun getItemCount(): Int {
        return chatMessages.size;
    }


    /**
     * This inner class is the custom view holder of the museum list screen
     *
     * @param itemView
     * @return RecyclerView.ViewHolder
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(data: ChatMessage) {
            val leftText = itemView!!.findViewById<TextView>(R.id.leftText)
            val rightText = itemView!!.findViewById<TextView>(R.id.rightText)
            leftText.text = data.getLeftText()
            rightText.text = data.getRightText()
        }
    }
}
