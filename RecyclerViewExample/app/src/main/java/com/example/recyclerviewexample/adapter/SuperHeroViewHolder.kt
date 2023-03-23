package com.example.recyclerviewexample.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.recyclerviewexample.R
import com.example.recyclerviewexample.databinding.ItemSuperheroBinding
import com.example.recyclerviewexample.model.SuperHero

class SuperHeroViewHolder(view: View) : ViewHolder(view) {
    private val binding = ItemSuperheroBinding.bind(view)

    /*
    val superHero = view.findViewById<TextView>(R.id.textSuperHeroName)
    val realName = view.findViewById<TextView>(R.id.textSuperHeroRealName)
    val  publisher = view.findViewById<TextView>(R.id.textSuperHeroPublisher)
    val photo = view.findViewById<ImageView>(R.id.imgSuperHero)
*/
    fun render(superHeroModel: SuperHero, onCLickListener:(SuperHero) -> Unit) {
        binding.textSuperHeroName.text = superHeroModel.superhero
        binding.textSuperHeroRealName.text = superHeroModel.realName
        binding.textSuperHeroPublisher.text = superHeroModel.publisher
        if (superHeroModel.photo != null)
            Glide.with(binding.imgSuperHero.context).load(superHeroModel.photo)
            .into(binding.imgSuperHero)
        else binding.imgSuperHero.setImageResource(R.drawable.choscar)


        itemView.setOnClickListener { onCLickListener(superHeroModel) }
    }
}