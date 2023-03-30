package com.iigr.recyclerviewexample.data

import com.iigr.recyclerviewexample.R
import com.iigr.recyclerviewexample.model.Publisher
import com.iigr.recyclerviewexample.model.SuperHero

/**
 * @author iigr
 * Created by Ivan González Rincón from GMV on 20/09/2022
 */
object SuperHeroProvider {

    fun getSuperHeroes(): List<SuperHero> {
        return listOf(
            SuperHero("Spiderman", "Peter Parker", Publisher.Marvel, R.drawable.spiderman),
            SuperHero("Wolverine", "James Howlett", Publisher.Marvel, R.drawable.logan),
            SuperHero("Batman", "Bruce Wayne", Publisher.Dc, R.drawable.batman),
            SuperHero("Thor", "Thor Odinson", Publisher.Marvel, R.drawable.thor),
            SuperHero("Flash", "Barry Allen", Publisher.Dc, R.drawable.flash),
            SuperHero("Green Lantern", "Hal Jordan", Publisher.Dc, R.drawable.green_lantern),
            SuperHero("Wonder Woman", "Princess Diana", Publisher.Dc, R.drawable.wonder_woman),
            SuperHero("Spawn", "Al Simmons", Publisher.Image, R.drawable.spawn)
        )
    }

}