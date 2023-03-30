package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cuando el boton es pulsado, se calcula la propina
        binding.calculateButton.setOnClickListener { calculateTip() }

        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }
    }

    /**
     * Realizar el calculo de la propina una vez se pulsa el botón
     */
    private fun calculateTip(){
        val cost = binding.costOfServiceEditText.text.toString().toDoubleOrNull()
        if(cost == null){
            binding.tipResult.text = ""
            binding.totalCost.text = ""
            return
        }

        /* Otra manera de implementarlo, más explicada =
        * val stringInTextField = binding.costOfService.text.toString()
        * val cost = stringInTextField.toDouble()
        */

        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentage
        // Comprobar si la opcion redondeo está marcada
        if(binding.roundUpSwitch.isChecked) tip = kotlin.math.ceil(tip)

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

        val total = cost + tip
        val formattedTotal = NumberFormat.getCurrencyInstance().format(total)

        binding.totalCost.text = getString(R.string.total_cost, formattedTotal)

    }

    /**
     * A private helper function that hides the onscreen keyboard if the keyCode input parameter is equal to KeyEvent.KEYCODE_ENTER.
     * The InputMethodManager controls if a soft keyboard is shown, hidden, and allows the user to choose which soft keyboard is displayed.
     *
     * The method returns true if the key event was handled, and returns false otherwise.
     */
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}