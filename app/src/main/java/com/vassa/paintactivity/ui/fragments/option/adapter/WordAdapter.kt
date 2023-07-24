package com.vassa.paintactivity.ui.fragments.option.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vassa.paintactivity.databinding.LayoutWordListElementBinding
import com.vassa.paintactivity.domain.entity.pack.WordDomEntity

class WordAdapter(var callBack : WordAdapterCallBack) : RecyclerView.Adapter<WordHolder>(),
    WordHolder.WordHolderCallBack {

    private var wordListHolders: ArrayList<WordHolder> = ArrayList()
    private var wordDomEntityList: ArrayList<WordDomEntity> = ArrayList()


    fun setList(list: ArrayList<WordDomEntity>) {
        wordDomEntityList = list

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        val binding = LayoutWordListElementBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = WordHolder(binding.root, binding,this)

        wordListHolders.add(holder)
        return holder
    }

    override fun getItemCount(): Int {

        return wordDomEntityList.size
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        holder.bind(wordDomEntityList[position])
    }

    override fun wordHolderDeleteCallBack(word: WordDomEntity) {
        callBack.wordAdapterDeleteCallBack(word)
    }

    override fun wordHolderEditCallBack(word: WordDomEntity) {
        callBack.wordAdapterEditCallBack(word)
    }

    interface WordAdapterCallBack{
        fun wordAdapterDeleteCallBack(word: WordDomEntity)
        fun wordAdapterEditCallBack(word: WordDomEntity)
    }
}