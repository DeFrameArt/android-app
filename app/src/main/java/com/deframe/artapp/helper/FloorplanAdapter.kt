package com.deframe.artapp.helper

import android.app.PendingIntent.getActivity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.deframe.artapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_map.*
import java.util.*
import android.support.v4.view.PagerAdapter
import android.widget.*

/**
 * This class is the custom adapter of floorplans
 */
class FloorplanAdapter(floorplanList: ArrayList<Museum>, ctx: Context) : PagerAdapter() {
    var ctx: Context
    var floorplanList : ArrayList<Museum>

    init {
        this.ctx = ctx
        this.floorplanList = floorplanList
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return false
    }

    override fun getCount(): Int {
        return floorplanList.size

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var item_view: View = layoutInflater.inflate(R.layout.floorplan_item, null)
        val img = item_view!!.findViewById<ImageView>(R.id.floorplan_item)
        val text = item_view!!.findViewById<TextView>(R.id.textView2)

        var item = floorplanList[position]
        text.text=item.getName()
        Picasso.get()
                .load(item.getUrl())
                .fit()
                .centerCrop()
                .into(img);

        container.addView(item_view)
        return item_view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}
