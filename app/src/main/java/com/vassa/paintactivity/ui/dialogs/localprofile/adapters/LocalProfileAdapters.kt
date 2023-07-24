package com.vassa.paintactivity.ui.dialogs.localprofile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vassa.paintactivity.databinding.LayoutProfileListElementBinding
import com.vassa.paintactivity.domain.entity.pack.FullPackDomEntity
import com.vassa.paintactivity.domain.entity.profile.LocalProfileDomEntity

class LocalProfileAdapters(var fragment : LocalProfAdapterCallback) :
    RecyclerView.Adapter<LocalProfileHolder>(), LocalProfileHolder.LocalProfileHolderCallBack {

    private var localViewHolders: ArrayList<LocalProfileHolder> = ArrayList()
    var nameArray: ArrayList<LocalProfileDomEntity> = ArrayList()

    fun setList(list: ArrayList<LocalProfileDomEntity>) {
        nameArray = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalProfileHolder {
        val binding = LayoutProfileListElementBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = LocalProfileHolder(binding.root, binding, this)

        localViewHolders.add(holder)
        return  holder
    }

    override fun getItemCount(): Int {
        return nameArray.size
    }

    override fun onBindViewHolder(holder: LocalProfileHolder, position: Int) {
        holder.bind(nameArray[position])
    }


    interface LocalProfAdapterCallback{
        fun localProfileAdapterCallBack(local: LocalProfileDomEntity)
    }

    override fun localProfileHolderCallBack(local: LocalProfileDomEntity) {
        fragment.localProfileAdapterCallBack(local)
    }

}
