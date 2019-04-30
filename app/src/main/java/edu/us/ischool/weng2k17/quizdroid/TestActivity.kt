package edu.us.ischool.weng2k17.quizdroid

import android.content.Intent
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

        if (intent.getBooleanExtra("edu.us.ischool.weng2k17.quizdroid.fromAnswerActivity", false)) {
            this.correct = intent.getIntExtra("edu.us.ischool.weng2k17.quizdroid.correct", 0)
            this.currQuestionNum = intent.getIntExtra("edu.us.ischool.weng2k17.quizdroid.currQuestionNum", 0)
        }

        val test = intent.getSerializableExtra("edu.us.ischool.weng2k17.quizdroid.test") as Test // ArrayList of Question objects
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
        val btnGroup = findViewById<RadioGroup>(R.id.btnContainer)

        btnGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener {group, checkedId ->
                if (checkedId > 0) {
                    nextBtn.visibility = View.VISIBLE
                }
            }
        )

        nextBtn.setOnClickListener {
            currQuestionNum++
            val checkedId = btnGroup.checkedRadioButtonId
            val yourAns = findViewById<RadioButton>(checkedId).text
            if (yourAns == currQuestion.answer) {
                correct++
            }

            val intent = Intent(this, AnswerActivity::class.java)
            intent.putExtra("edu.us.ischool.weng2k17.quizdroid.correct", correct)
            intent.putExtra("edu.us.ischool.weng2k17.quizdroid.currQuestionNum", currQuestionNum)
            intent.putExtra("edu.us.ischool.weng2k17.quizdroid.test", test)
            intent.putExtra("edu.us.ischool.weng2k17.quizdroid.yourAns", yourAns)
            this.startActivity(intent)
        }
    }
}