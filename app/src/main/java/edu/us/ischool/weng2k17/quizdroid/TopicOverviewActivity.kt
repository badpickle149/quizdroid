package edu.us.ischool.weng2k17.quizdroid

import android.content.Intent
import android.os.Bundle
import android.os.Debug
import android.os.PersistableBundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*
import org.w3c.dom.Text

class TopicOverviewActivity: AppCompatActivity() {

    private  val TAG = "TopicOverviewActivity"
    private val testMap = mutableMapOf<String, Test>()
    private val correct = 0
    private var currQuestionNum = 0
    private var topicName = ""
    private val isFinished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topic_overview)

        assignData()
        topicName = intent.getStringExtra("TopicName")
        val description = intent.getStringExtra("Description")

        val overviewContainer = findViewById<LinearLayout>(R.id.overviewContainer)

        val overviewTitle = findViewById<TextView>(R.id.overviewTopicName)
        overviewTitle.text = topicName

        val overviewDesc = findViewById<TextView>(R.id.overviewDesc)
        overviewDesc.text = description

        val questionNumber = findViewById<TextView>(R.id.overviewQuestionNum)
        val test = testMap[topicName] as Test
        questionNumber.text = test.questions.size.toString()

        val beginBtn = findViewById<Button>(R.id.beginButton)
        beginBtn.setOnClickListener {
            val fragment: SingleQuestionFragment = SingleQuestionFragment.newInstance(testMap[topicName]!!.questions[currQuestionNum])

            // add fragment to View
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragmentContainer, fragment)
            ft.commit()

            overviewContainer.visibility = View.GONE
        }
    }

    private fun assignData() {
        // tests and questions
        val mathTest = ArrayList<Question>()
        val q1 = Question("What is 1 + 1?", ArrayList<String>(), "2")
        q1.choices.add("2")
        q1.choices.add("4")
        q1.choices.add("5")
        q1.choices.add("6")

        val q2 = Question("What is 1 + 2?", ArrayList<String>(), "3")
        q2.choices.add("2")
        q2.choices.add("3")
        q2.choices.add("5")
        q2.choices.add("6")

        val q3 = Question("What is 1 + 4?", ArrayList<String>(), "5")
        q3.choices.add("2")
        q3.choices.add("3")
        q3.choices.add("5")
        q3.choices.add("6")

        val q4 = Question("What is 1 + 5?", ArrayList<String>(), "6")
        q4.choices.add("2")
        q4.choices.add("3")
        q4.choices.add("5")
        q4.choices.add("6")

        mathTest.add(q1)
        mathTest.add(q2)
        mathTest.add(q3)
        mathTest.add(q4)

        val physicsTest = ArrayList<Question>()
        val q1Physics = Question("What is the equation for Force?", ArrayList<String>(), "F=ma")
        q1Physics.choices.add("K=1/2mv^2")
        q1Physics.choices.add("none of these")
        q1Physics.choices.add("F=ma")
        q1Physics.choices.add("a=Fm")

        val q2Physics = Question("What is the equation for acceleration?", ArrayList<String>(), "a=F/m")
        q2Physics.choices.add("K=1/2mv^2")
        q2Physics.choices.add("none of these")
        q2Physics.choices.add("F=ma")
        q2Physics.choices.add("a=F/m")

        val q3Physics = Question("What is the equation for mass?", ArrayList<String>(), "m=F/a")
        q3Physics.choices.add("K=1/2mv^2")
        q3Physics.choices.add("none of these")
        q3Physics.choices.add("F=ma")
        q3Physics.choices.add("m=F/a")

        val q4Physics = Question("What is the equation for Kinetic Energy?", ArrayList<String>(), "K=1/2mv^2")
        q4Physics.choices.add("K=1/2mv^2")
        q4Physics.choices.add("none of these")
        q4Physics.choices.add("F=ma")
        q4Physics.choices.add("m=F/a")

        physicsTest.add(q1Physics)
        physicsTest.add(q2Physics)
        physicsTest.add(q3Physics)
        physicsTest.add(q4Physics)

        val marvelHerosTest = ArrayList<Question>()
        val q1Marvel = Question("What actor plays Captain Marvel?", ArrayList<String>(), "Brie Larson")
        q1Marvel.choices.add("Brie Larson")
        q1Marvel.choices.add("Chris Hemsworth")
        q1Marvel.choices.add("Chris Evans")
        q1Marvel.choices.add("Tom Holland")

        val q2Marvel = Question("What actor plays Spiderman?", ArrayList<String>(), "Tom Holland")
        q2Marvel.choices.add("Brie Larson")
        q2Marvel.choices.add("Chris Hemsworth")
        q2Marvel.choices.add("Chris Evans")
        q2Marvel.choices.add("Tom Holland")

        val q3Marvel = Question("What actor plays Captain America?", ArrayList<String>(), "Chris Evans")
        q3Marvel.choices.add("Brie Larson")
        q3Marvel.choices.add("Chris Hemsworth")
        q3Marvel.choices.add("Chris Evans")
        q3Marvel.choices.add("Tom Holland")

        val q4Marvel = Question("What actor plays Thor?", ArrayList<String>(), "Chris Hemsworth")
        q4Marvel.choices.add("Brie Larson")
        q4Marvel.choices.add("Chris Hemsworth")
        q4Marvel.choices.add("Chris Evans")
        q4Marvel.choices.add("Tom Holland")

        marvelHerosTest.add(q1Marvel)
        marvelHerosTest.add(q2Marvel)
        marvelHerosTest.add(q3Marvel)
        marvelHerosTest.add(q4Marvel)

        testMap["Math"] = Test(mathTest)
        testMap["Physics"] = Test(physicsTest)
        testMap["Marvel Superheros"] = Test(marvelHerosTest)
        // tests and questions end
    }
}