package edu.us.ischool.weng2k17.quizdroid

import java.io.Serializable

data class Question(val qText: String, val choices: ArrayList<String>, val answer: String): Serializable