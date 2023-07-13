package com.vassa.paintactivity.ui.fragments.option

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vassa.paintactivity.databinding.FragmentOptionBinding
import com.vassa.paintactivity.domain.entity.pack.FullPackDomEntity
import com.vassa.paintactivity.ui.fragments.option.dialogs.pack.PackListDialog
import com.vassa.paintactivity.ui.fragments.option.modelview.OptionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OptionFragment : Fragment(), PackListDialog.FragmentDialogCallBack {

    private var _binding: FragmentOptionBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = OptionFragment()
    }

    private val viewModel: OptionViewModel by viewModel<OptionViewModel>()

    lateinit var dialog: PackListDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOptionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog = PackListDialog(this)
        viewModel.loadFullPack()

        viewModel.getGlobalProfile().observe(viewLifecycleOwner) {
        }

        viewModel.getLocalProfileList().observe(viewLifecycleOwner) {
        }

        viewModel.getFullPackListProfile().observe(viewLifecycleOwner) {
            dialog.changeList(it)

        }
        binding.tVChoosePack.setOnClickListener { dialog.show(parentFragmentManager, "dlg1") }
    }

    override fun fragmentDialog(pack: FullPackDomEntity) {
        binding.tVChoosePack.text = pack.name
    }


}