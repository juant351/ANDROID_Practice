package com.example.recyclerviewexample.data

import android.content.Context
import android.net.Uri
import com.example.recyclerviewexample.R
import com.example.recyclerviewexample.model.SuperHero

class SuperHeroProvider {
    companion object {
        val superHeroList = listOf<SuperHero>(
            SuperHero(
                "KotlinMan",
                "Jetbrains",
                "Aristidevs",
                "https://cursokotlin.com/wp-content/uploads/2020/09/Webp.net-compress-image.jpg"
            ),
            SuperHero(
                "TorroPai",
                "Jetbrains",
                "Juan",
                "https://cursokotlin.com/wp-content/uploads/2020/09/Webp.net-compress-image.jpg"
            ),
            SuperHero(
                "Choscar",
                "Jetbrains",
                "Oscar",
                photo2 = R.drawable.choscar
            ),
            SuperHero(
                "Batman",
                "DC",
                "Bruce Wayne",
                "https://cursokotlin.com/wp-content/uploads/2020/09/Webp.net-compress-image.jpg"
            ),
            SuperHero(
                "Superman",
                "DC",
                "Clar Kent",
                "https://cursokotlin.com/wp-content/uploads/2020/09/Webp.net-compress-image.jpg"
            ),
            SuperHero(
                "Spiderman",
                "Marvel",
                "Peter Parker",
                "https://cursokotlin.com/wp-content/uploads/2020/09/Webp.net-compress-image.jpg"
            ),
        )
    }
}