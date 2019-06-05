package edu.us.ischool.weng2k17.quizdroid

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_preferences.*

class PreferencesActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    companion object {

        const val URL_KEY = "url"
        const val UPDATE_INTERVAL_KEY = "update_interval"

        private const val USER_PREFERENCES_KEY = "USER_PREFERENCES_KEY"
        private const val TIMESTAMP_KEY = "timestamp"


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sharedPreferences = getSharedPreferences(USER_PREFERENCES_KEY, Context.MODE_PRIVATE)

        val saveURLBtn = findViewById<Button>(R.id.saveURLBtn)
        saveURLBtn.setOnClickListener {
            val newURL = findViewById<EditText>(R.id.downloadURL)

            sharedPreferences
                .edit()
                .putString(URL_KEY, newURL.text.toString())
                .apply()

            val toast = Toast.makeText(this, "${sharedPreferences.getString(URL_KEY, "no key found")}", Toast.LENGTH_LONG).show()
        }

        val updateIntervalBtn = findViewById<Button>(R.id.saveUpdateIntervalBtn)
        updateIntervalBtn.setOnClickListener {
            val newUpdateInterval = findViewById<EditText>(R.id.minuteSetting).text.toString().toInt()

            sharedPreferences
                .edit()
                .putInt(UPDATE_INTERVAL_KEY, newUpdateInterval)
                .apply()

            val toast = Toast.makeText(this, "${sharedPreferences.getInt(UPDATE_INTERVAL_KEY, -1)}", Toast.LENGTH_LONG).show()
        }
    }

}
