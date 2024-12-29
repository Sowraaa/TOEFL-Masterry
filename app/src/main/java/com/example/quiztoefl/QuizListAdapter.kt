package com.example.quiztoefl

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiztoefl.databinding.QuiItemRecyclerRowBinding

class QuizListAdapter (private val quizModelList: List<QuizModel>) :
    RecyclerView.Adapter<QuizListAdapter.MyViewHolder>() {
    class MyViewHolder(private val binding: QuiItemRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(model : QuizModel){
            binding.apply {
                judulQuizText.text = model.judul
                penjelasanQuizText.text = model.penjelasan
                waktuQuizText.text = model.waktu + " min"
                root.setOnClickListener {
                    val intent = Intent(root.context,QuizActivity::class.java)
                    QuizActivity.questionModelList = model.pertanyaanList
                    QuizActivity.time = model.waktu
                    root.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = QuiItemRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return quizModelList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(quizModelList[position])
    }
}