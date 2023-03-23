package com.iigr.recyclerviewexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.iigr.recyclerviewexample.R
import com.iigr.recyclerviewexample.data.SuperHeroProvider
import com.iigr.recyclerviewexample.databinding.ActivityMainBinding
import com.iigr.recyclerviewexample.model.Publisher
import com.iigr.recyclerviewexample.model.SuperHero
import com.iigr.recyclerviewexample.ui.recyclerview.SuperheroAdapter

/**
 * @author iigr
 * Created by Ivan González Rincón from GMV on 20/09/2022
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var superheroMutableList: MutableList<SuperHero> =
        SuperHeroProvider.getSuperHeroes().toMutableList()
    private lateinit var superheroAdapter: SuperheroAdapter

    // To build an horizontal list, the second param should be LinearLayoutManager.HORIZONTAL
    private val linearLayoutManager =
        LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        setupButton()
    }

    private fun setupButton() {
        with(binding) {
            addSuperhero.setOnClickListener {
                createSuperhero()
            }
        }

    }

    private fun createSuperhero() {
        val newSuperhero = SuperHero(
            name = "Murder Falcon",
            realName = "??????",
            publisher = Publisher.Image,
            photo = R.drawable.murf
        )

        superheroMutableList.add(newSuperhero)
        superheroAdapter.notifyItemInserted(superheroMutableList.size - 1)
        linearLayoutManager.scrollToPositionWithOffset(superheroMutableList.size - 1, 20)
    }

    private fun setupRecyclerView() {
        // Used to add divider item between list items
        val decoration = DividerItemDecoration(this, linearLayoutManager.orientation)

        superheroAdapter = SuperheroAdapter(
            superHeroList = superheroMutableList,
            onSuperHeroClick = { onSuperHeroClickHandler(it) },
            onDeleteSuperHero = { onDeleteSuperHeroHandler(it) },
            onEditSuperHero = { onEditSuperHeroHandler(it) }
        )

        with(binding) {
            with(superheroList) {
                layoutManager = linearLayoutManager
                adapter = superheroAdapter
                addItemDecoration(decoration)
            }
        }
    }

    private fun onSuperHeroClickHandler(superHero: SuperHero) {
        Toast.makeText(this@MainActivity, superHero.name, Toast.LENGTH_SHORT).show()
    }

    private fun onDeleteSuperHeroHandler(position: Int) {
        superheroMutableList.removeAt(position)
        superheroAdapter.notifyItemRemoved(position)
    }

    private fun onEditSuperHeroHandler(position: Int) {
        val editedSuperhero = superheroMutableList[position]
        editedSuperhero.name = "New name"
        superheroAdapter.notifyItemChanged(position)
    }

}