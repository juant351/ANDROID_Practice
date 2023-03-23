package com.iigr.recyclerviewexample.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iigr.recyclerviewexample.R
import com.iigr.recyclerviewexample.model.SuperHero

/**
 * @author iigr
 * Created by Ivan González Rincón from GMV on 20/09/2022
 */
class SuperheroAdapter(
    private val superHeroList: List<SuperHero>,
    private val onSuperHeroClick: (SuperHero) -> Unit,
    private val onDeleteSuperHero: (Int) -> Unit,
    private val onEditSuperHero: (Int) -> Unit,
) : RecyclerView.Adapter<SuperHeroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(
            layoutInflater.inflate(
                R.layout.view_holder_superhero,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superHeroList[position]
        holder.render(item, onSuperHeroClick, onDeleteSuperHero, onEditSuperHero)
    }

    override fun getItemCount(): Int = superHeroList.size
}