package com.vassa.paintactivity.ui.dialogs.pack.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vassa.paintactivity.databinding.LayoutTextListElementBinding
import com.vassa.paintactivity.domain.entity.pack.FullPackDomEntity

class PackHolder(
    itemView: View,
    var binding: LayoutTextListElementBinding,
    var holderCallBack: PackHolderCallBack
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

    interface PackHolderCallBack {
        fun holderCallBack(pack: FullPackDomEntity)
    }
}