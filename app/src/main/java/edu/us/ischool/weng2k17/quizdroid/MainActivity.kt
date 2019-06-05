package edu.us.ischool.weng2k17.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import edu.us.ischool.weng2k17.quizdroid.QuizApp.Companion.app

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        QuizApp.newInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val topics = QuizApp.accessTopicRepo()
        val topicList = ArrayList<Topic>()
        val mapKeys = topics.keys
        topicList.apply {
            for (key in mapKeys) {
                add(topics[key]!!)
            }
        }

        val adapter = CustomAdapter(topicList)

        recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.preferences -> {
                val intent = Intent(this, PreferencesActivity::class.java)
                this.startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
