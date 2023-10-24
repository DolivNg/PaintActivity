package com.vassa.paintactivity.ui.dialogs.vote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vassa.paintactivity.databinding.LayoutChatElementBinding
import com.vassa.paintactivity.databinding.LayoutPlayerElementVoteBinding
import com.vassa.paintactivity.domain.entity.massage.MassageChatDomEntity
import com.vassa.paintactivity.domain.entity.socket.InfoClientDomEntity
import com.vassa.paintactivity.domain.entity.socket.SuspicionDomEntity
import com.vassa.paintactivity.ui.dialogs.chat.adapter.holder.ChatHolder
import com.vassa.paintactivity.ui.dialogs.vote.adapter.holder.SuspicionHolder

class SuspicionAdapter(var sus: AdapterCallBack) :  RecyclerView.Adapter<SuspicionHolder>(), SuspicionHolder.ChatHolderCallBack {
    private var holderList : ArrayList<SuspicionHolder> = ArrayList()
    private var playerSuspicion: ArrayList<InfoClientDomEntity> = ArrayList()

    fun setList(list: ArrayList<InfoClientDomEntity>) {
        playerSuspicion = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuspicionHolder {
        val binding = LayoutPlayerElementVoteBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = SuspicionHolder(binding.root, binding, this)

        holderList.add(holder)
        return holder
    }

    override fun getItemCount(): Int {
        return playerSuspicion.size
    }

    override fun onBindViewHolder(holder: SuspicionHolder, position: Int) {
        holder.bind(playerSuspicion[position])
    }

    override fun holderCallBack(susp: SuspicionDomEntity) {
        sus.sendSuspicion(susp)
    }
    interface AdapterCallBack {
        fun sendSuspicion(susp: SuspicionDomEntity)
    }
}