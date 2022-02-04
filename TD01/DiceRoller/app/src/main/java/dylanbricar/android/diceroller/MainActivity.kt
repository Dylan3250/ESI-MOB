package dylanbricar.android.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }

        val countUpDice: Button = findViewById(R.id.count_up_button)
        countUpDice.setOnClickListener { countUpDice() }
    }

    private fun rollDice() {
        // Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()
        val resultText: TextView = findViewById(R.id.result_text)
        val randomInt = (1..6).random()
        resultText.text = randomInt.toString()
    }

    private fun countUpDice() {
        val resultText: TextView = findViewById(R.id.result_text)
        if (resultText.text == "Hello World!") {
            resultText.text = (1).toString()
        } else if (resultText.text != "6") {
            resultText.text = (resultText.text.toString().toInt() + 1).toString()
        }
    }
}