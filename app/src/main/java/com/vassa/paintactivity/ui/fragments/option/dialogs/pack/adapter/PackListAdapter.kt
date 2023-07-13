package com.vassa.paintactivity.ui.fragments.option.dialogs.pack.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vassa.paintactivity.databinding.LayoutTextListElementBinding
import com.vassa.paintactivity.domain.entity.pack.FullPackDomEntity

class PackListAdapter(private var adapterCallBack: AdapterCallBack) :
    RecyclerView.Adapter<PackHolder>(),
    PackHolder.HolderCallBack {
    private var packListHolders: ArrayList<PackHolder> = ArrayList()
    private var fullPackDomEntityList: ArrayList<FullPackDomEntity> = ArrayList()


    fun setList(list: ArrayList<FullPackDomEntity>) {
        fullPackDomEntityList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackHolder {
        val binding = LayoutTextListElementBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = PackHolder(binding.root, binding, this)

        packListHolders.add(holder)
        return holder
    }

    override fun getItemCount(): Int {
        return fullPackDomEntityList.size
    }

    override fun onBindViewHolder(holder: PackHolder, position: Int) {
        holder.bind(fullPackDomEntityList[position])
    }

    interface AdapterCallBack {
        fun adapterCallBack(pack: FullPackDomEntity)
    }

    override fun holderCallBack(pack: FullPackDomEntity) {
        adapterCallBack.adapterCallBack(pack)
    }


}