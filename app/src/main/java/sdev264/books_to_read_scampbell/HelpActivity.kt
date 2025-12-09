package sdev264.books_to_read_scampbell   // make sure this matches your package

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        // Button to return to MainActivity
        val btnBackToMain: Button = findViewById(R.id.btnBackToMain)

        btnBackToMain.setOnClickListener {
            // Close HelpActivity and go back to the MainActivity that opened it
            finish()
        }
    }
}
