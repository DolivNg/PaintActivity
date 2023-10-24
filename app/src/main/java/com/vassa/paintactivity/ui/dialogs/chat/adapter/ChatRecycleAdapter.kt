package com.vassa.paintactivity.ui.dialogs.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vassa.paintactivity.databinding.LayoutChatElementBinding
import com.vassa.paintactivity.domain.entity.massage.MassageChatDomEntity
import com.vassa.paintactivity.ui.dialogs.chat.adapter.holder.ChatHolder

class ChatRecycleAdapter :  RecyclerView.Adapter<ChatHolder>(), ChatHolder.ChatHolderCallBack {
    private var holderList : ArrayList<ChatHolder> = ArrayList()
    private var massageDomEntityList: ArrayList<MassageChatDomEntity> = ArrayList()

    fun setList(list: ArrayList<MassageChatDomEntity>) {
        massageDomEntityList = list

        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val binding = LayoutChatElementBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ChatHolder(binding.root, binding, this)

        holderList.add(holder)
        return holder
    }

    override fun getItemCount(): Int {
        return massageDomEntityList.size
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.bind(massageDomEntityList[position])
    }

    override fun holderCallBack(massage: MassageChatDomEntity) {

    }
}