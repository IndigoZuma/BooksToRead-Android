package sdev264.books_to_read_scampbell

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondaryActivity : AppCompatActivity() {

    private lateinit var tvBookTitle: TextView
    private lateinit var tvStatus: TextView
    private lateinit var btnBackFromSecondary: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        tvBookTitle = findViewById(R.id.tvBookTitle)
        tvStatus = findViewById(R.id.tvStatus)
        btnBackFromSecondary = findViewById(R.id.btnBackFromSecondary)

        val titleFromIntent = intent.getStringExtra("BOOK_TITLE")

        if (!titleFromIntent.isNullOrEmpty()) {
            tvBookTitle.text = titleFromIntent
            tvStatus.text = "You marked \"$titleFromIntent\" as read."

            // Add this book as READ and persist it
            BookRepository.addBook(this, titleFromIntent, true)
        } else {
            tvBookTitle.text = "No book title received."
            tvStatus.text = "Return to the main screen and enter a book."
        }

        btnBackFromSecondary.setOnClickListener {
            finish()
        }
    }
}
