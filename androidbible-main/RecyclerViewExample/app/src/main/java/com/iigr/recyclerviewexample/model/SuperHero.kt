package com.iigr.recyclerviewexample.model

import android.graphics.Color
import androidx.annotation.DrawableRes

/**
 * @author iigr
 * Created by Ivan González Rincón from GMV on 20/09/2022
 */
data class SuperHero(
    var name: String,
    val realName: String,
    val publisher: Publisher,
    @DrawableRes val photo: Int,
)

sealed class Publisher(
    val publisherName: String
) {
    object Marvel : Publisher("Marvel")
    object Dc : Publisher("DC")
    object Image : Publisher("Image comics")
}

fun Publisher.publisherColor() =
    when (this) {
        Publisher.Marvel -> Color.RED
        Publisher.Dc -> Color.BLUE
        Publisher.Image -> Color.GREEN
    }

