package com.vassa.paintactivity.ui.fragments.multyplayer.lobby.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vassa.paintactivity.data.constants.VectorAssets
import com.vassa.paintactivity.databinding.LayoutProfileListElementBinding
import com.vassa.paintactivity.domain.entity.socket.InfoClientDomEntity

class LobbyClientHolder (
    itemView: View,
    var binding: LayoutProfileListElementBinding,
    var select: LobbyHolderCallBack
    ) : RecyclerView.ViewHolder(itemView) {
        private lateinit var local: InfoClientDomEntity

        init {
            binding.lLayautElmeent.setOnClickListener { select.lobbyHolderCallBack(local) }
            //binding.bttnCencelProfile.visibility = View.GONE
        }

        fun bind(loc: InfoClientDomEntity) {
            local = loc
            binding.imVAvatarProfile.setImageResource(VectorAssets.vectors[local.avatarId])
            binding.imVAvatarProfile.setBackgroundColor(local.colorId)
            binding.tVProfileName.text = local.name
        }

        interface LobbyHolderCallBack {
            fun lobbyHolderCallBack(local: InfoClientDomEntity)
        }
    }