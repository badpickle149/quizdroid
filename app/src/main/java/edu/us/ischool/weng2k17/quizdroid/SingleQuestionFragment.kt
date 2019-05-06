package edu.us.ischool.weng2k17.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class SingleQuestionFragment : Fragment() {

    interface OnSubmitBtnListener {
        fun onSubmitClick(userAnswer: String)
    }

    private val QUESTION_KEY = "question"
    companion object {
        private val KEY = "question"

        fun newInstance(question: Question): SingleQuestionFragment {
            val args = Bundle().apply {
                putSerializable(KEY, question)
            }

            val fragment = SingleQuestionFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_single_question, container, false)

        val question = arguments?.let {
            it.getSerializable(QUESTION_KEY)
        } as Question

        val questionText = rootView.findViewById<TextView>(R.id.questionText) // TextView in single_question.xml
        questionText.text = question.qText

        val choices = question.choices
        val choiceBtns = ArrayList<RadioButton>()
        choiceBtns.add(rootView.findViewById(R.id.choice1))
        choiceBtns.add(rootView.findViewById(R.id.choice2))
        choiceBtns.add(rootView.findViewById(R.id.choice3))
        choiceBtns.add(rootView.findViewById(R.id.choice4))

        for (i in 0 until choices.size) {
            choiceBtns[i].text = choices[i]
        }


        val nextBtn = rootView.findViewById<Button>(R.id.nextQuestionBtn)
        val btnGroup = rootView.findViewById<RadioGroup>(R.id.btnContainer)

        btnGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                if (checkedId > 0) {
                    nextBtn.visibility = View.VISIBLE
                }
            }
        )

        /*
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
        */

        nextBtn.setOnClickListener {
            val checkedId = btnGroup.checkedRadioButtonId
            val yourAns = rootView.findViewById<RadioButton>(checkedId).text
            val btnListener = activity as OnSubmitBtnListener
            btnListener.onSubmitClick(yourAns.toString())
        }

        return rootView
    }
}