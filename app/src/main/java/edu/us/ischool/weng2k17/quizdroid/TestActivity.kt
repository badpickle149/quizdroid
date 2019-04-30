package edu.us.ischool.weng2k17.quizdroid

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class TestActivity: AppCompatActivity() {

    private var correct: Int = 0
    private var currQuestionNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_question)
        val test = intent.getSerializableExtra("edu.us.ischool.weng2k17.quizdroid.test") as Test // ArrayList of Question objects
        Log.i("TestActivity", test.toString())
        val questionText = findViewById<TextView>(R.id.questionText) // TextView in single_question.xml
        val currQuestion = test.questions[currQuestionNum] // current question to display
        questionText.text = currQuestion.qText

        val choices = currQuestion.choices
        val choiceBtns = ArrayList<RadioButton>()
        choiceBtns.add(findViewById(R.id.choice1))
        choiceBtns.add(findViewById(R.id.choice2))
        choiceBtns.add(findViewById(R.id.choice3))
        choiceBtns.add(findViewById(R.id.choice4))

        for (i in 0 until choices.size) {
            choiceBtns[i].text = choices[i]
        }


        val nextBtn = findViewById<Button>(R.id.nextQuestionBtn)
        nextBtn.setOnClickListener {
            nextBtn.visibility = View.VISIBLE
            val btnGroup = findViewById<RadioGroup>(R.id.btnContainer)
            val checkedId = btnGroup.checkedRadioButtonId
            if (findViewById<RadioButton>(checkedId).text == currQuestion.answer) {
                correct++
            }
        }
    }
}