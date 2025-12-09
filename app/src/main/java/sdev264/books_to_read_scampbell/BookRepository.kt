package sdev264.books_to_read_scampbell

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

data class BookEntry(
    val title: String,
    val isRead: Boolean
)

object BookRepository {

    private const val PREFS_NAME = "books_storage"
    private const val KEY_BOOKS_JSON = "books_json"

    private val books = mutableListOf<BookEntry>()
    private var isLoaded = false

    fun loadFromStorage(context: Context) {
        if (isLoaded) return

        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val jsonString = prefs.getString(KEY_BOOKS_JSON, null) ?: return

        val array = JSONArray(jsonString)
        books.clear()
        for (i in 0 until array.length()) {
            val obj = array.getJSONObject(i)
            val title = obj.getString("title")
            val isRead = obj.getBoolean("isRead")
            books.add(BookEntry(title, isRead))
        }
        isLoaded = true
    }

    fun saveToStorage(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val array = JSONArray()
        for (book in books) {
            val obj = JSONObject()
            obj.put("title", book.title)
            obj.put("isRead", book.isRead)
            array.put(obj)
        }
        prefs.edit().putString(KEY_BOOKS_JSON, array.toString()).apply()
    }

    fun addBook(context: Context, title: String, isRead: Boolean) {
        loadFromStorage(context)
        books.add(BookEntry(title, isRead))
        saveToStorage(context)
    }

    fun getAllBooks(context: Context): List<BookEntry> {
        loadFromStorage(context)
        return books.toList()
    }
}
