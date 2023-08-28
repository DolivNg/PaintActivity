package com.vassa.paintactivity.ui.fragments.multyplayer.lobby.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vassa.paintactivity.databinding.LayoutProfileListElementBinding
import com.vassa.paintactivity.domain.entity.socket.InfoClientDomEntity

class LobbyClientAdapter(var fragment : LobbyAdapterCallback,var c : Int) :
RecyclerView.Adapter<LobbyClientHolder>(), LobbyClientHolder.LobbyHolderCallBack {

    private var localViewHolders: ArrayList<LobbyClientHolder> = ArrayList()
    private var nameArray: ArrayList<InfoClientDomEntity> = ArrayList()

    fun setList(list: ArrayList<InfoClientDomEntity>) {
        nameArray = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LobbyClientHolder {
        val binding = LayoutProfileListElementBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = LobbyClientHolder(binding.root, binding, this,c)

        localViewHolders.add(holder)
        return holder
    }

    override fun getItemCount(): Int {
        return nameArray.size
    }

    override fun onBindViewHolder(holder: LobbyClientHolder, position: Int) {
        holder.bind(nameArray[position])
    }

    interface LobbyAdapterCallback {
        fun lobbyAdapterCallBack(local: InfoClientDomEntity)
    }

    override fun lobbyHolderCallBack(local: InfoClientDomEntity) {
        fragment.lobbyAdapterCallBack(local)
    }
}