package com.lleans.myrecyclerview

import Hero
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.w3c.dom.Text

class CardAdapter(val listHeroes: ArrayList<Hero>) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_card_hero, viewGroup, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val (name, from, photo) = listHeroes[position]
        Glide.with(holder.itemView.context)
            .load(listHeroes[position].photo_hero)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)
        holder.hero_name.text = name
        holder.hero_desc.text = from

        holder.btnShare.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Bagikan " + listHeroes[holder.adapterPosition].name_hero,
                Toast.LENGTH_SHORT
            ).show()
        }

        holder.btnFavor.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Favoritkan " + listHeroes[holder.adapterPosition].name_hero,
                Toast.LENGTH_SHORT
            ).show()
        }

        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Kamu Memilih " + listHeroes[holder.adapterPosition].name_hero,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return listHeroes.size
    }

    inner class CardViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.hero_photo)
        var hero_name: TextView = itemView.findViewById(R.id.hero_name)
        var hero_desc: TextView = itemView.findViewById(R.id.hero_desc)
        var btnFavor: Button = itemView.findViewById(R.id.set_favorite)
        var btnShare: Button = itemView.findViewById(R.id.share)
    }
}