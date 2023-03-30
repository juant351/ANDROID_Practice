package com.example.fragmentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.fragmentexample.FirstFragment.Companion.ADDRESS_BUNDLE
import com.example.fragmentexample.FirstFragment.Companion.NAME_BUNDLE

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            val bundle = bundleOf(NAME_BUNDLE to "Torro",
                ADDRESS_BUNDLE to "RosaliaCastro")
            supportFragmentManager.commit {
                // Esto se pone siempre, para una cosa de "performance", lo pones y punto.
                setReorderingAllowed(true)
                add<FirstFragment>(R.id.fragmentContainer, args = bundle)
            }
        }
    }
}