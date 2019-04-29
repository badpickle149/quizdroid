package edu.us.ischool.weng2k17.quizdroid

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import org.w3c.dom.Text

class TopicOverviewActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topic_overview)
        val topicName = intent.getStringExtra("TopicName")
        val description = intent.getStringExtra("Description")

        val title = findViewById<TextView>(R.id.overviewTopicName)
        title.text = topicName

        val desc = findViewById<TextView>(R.id.overviewDesc)
        desc.text = description
    }
}