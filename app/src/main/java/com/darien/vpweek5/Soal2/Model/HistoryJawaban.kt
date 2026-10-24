package com.darien.vpweek5.Soal2.Model


data class AnswerHistory(
    val question: Question,
    val selectedAnswer: String
) {
    val isCorrect: Boolean
        get() = selectedAnswer == question.correctAnswer
}