package com.vassa.paintactivity.ui.fragments.option.dialogs.localprofile.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vassa.paintactivity.databinding.LayoutProfileListElementBinding
import com.vassa.paintactivity.domain.entity.profile.LocalProfileDomEntity
import com.vassa.paintactivity.ui.constants.VectorAssets

class LocalProfileHolder(
    itemView: View,
    var binding: LayoutProfileListElementBinding,
    var select: LocalProfileHolderCallBack
) : RecyclerView.ViewHolder(itemView) {
    private lateinit var local: LocalProfileDomEntity

    init {
        binding.lLayautElmeent.setOnClickListener { select.localProfileHolderCallBack(local) }
        binding.bttnCencelProfile.visibility = View.GONE
    }

    fun bind(loc: LocalProfileDomEntity) {
        local = loc
        binding.imVAvatarProfile.setImageResource(VectorAssets.vectors[local.avatar])
        binding.imVAvatarProfile.setBackgroundColor(local.color)
        binding.tVProfileName.text = local.name
    }

    interface LocalProfileHolderCallBack {
        fun localProfileHolderCallBack(local: LocalProfileDomEntity)
    }
}