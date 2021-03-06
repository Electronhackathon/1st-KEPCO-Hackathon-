package kr.wonjun.electhon

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.content_place.view.*
import kr.wonjun.electhon.models.Map

import java.util.ArrayList

class PlaceRecyclerViewAdapter(private var items: ArrayList<Map>, var context: Context) : RecyclerView.Adapter<PlaceRecyclerViewAdapter.ViewHolder>() {
    var onClick: OnListItemClick? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position].title
        holder.distance.text = "${items[position].address}m"

        holder.itemView.setOnClickListener {
            onClick?.onListItemClick(holder.itemView, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? = ViewHolder(LayoutInflater.from(context).inflate(R.layout.content_place, parent))

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.placeTitle
        val distance: TextView = itemView.placeDistance
    }

    companion object {
        interface OnListItemClick {
            fun onListItemClick(v: View, position: Int)
        }
    }
}
