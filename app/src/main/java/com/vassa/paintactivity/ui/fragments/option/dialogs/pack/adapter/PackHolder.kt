package com.vassa.paintactivity.ui.fragments.option.dialogs.pack.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vassa.paintactivity.databinding.LayoutTextListElementBinding
import com.vassa.paintactivity.domain.entity.pack.FullPackDomEntity

class PackHolder(
    itemView: View,
    var binding: LayoutTextListElementBinding,
    var holderCallBack: HolderCallBack
) :
    RecyclerView.ViewHolder(itemView) {

    lateinit var pack: FullPackDomEntity

    init {
        binding.tVName.setOnClickListener {
            holderCallBack.holderCallBack(pack)
        }
    }

    fun bind(pack: FullPackDomEntity) {
        this.pack = pack
        binding.tVName.text = pack.name
    }

    interface HolderCallBack {
        fun holderCallBack(pack: FullPackDomEntity)
    }
}