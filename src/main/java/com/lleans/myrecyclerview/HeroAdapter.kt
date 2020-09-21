package com.lleans.myrecyclerview

import Hero
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class HeroAdapter(val listHero: ArrayList<Hero>) :
    RecyclerView.Adapter<HeroAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    
    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_hero, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, from, photo) = listHero[position]

        Glide.with(holder.itemView.context)
            .load(photo)
            .apply(RequestOptions().override(55, 55))
            .into(holder.imgPhoto)

        holder.tvName.text = name
        holder.tvFrom.text = from
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listHero[holder.adapterPosition])
        }
    }
    
    interface OnItemClickCallback{
        fun onItemClicked(data: Hero)
    }

    override fun getItemCount(): Int {
        return listHero.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.item_name)
        var tvFrom: TextView = itemView.findViewById(R.id.item_born)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item)
    }
}