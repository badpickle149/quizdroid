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

class TopicOverviewActivity: AppCompatActivity(), SingleQuestionFragment.OnSubmitBtnListener, AnswerFragment.OnNextBtnListener{

    private var testMap = mutableMapOf<String, Topic>()
    private var correct = 0
    private var currQuestionNum = 0
    private var topicName = ""
    private val fragmentContainer = R.id.fragmentContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topic_overview)

        testMap = QuizApp.accessTopicRepo()

        // get topic name and description from MainActivity
        topicName = intent.getStringExtra("TopicName")
        val description = intent.getStringExtra("Description")

        val overviewContainer = findViewById<LinearLayout>(R.id.overviewContainer)

        val overviewTitle = findViewById<TextView>(R.id.overviewTopicName)
        overviewTitle.text = topicName

        val overviewDesc = findViewById<TextView>(R.id.overviewDesc)
        overviewDesc.text = description

        val questionNumber = findViewById<TextView>(R.id.overviewQuestionNum)
        val test = testMap[topicName] as Topic
        questionNumber.text = test.quizzes.size.toString()

        val beginBtn = findViewById<Button>(R.id.beginButton)

        // When begin button is clicked it takes user to first question
        beginBtn.setOnClickListener {
            val fragment: SingleQuestionFragment = SingleQuestionFragment.newInstance(testMap[topicName]!!.quizzes[currQuestionNum])

            // add fragment to View
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.replace(fragmentContainer, fragment)
            ft.commit()

            overviewContainer.visibility = View.GONE
        }
    }

    // handles clicking the "submit" button on the Question page
    // makes and displays an AnswerFragment
    override fun onSubmitClick(userAnswer: String) {
        currQuestionNum++
        val lastQuestion = testMap[topicName]!!.quizzes[currQuestionNum - 1];
        if (lastQuestion.choices.indexOf(userAnswer) == lastQuestion.correct - 1) {
            correct++
        }

        val fragment = AnswerFragment.newInstance(testMap[topicName] as Topic, correct, userAnswer, currQuestionNum)
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(fragmentContainer, fragment)
        ft.commit()
    }

    // handles clicking the "next" button on the Answer page
    // makes and displays a SingleButtonFragment
    override fun onNextBtnClick() {
        val fragment: SingleQuestionFragment = SingleQuestionFragment.newInstance(testMap[topicName]!!.quizzes[currQuestionNum])

        // add fragment to View
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(fragmentContainer, fragment)
        ft.commit()
    }
}