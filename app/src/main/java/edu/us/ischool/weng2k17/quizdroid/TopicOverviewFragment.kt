package edu.us.ischool.weng2k17.quizdroid


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 *
 */
class TopicOverviewFragment : Fragment() {
    private val TEST_KEY = "test"
    private val NAME_KEY = "name"
    private val DESCRIPTION_KEY = "desc"

    companion object {
        val TEST_KEY = "test"
        val NAME_KEY = "name"
        val DESCRIPTION = "desc"

        fun newInstance(test: Test, topicName: String, description: String): TopicOverviewFragment {
            val args = Bundle().apply {
                putSerializable(TEST_KEY, test)
                putString(NAME_KEY, topicName)
                putString(DESCRIPTION, description)
            }

            val fragment = TopicOverviewFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_topic_overview, container, false)
        val test = arguments?.let {
            it.getSerializable(TEST_KEY)
        } as Test

        val topicName = arguments?.let {
            it.getString(NAME_KEY)
        }

        val topicDescription = arguments?.let {
            it.getString(DESCRIPTION_KEY)
        }

        val title = rootView.findViewById<TextView>(R.id.overviewTopicName)
        title.text =  topicName

        val desc = rootView.findViewById<TextView>(R.id.overviewDesc)
        desc.text = topicDescription

        return rootView
    }


}
