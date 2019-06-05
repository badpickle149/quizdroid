package edu.us.ischool.weng2k17.quizdroid

import java.io.Serializable

data class Quiz(val qText: String, val choices: ArrayList<String>, val correct: Int): Serializable