package com.vassa.paintactivity.ui.fragments.option.dialogs.localprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vassa.paintactivity.databinding.DialogListTextBinding
import com.vassa.paintactivity.domain.entity.profile.LocalProfileDomEntity
import com.vassa.paintactivity.ui.fragments.option.dialogs.localprofile.adapters.LocalProfileAdapters

class LocalProfileDialog(var localCallBack: LocalFragmentDialogCallBack) : DialogFragment(),LocalProfileAdapters.LocalProfAdapterCallback {
    private var _binding: DialogListTextBinding? = null
    private val binding get() = _binding!!
    private var adapter : LocalProfileAdapters = LocalProfileAdapters(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogListTextBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycleText.layoutManager = LinearLayoutManager(context)
        binding.recycleText.adapter = adapter

        binding.bttnCencelDialog.setOnClickListener { dismiss() }
    }

    fun changeList(list: ArrayList<LocalProfileDomEntity>) {
        adapter.setList(list)
    }


    interface LocalFragmentDialogCallBack {
        fun localFragmentDialog(local: LocalProfileDomEntity)
    }

    override fun localProfileAdapterCallBack(local: LocalProfileDomEntity) {
        localCallBack.localFragmentDialog(local)
        dismiss()
    }


}