package com.example.recyclerviewexample.model

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

data class SuperHero(
    val superhero: String,
    val publisher: String,
    val realName: String,
    val photo: String? = null,
    @DrawableRes val photo2: Int? = null
)