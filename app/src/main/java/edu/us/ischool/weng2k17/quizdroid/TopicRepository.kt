package edu.us.ischool.weng2k17.quizdroid

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import edu.us.ischool.weng2k17.quizdroid.QuizApp.Companion.app
import org.json.JSONObject
import java.io.IOException

class TopicRepository() : QuizApp.TopicRepository {
    var topicMap: MutableMap<String, Topic> = mutableMapOf()

    private val TAG = "TopicRepository"

    override fun getTopic(topic: String): Topic {
        return topicMap[topic] as Topic
    }

    init {
        //initializeData()
    }

    /*
    private fun initializeData() {
        // tests and questions
        // math test
        val q1 = Quiz("What is 1 + 1?", ArrayList<String>(), 1)
        q1.choices.add("2")
        q1.choices.add("4")
        q1.choices.add("5")
        q1.choices.add("6")

        val q2 = Quiz("What is 1 + 2?", ArrayList<String>(), 2)
        q2.choices.add("2")
        q2.choices.add("3")
        q2.choices.add("5")
        q2.choices.add("6")

        val q3 = Quiz("What is 1 + 4?", ArrayList<String>(), 3)
        q3.choices.add("2")
        q3.choices.add("3")
        q3.choices.add("5")
        q3.choices.add("6")

        val q4 = Quiz("What is 1 + 5?", ArrayList<String>(), 4)
        q4.choices.add("2")
        q4.choices.add("3")
        q4.choices.add("5")
        q4.choices.add("6")

        val mathTest = Topic("Math", "Numbers and equations", "Math is a topic that some find fun but many find NOT fun. If you're on this page, you must be someone who thinks math is fun (weirdo)", ArrayList<Quiz>())
        mathTest.quizzes.apply {
            add(q1)
            add(q2)
            add(q3)
            add(q4)
        }

        // physics test
        val q1Physics = Quiz("What is the equation for Force?", ArrayList<String>(), 3)
        q1Physics.choices.add("K=1/2mv^2")
        q1Physics.choices.add("none of these")
        q1Physics.choices.add("F=ma")
        q1Physics.choices.add("a=Fm")

        val q2Physics = Quiz("What is the equation for acceleration?", ArrayList<String>(), 4)
        q2Physics.choices.add("K=1/2mv^2")
        q2Physics.choices.add("none of these")
        q2Physics.choices.add("F=ma")
        q2Physics.choices.add("a=F/m")

        val q3Physics = Quiz("What is the equation for mass?", ArrayList<String>(), 4)
        q3Physics.choices.add("K=1/2mv^2")
        q3Physics.choices.add("none of these")
        q3Physics.choices.add("F=ma")
        q3Physics.choices.add("m=F/a")

        val q4Physics = Quiz("What is the equation for Kinetic Energy?", ArrayList<String>(), 1)
        q4Physics.choices.add("K=1/2mv^2")
        q4Physics.choices.add("none of these")
        q4Physics.choices.add("F=ma")
        q4Physics.choices.add("m=F/a")

        val physicsTest = Topic("Physics", "Forces, Kinematics, etc", "No one really likes Physics except the people takng this test", ArrayList<Quiz>())
        physicsTest.quizzes.apply {
            add(q1Physics)
            add(q2Physics)
            add(q3Physics)
            add(q4Physics)
        }

        // marvel heros
        val q1Marvel = Quiz("What actor plays Captain Marvel?", ArrayList<String>(), 1)
        q1Marvel.choices.add("Brie Larson")
        q1Marvel.choices.add("Chris Hemsworth")
        q1Marvel.choices.add("Chris Evans")
        q1Marvel.choices.add("Tom Holland")

        val q2Marvel = Quiz("What actor plays Spiderman?", ArrayList<String>(), 4)
        q2Marvel.choices.add("Brie Larson")
        q2Marvel.choices.add("Chris Hemsworth")
        q2Marvel.choices.add("Chris Evans")
        q2Marvel.choices.add("Tom Holland")

        val q3Marvel = Quiz("What actor plays Captain America?", ArrayList<String>(), 3)
        q3Marvel.choices.add("Brie Larson")
        q3Marvel.choices.add("Chris Hemsworth")
        q3Marvel.choices.add("Chris Evans")
        q3Marvel.choices.add("Tom Holland")

        val q4Marvel = Quiz("What actor plays Thor?", ArrayList<String>(), 2)
        q4Marvel.choices.add("Brie Larson")
        q4Marvel.choices.add("Chris Hemsworth")
        q4Marvel.choices.add("Chris Evans")
        q4Marvel.choices.add("Tom Holland")

        val marvelHerosTest = Topic("Marvel Superheros", "Superhero names and actors", "Yay! A fun topic about the coolest heros in the MCU", ArrayList<Quiz>())
        marvelHerosTest.quizzes.apply {
            add(q1Marvel)
            add(q2Marvel)
            add(q3Marvel)
            add(q4Marvel)
        }

        topicMap[mathTest.title] = mathTest
        topicMap[marvelHerosTest.title] = marvelHerosTest
        topicMap[physicsTest.title] = physicsTest

    }
    */

    fun readJSON() {

        val jsonString: String? = try {

            val inputStream = assets.open("questions.json")
//            val inputStream = assets
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            null
        }

        Log.i(TAG, jsonString)

        /*
        jsonString?.let {

            Log.i(TAG, jsonString)

            // Create json from string
            val jsonObject = JSONObject(jsonString)

            // Get JSON array
            val employeesJSONArray = jsonObject.getJSONArray(EMPLOYEES_KEY)

            // Read JSON array
            for (i in 0 until employeesJSONArray.length()) {
                // get data of array value at index
                val employeeJSONObject = employeesJSONArray.get(i) as JSONObject

                // Get data value of key
                val firstName = employeeJSONObject.get(FIRST_NAME)
                val lastName = employeeJSONObject.get(LAST_NAME)

                Log.i(TAG, "Hello my name is $firstName $lastName")
            }
        }
        */

    }
}