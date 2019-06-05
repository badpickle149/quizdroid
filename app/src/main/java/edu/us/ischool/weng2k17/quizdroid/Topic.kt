package edu.us.ischool.weng2k17.quizdroid

import java.io.Serializable

//data class Topic(val name:String, val overviewDesc: String)

data class Topic(val title: String, val shortDesc: String, val longDesc: String, val quizzes: ArrayList<Quiz>): Serializable