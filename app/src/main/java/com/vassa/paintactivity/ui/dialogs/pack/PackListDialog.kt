package com.vassa.paintactivity.ui.dialogs.pack

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vassa.paintactivity.databinding.DialogListTextBinding
import com.vassa.paintactivity.domain.entity.pack.FullPackDomEntity
import com.vassa.paintactivity.ui.dialogs.pack.adapter.PackListAdapter


class PackListDialog(var fragment : PackFragmentDialogCallBack) : DialogFragment(),
    PackListAdapter.PackAdapterCallBack {

    private var _binding: DialogListTextBinding? = null
    private val binding get() = _binding!!
    private val adapter: PackListAdapter = PackListAdapter(this)

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

    fun changeList(list: ArrayList<FullPackDomEntity>) {
        adapter.setList(list)
    }


    interface PackFragmentDialogCallBack {
        fun fragmentDialog(pack: FullPackDomEntity)
    }

    override fun adapterCallBack(pack: FullPackDomEntity) {
        fragment.fragmentDialog(pack)
        dismiss()
    }
}