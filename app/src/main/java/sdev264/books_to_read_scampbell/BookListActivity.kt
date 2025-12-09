package sdev264.books_to_read_scampbell

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class BookListActivity : AppCompatActivity() {

    private lateinit var lvBooks: ListView
    private lateinit var btnBackFromList: Button

    private val PREFS_NAME = "books_prefs"
    private val KEY_SORT = "sort_option"
    private val KEY_SHOW_FINISHED = "show_finished"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        lvBooks = findViewById(R.id.lvBooks)
        btnBackFromList = findViewById(R.id.btnBackFromList)

        // Load preferences for sorting/filtering
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val sort = prefs.getString(KEY_SORT, "alpha")
        val showFinished = prefs.getBoolean(KEY_SHOW_FINISHED, true)

        // Load all books from persistent repository
        var books = BookRepository.getAllBooks(this)

        // Apply "show finished" preference
        if (!showFinished) {
            books = books.filter { !it.isRead }
        }

        // Apply sort preference
        books = if (sort == "status") {
            // Unread first (isRead == false), then read; each group alphabetical
            books.sortedWith(
                compareBy<BookEntry> { it.isRead }   // false before true
                    .thenBy { it.title.lowercase() }
            )
        } else {
            // Alphabetical by title
            books.sortedBy { it.title.lowercase() }
        }

        val items = books.map { book ->
            val status = if (book.isRead) "Read" else "Unread"
            "${book.title} - $status"
        }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            items
        )
        lvBooks.adapter = adapter

        btnBackFromList.setOnClickListener {
            finish()
        }
    }
}
