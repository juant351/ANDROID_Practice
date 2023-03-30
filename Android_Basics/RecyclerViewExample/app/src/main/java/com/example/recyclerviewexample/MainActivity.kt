package com.example.recyclerviewexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewexample.adapter.SuperHeroAdapter
import com.example.recyclerviewexample.data.SuperHeroProvider
import com.example.recyclerviewexample.databinding.ActivityMainBinding
import com.example.recyclerviewexample.model.SuperHero

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        binding.recyclerSuperHero.layoutManager = manager
        binding.recyclerSuperHero.adapter =
            SuperHeroAdapter(SuperHeroProvider.superHeroList) { superHero ->
                onItemSelected(
                    superHero
                )
            }

        // Lo mismo pero sin binding (forma "tradicional")

//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerSuperHero)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = SuperHeroAdapter(SuperHeroProvider.superHeroList)

    }

    private fun onItemSelected(superHero: SuperHero) {
        Toast.makeText(this, superHero.superhero, Toast.LENGTH_SHORT).show()
    }
}