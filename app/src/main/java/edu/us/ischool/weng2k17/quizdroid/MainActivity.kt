package edu.us.ischool.weng2k17.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val topics = ArrayList<Topic>()

        topics.add(Topic("Math", "Math is a topic that some find fun but many find NOT fun. If you're on this page, you must be someone who thinks math is fun (weirdo)"))
        topics.add(Topic("Physics", "Like math, physics is a topic only the select few can savor. Being this quiz at your own risk"))
        topics.add(Topic("Marvel Superheros", "Try to name Marvel Superheros! Fun!"))

        val adapter = CustomAdapter(topics)

        recyclerView.adapter = adapter
    }
}
