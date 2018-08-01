package com.deframe.artapp.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.deframe.artapp.R
import com.deframe.artapp.R.id.editText
import com.deframe.artapp.helper.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL


/**
 * This class handles the museum list view screen
 */
class BotFragment : Fragment() {

    //private var chatMessages: ArrayList<String> = null
    private var chatMessageJson : JSONObject ?= null
    //var chatView: RecyclerView? = null
    companion object {
        val TAG: String = BotFragment::class.java.simpleName
        fun newInstance() = BotFragment()
    }

    /**
     * Handles onCreate actions of the activity
     *
     * @param savedInstanceState
     * @param inflater
     * @param container parent view group
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_bot)
        super.onCreate(savedInstanceState)
        var view = inflater.inflate(R.layout.fragment_bot, container, false)
        var chatView: RecyclerView= view.findViewById(R.id.chatView)
        var editText: EditText = view.findViewById(R.id.editText)
        var addBtn: RelativeLayout =  view.findViewById<RelativeLayout>(R.id.addBtn)
        val chatMessages = ArrayList<ChatMessage>()
        //recyclerView.fix

        val myLayoutManager = LinearLayoutManager(context)
        myLayoutManager.orientation = LinearLayout.VERTICAL
        chatView.layoutManager = myLayoutManager
        var cnt:Int = 0
        addBtn.setOnClickListener {
            val usermessage = editText.text.toString().trim()
            cnt = cnt + 1
            //Toast.makeText(this.context, "Hello"+message, Toast.LENGTH_LONG).show()

            //val mainPageIntent = Intent(this, MainActivity::class.java)
            //startActivity(mainPageIntent)

            //////////

            var recyclerView: RecyclerView = view.findViewById(R.id.chatView)

            //val myLayoutManager = LinearLayoutManager(context)
            //myLayoutManager.orientation = LinearLayout.VERTICAL
            recyclerView.layoutManager = myLayoutManager


            //runs a thread to call the database
            var t = Thread(Runnable {
                getSpeech(usermessage)
            })
            t.start()
            t.join()

            //goes through json array to create Museum array list
            val response = parseResponse(chatMessageJson, usermessage)
            chatMessages.add(response)
            val adapter = ChatListAdapter(chatMessages ,response)

            //add the adapter to recyclerview
            //recyclerView.adapter = adapter


            ///////////

            //var recyclerView: RecyclerView = view.findViewById(R.id.list_museum)
            /*
            val myLayoutManager = LinearLayoutManager(context)
            myLayoutManager.orientation = LinearLayout.VERTICAL
            chatView.layoutManager = myLayoutManager

            val items = ArrayList<Museum>()

            //add dummy data
            //items.add(Museum("ICA", "123 Washington Street"))
            //items.add(Museum("Philadelphia Museum of Art", "321 Chestnut Street"))

            val adapter = ChatListAdapter(items)

            //add the adapter to recyclerview
            */
            recyclerView.adapter = adapter

            //Things to do after sending text
            editText.text.clear()
            
        }

        /*editText.addTextChangedListener(watcher::TextWatcher ){

        }*/

        return view
    }

    /**
     * Handles onViewCreated actions of the activity
     *
     * @param savedInstanceState
     * @param view
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*var recyclerView: RecyclerView = view.findViewById(R.id.list_museum)

        val myLayoutManager = LinearLayoutManager(context)
        myLayoutManager.orientation = LinearLayout.VERTICAL
        recyclerView.layoutManager = myLayoutManager

        val items = ArrayList<Museum>()

        //add dummy data
        items.add(Museum("ICA", "123 Washington Street"))
        items.add(Museum("Philadelphia Museum of Art", "321 Chestnut Street"))

        val adapter = MuseumListAdapter(items)

        //add the adapter to recyclerview
        recyclerView.adapter = adapter*/
    }


    /**
     * Call to database to get all museums' JSONArray
     */
    fun getSpeech(usermessage : String): Unit {
        val result = URL(Constants.API_TEST + "/deframe-bot/"+usermessage).readText()
        val obj = JSONObject(result)
        chatMessageJson = obj
    }


    /**
     * Creates list of museum objects from JSONArray
     *
     * @param arr JSONArray from database
     * @return ArrayList of Museum objects
     */
    fun parseResponse(obj : JSONObject?, usermessage: String): ChatMessage{
        val msg = ChatMessage("",usermessage,"",obj)
        val response = obj!!.get("speech").toString()

        msg.setLeftText(response)
        return msg
    }
}