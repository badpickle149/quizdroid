package edu.us.ischool.weng2k17.quizdroid


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class AnswerFragment : Fragment() {
    private val TEST_KEY = "test"
    private val USERS_ANSWER_KEY = "usersAnswer"
    private val CURR_QUESTION_NUM_KEY = "currQuestionNum"
    private val NUM_CORRECT_KEY = "numCorrect"

    interface OnNextBtnListener {
        fun onNextBtnClick()
    }

    companion object {

        fun newInstance(test: Topic, numCorrect: Int, usersAnswer: String, currQuestionNum: Int): AnswerFragment {
            val args = Bundle().apply {
                putSerializable("test", test)
                putInt("numCorrect", numCorrect)
                putString("usersAnswer", usersAnswer)
                putInt("currQuestionNum", currQuestionNum)
            }

            val fragment = AnswerFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.question_answer, container, false)
        val test = arguments?.let {
            it.getSerializable(TEST_KEY) as Topic
        }
        val totalQuestionNum = test!!.quizzes.size
        val numCorrect = arguments?.let {
            it.getInt(NUM_CORRECT_KEY)
        }
        val usersAns = arguments?.let {
            it.getString(USERS_ANSWER_KEY)
        }
        val questionNum = arguments?.let {
            it.getInt(CURR_QUESTION_NUM_KEY)
        }

        val correctAnsTextView = rootView.findViewById<TextView>(R.id.correctAns)
        val yourAnsTextView = rootView.findViewById<TextView>(R.id.yourAns)
        val numCorrectTextView = rootView.findViewById<TextView>(R.id.numCorrect)

        val question = test.quizzes[questionNum!! - 1]
        correctAnsTextView.text = question.choices[question.correct - 1]
        yourAnsTextView.text = usersAns
        numCorrectTextView.text = numCorrect.toString() + "/" + totalQuestionNum

        val nextBtn = rootView.findViewById<Button>(R.id.nextBtn)
        if (questionNum == totalQuestionNum) {
            nextBtn.text = "Finish"
        }
        nextBtn.setOnClickListener {
            if (questionNum != totalQuestionNum) {
                val btnListener = activity as OnNextBtnListener
                btnListener.onNextBtnClick()
            } else {
                val intent = Intent(activity, MainActivity::class.java)
                this.startActivity(intent)
            }
        }

        return rootView
    }


}
