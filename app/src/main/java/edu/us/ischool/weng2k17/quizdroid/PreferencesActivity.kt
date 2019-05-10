package edu.us.ischool.weng2k17.quizdroid

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_preferences.*

class PreferencesActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    companion object {

        private const val USER_PREFERENCES_KEY = "USER_PREFERENCES"
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
    }

    fun readWriteJSON() {
/*
        sharedPreferences = getSharedPreferences(USER_PREFERENCES_KEY, Context.MODE_PRIVATE)

        sharedPreferences
            .edit()
            .put*/

    }

}
