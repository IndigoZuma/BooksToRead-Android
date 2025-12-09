package sdev264.books_to_read_scampbell   // keep this exactly as your package

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PreferencesActivity : AppCompatActivity() {

    private lateinit var rgSortOptions: RadioGroup
    private lateinit var rbSortAlpha: RadioButton
    private lateinit var rbSortStatus: RadioButton
    private lateinit var cbShowFinished: CheckBox
    private lateinit var btnSavePrefs: Button

    private val PREFS_NAME = "books_prefs"
    private val KEY_SORT = "sort_option"
    private val KEY_SHOW_FINISHED = "show_finished"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        rgSortOptions = findViewById(R.id.rgSortOptions)
        rbSortAlpha = findViewById(R.id.rbSortAlpha)
        rbSortStatus = findViewById(R.id.rbSortStatus)
        cbShowFinished = findViewById(R.id.cbShowFinished)
        btnSavePrefs = findViewById(R.id.btnSavePrefs)

        // Load saved values or defaults when the screen opens
        loadPreferences()

        // Save current choices when user taps the button
        btnSavePrefs.setOnClickListener {
            savePreferences()
            Toast.makeText(this, "Preferences saved.", Toast.LENGTH_SHORT).show()
            finish()   // return to MainActivity
        }
    }

    private fun loadPreferences() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Defaults: sort alphabetically and show finished books
        val sort = prefs.getString(KEY_SORT, "alpha")
        val showFinished = prefs.getBoolean(KEY_SHOW_FINISHED, true)

        if (sort == "alpha") {
            rbSortAlpha.isChecked = true
        } else {
            rbSortStatus.isChecked = true
        }

        cbShowFinished.isChecked = showFinished
    }

    private fun savePreferences() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()

        val sortValue = if (rbSortAlpha.isChecked) "alpha" else "status"
        editor.putString(KEY_SORT, sortValue)
        editor.putBoolean(KEY_SHOW_FINISHED, cbShowFinished.isChecked)

        editor.apply()  // commit asynchronously
    }
}
