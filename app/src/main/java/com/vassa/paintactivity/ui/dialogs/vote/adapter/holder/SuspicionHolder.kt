package com.vassa.paintactivity.ui.dialogs.vote.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vassa.paintactivity.data.constants.VectorAssets
import com.vassa.paintactivity.databinding.LayoutPlayerElementVoteBinding
import com.vassa.paintactivity.domain.entity.socket.InfoClientDomEntity
import com.vassa.paintactivity.domain.entity.socket.SuspicionDomEntity

class SuspicionHolder (itemView: View,
                       var binding: LayoutPlayerElementVoteBinding,
                       var holderCallBack: ChatHolderCallBack
) : RecyclerView.ViewHolder(itemView) {
    private lateinit var suspicion: InfoClientDomEntity

    fun bind(m: InfoClientDomEntity) {
        suspicion = m
        binding.imBMakeSusp.setOnClickListener { holderCallBack.holderCallBack(SuspicionDomEntity(suspicion.key,0,1)) }

        binding.imVAvatarSusp.setImageResource(VectorAssets.vectors[suspicion.avatarId])
        binding.imVAvatarSusp.setBackgroundColor(VectorAssets.colors[suspicion.colorId])
        binding.tVNameSusp.text = suspicion.name
    }

    interface ChatHolderCallBack {
        fun holderCallBack(susp: SuspicionDomEntity)
    }
}
