package edu.us.ischool.weng2k17.quizdroid

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.card_layout.view.*

class CustomAdapter(val topicList: ArrayList<Topic>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.card_layout, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return topicList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val topic: Topic = topicList[p1]
        p0.cardTopic.text = topic.name
        p0.goToOverviewBtn.setOnClickListener {
            //cardTopic.setText("Clicked on this button")
            val intent = Intent(p0.view.context, TopicOverviewActivity::class.java)
            intent.putExtra("TopicName", topic.name)
            intent.putExtra("Description", topic.overviewDesc)
            p0.view.context.startActivity(intent)
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardTopic = itemView.findViewById<TextView>(R.id.cardTitle)
        val goToOverviewBtn = itemView.findViewById<Button>(R.id.overviewButton)
        val view = itemView
    }
}