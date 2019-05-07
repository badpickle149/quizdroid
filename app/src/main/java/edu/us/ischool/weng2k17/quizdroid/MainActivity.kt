package edu.us.ischool.weng2k17.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val topics = QuizApp.accessTopicRepo()
        val topicList = ArrayList<Topic>()
        topicList.apply {
            add(topics["Math"]!!)
            add(topics["Physics"]!!)
            add(topics["Marvel Superheros"]!!)
        }


        val adapter = CustomAdapter(topicList)

        recyclerView.adapter = adapter

    }

}
