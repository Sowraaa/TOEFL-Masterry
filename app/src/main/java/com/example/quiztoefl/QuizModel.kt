package com.example.quiztoefl

data class QuizModel(
    val id : String,
    val judul : String,
    val penjelasan : String,
    val waktu : String,
    val pertanyaanList: List<QuestionModel>
){
    constructor() : this("","","","", emptyList())
}

data class QuestionModel(
    val pertanyaan : String,
    val pilgan : List<String>,
    val benar : String,
){
    constructor() : this ("", emptyList(),"")
}