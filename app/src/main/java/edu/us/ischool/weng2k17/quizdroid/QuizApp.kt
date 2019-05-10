package edu.us.ischool.weng2k17.quizdroid

import android.app.Application
import android.content.Context
import android.util.Log
import okhttp3.*
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

        fun newInstance() {
            app = QuizApp()
        }
    }

    interface TopicRepository {
        fun getTopic(topic: String): Topic
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "QuizApp is being loaded and run!!!")
        readJSON()
        val json = getJSONFromWeb()
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

        /*Log.i(TAG, jsonString)*/
        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            assignTopicData(jsonArray, i)
        }
        /*Log.i(TAG, topicMap.toString())*/

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

    // http://students.washington.edu/weng2k17/info448-data/data.json
    fun getJSONFromWeb(): String {
        val url = "http://students.washington.edu/weng2k17/info448-data/data.php"

        val request = Request.Builder().url(url).build()
        var json = ""

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val resBody = response.body()?.string()
                json = resBody!!
                Log.i(TAG, resBody)
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, e.toString())
            }
        })



        return json
    }

}