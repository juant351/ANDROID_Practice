package com.example.recyclerviewexample.adapter

import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.R
import com.example.recyclerviewexample.data.SuperHeroProvider.Companion.superHeroList
import com.example.recyclerviewexample.model.SuperHero

class SuperHeroAdapter(private val superHeroList:List<SuperHero>, private val onClickListener:(SuperHero) -> Unit) : RecyclerView.Adapter<SuperHeroViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(adapterLayout.inflate(R.layout.item_superhero, parent, false))
    }

    override fun getItemCount(): Int = superHeroList.size

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superHeroList[position]
        holder.render(item, onClickListener)
    }
}