package edu.us.ischool.weng2k17.quizdroid

import android.app.Application
import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONStringer
import java.io.IOException

class QuizApp: Application() {
    private val TAG = "QuizApp"

    companion object {
        var topicMap = edu.us.ischool.weng2k17.quizdroid.TopicRepository().topicMap
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
        readJSON()
    }

    fun readJSON() {

        val jsonString: String? = try {

            val inputStream = assets.open("questions.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            null
        }

        Log.i(TAG, jsonString)
        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            assignTopicData(jsonArray, i)
        }
        Log.i(TAG, topicMap.toString())

    }

    fun assignTopicData(jsonArray: JSONArray, index: Int) {
        val topic = JSONObject(jsonArray[index].toString())
        val topicTitle = topic["title"].toString()
        //Log.i(TAG, scienceTitle)
        val Topic = Topic(topicTitle, topic["desc"].toString(), topic["desc"].toString(), ArrayList<Quiz>())
        val topicQuestions = JSONArray(topic["questions"].toString())
        //Log.i(TAG, scienceQuestions.toString())
        for (i in 0 until topicQuestions.length()) {
            val question = JSONObject(topicQuestions[i].toString())
            val questionChoices = JSONArray(question["answers"].toString())
            val quiz = Quiz(question["text"].toString(), ArrayList(), question["answer"].toString().toInt())
            for (j in 0 until questionChoices.length()) {
                quiz.choices.add(questionChoices[j].toString())
            }
            Topic.quizzes.add(quiz)
        }
        topicMap[topicTitle] = Topic
    }

}