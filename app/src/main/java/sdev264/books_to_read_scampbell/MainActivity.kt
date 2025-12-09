package sdev264.books_to_read_scampbell

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etBookTitle: EditText
    private lateinit var btnGoToSecondary: Button
    private lateinit var btnAddUnread: Button
    private lateinit var btnOpenPreferences: Button
    private lateinit var btnOpenHelp: Button
    private lateinit var btnOpenBookList: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBookTitle = findViewById(R.id.etBookTitle)
        btnGoToSecondary = findViewById(R.id.btnGoToSecondary)
        btnAddUnread = findViewById(R.id.btnAddUnread)
        btnOpenPreferences = findViewById(R.id.btnOpenPreferences)
        btnOpenHelp = findViewById(R.id.btnOpenHelp)
        btnOpenBookList = findViewById(R.id.btnOpenBookList)

        // Event: Mark as read → open SecondaryActivity and pass title
        btnGoToSecondary.setOnClickListener {
            val title = etBookTitle.text.toString().trim()
            if (title.isEmpty()) {
                Toast.makeText(this, "Please enter a book title.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SecondaryActivity::class.java)
                intent.putExtra("BOOK_TITLE", title)
                startActivity(intent)
            }
        }

        // Event: Add as unread → store in BookRepository only
        btnAddUnread.setOnClickListener {
            val title = etBookTitle.text.toString().trim()
            if (title.isEmpty()) {
                Toast.makeText(this, "Please enter a book title.", Toast.LENGTH_SHORT).show()
            } else {
                BookRepository.addBook(this, title, false)
                Toast.makeText(this, "Added \"$title\" as unread.", Toast.LENGTH_SHORT).show()
                etBookTitle.text.clear()
            }
        }

        // Event: Open PreferencesActivity
        btnOpenPreferences.setOnClickListener {
            val intent = Intent(this, PreferencesActivity::class.java)
            startActivity(intent)
        }

        // Event: Open HelpActivity
        btnOpenHelp.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }

        // Event: Open BookListActivity
        btnOpenBookList.setOnClickListener {
            val intent = Intent(this, BookListActivity::class.java)
            startActivity(intent)
        }
    }
}
