package com.vassa.paintactivity.ui.fragments.option

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vassa.paintactivity.databinding.LayoutWordListElementBinding
import com.vassa.paintactivity.domain.entity.pack.WordDomEntity

class WordHolder(itemView: View, var binding: LayoutWordListElementBinding,var callBack : WordHolderCallBack) :
    RecyclerView.ViewHolder(itemView) {
    lateinit var word: WordDomEntity


    init {
        binding.bttnDeleteWord.setOnClickListener{
            callBack.wordHolderDeleteCallBack(word)
        }

        binding.bttnEditWord.setOnClickListener{
            callBack.wordHolderEditCallBack(word)
        }
    }


    fun bind(word: WordDomEntity) {
        this.word = word
        binding.tVWord.text = word.word
    }

    interface WordHolderCallBack{
        fun wordHolderDeleteCallBack(word: WordDomEntity)
        fun wordHolderEditCallBack(word: WordDomEntity)
    }
}