package com.iigr.recyclerviewexample.ui.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.iigr.recyclerviewexample.databinding.ViewHolderSuperheroBinding
import com.iigr.recyclerviewexample.model.SuperHero
import com.iigr.recyclerviewexample.model.publisherColor

/**
 * @author iigr
 * Created by Ivan González Rincón from GMV on 20/09/2022
 */
class SuperHeroViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val binding = ViewHolderSuperheroBinding.bind(view)

    fun render(
        superHero: SuperHero,
        onSuperHeroClick: (SuperHero) -> Unit,
        onDeleteSuperHero: (Int) -> Unit,
        onEditSuperHero: (Int) -> Unit
    ) {
        with(binding) {
            superheroContainer.setOnClickListener { onSuperHeroClick(superHero) }
            deleteSuperhero.setOnClickListener { onDeleteSuperHero(adapterPosition) }
            editSuperhero.setOnClickListener { onEditSuperHero(adapterPosition) }

            name.text = superHero.name
            realName.text = superHero.realName
            publisher.text = superHero.publisher.publisherName
            publisher.setTextColor(superHero.publisher.publisherColor())
            avatar.setImageResource(superHero.photo)
        }
    }

}