package edu.us.ischool.weng2k17.quizdroid

import android.app.Application
import android.util.Log

class QuizApp: Application() {
    private val TAG = "QuizApp"

    companion object {
        val topicMap = edu.us.ischool.weng2k17.quizdroid.TopicRepository().topicMap
        lateinit var app: QuizApp

        fun accessTopicRepo(): MutableMap<String, Topic> {
            return topicMap
        }
    }

    interface TopicRepository {
        fun getTopic(topic: String): Topic
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "QuizApp is being loaded and run!!!")
    }

}