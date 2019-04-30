package edu.us.ischool.weng2k17.quizdroid

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class AnswerActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question_answer)

        val test = intent.getSerializableExtra("edu.us.ischool.weng2k17.quizdroid.test") as Test
        val totalQuestionNum = test.questions.size
        val numCorrect = intent.getIntExtra("edu.us.ischool.weng2k17.quizdroid.correct", 0)
        val usersAns = intent.getStringExtra("edu.us.ischool.weng2k17.quizdroid.yourAns")
        val questionNum = intent.getIntExtra("edu.us.ischool.weng2k17.quizdroid.currQuestionNum", 0)

        val correctAnsTextView = findViewById<TextView>(R.id.correctAns)
        val yourAnsTextView = findViewById<TextView>(R.id.yourAns)
        val numCorrectTextView = findViewById<TextView>(R.id.numCorrect)

        correctAnsTextView.text = test.questions[questionNum - 1].answer
        yourAnsTextView.text = usersAns
        numCorrectTextView.text = numCorrect.toString() + "/" + totalQuestionNum

        val nextBtn = findViewById<Button>(R.id.nextBtn)
        if (questionNum == totalQuestionNum) {
            nextBtn.text = "Finish"
        }
        nextBtn.setOnClickListener {
             if (questionNum != totalQuestionNum) {
                 val intent = Intent(this, TestActivity::class.java)
                 intent.putExtra("edu.us.ischool.weng2k17.quizdroid.correct", numCorrect)
                 intent.putExtra("edu.us.ischool.weng2k17.quizdroid.currQuestionNum", questionNum)
                 intent.putExtra("edu.us.ischool.weng2k17.quizdroid.fromAnswerActivity", true)
                 intent.putExtra("edu.us.ischool.weng2k17.quizdroid.test", test)
                 this.startActivity(intent)
             } else {
                 val intent = Intent(this, MainActivity::class.java)
                 this.startActivity(intent)
             }
        }

    }
}