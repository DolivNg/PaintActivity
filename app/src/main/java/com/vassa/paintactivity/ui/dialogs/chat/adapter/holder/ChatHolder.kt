package com.vassa.paintactivity.ui.dialogs.chat.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vassa.paintactivity.data.constants.VectorAssets
import com.vassa.paintactivity.databinding.LayoutChatElementBinding
import com.vassa.paintactivity.databinding.LayoutPlayerElementVoteBinding
import com.vassa.paintactivity.domain.entity.massage.MassageChatDomEntity

class ChatHolder(
    itemView: View,
    var binding: LayoutChatElementBinding,
    var holderCallBack: ChatHolderCallBack
) : RecyclerView.ViewHolder(itemView) {
    private lateinit var massage: MassageChatDomEntity

    fun bind(m: MassageChatDomEntity) {
        massage = m
        binding.imVAvatarChat.setImageResource(VectorAssets.vectors[massage.avatar])
        binding.imVAvatarChat.setBackgroundColor(VectorAssets.colors[massage.color])
        binding.tVNamePlayerChat.text = massage.name
        binding.tVMassegeChat.text = massage.massage
    }

    interface ChatHolderCallBack {
        fun holderCallBack(massage: MassageChatDomEntity)
    }
}