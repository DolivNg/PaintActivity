package com.vassa.paintactivity.ui.fragments.menu

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vassa.paintactivity.R
import com.vassa.paintactivity.databinding.FragmentMainMenuBinding
import com.vassa.paintactivity.domain.entity.profile.GlobalProfileDomEntity
import com.vassa.paintactivity.data.constants.VectorAssets
import com.vassa.paintactivity.ui.fragments.menu.viewmodel.MainMenuViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author vassa
 * version code 1.0
 * */

class MainMenuFragment : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = MainMenuFragment()
    }

    val viewModel: MainMenuViewModel  by viewModel<MainMenuViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMenuBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       // viewModel = ViewModelProvider(this)[MainMenuViewModel::class.java]

        viewModel.getGlobalProfile().observe(viewLifecycleOwner) {
            binding.inclBar.tVNameProfile.text = it.name
            binding.inclBar.imVAvatarProfil.setBackgroundColor(it.color)
            binding.inclBar.imVAvatarProfil.setImageResource(VectorAssets.vectors[it.avatar])
        }
        viewModel.loadGlobalProfile()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bttnPlay.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_typeGameMenuFragment)
        }

        binding.bttnRuleGame.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_ruleFragment)
        }

        binding.bttnMakePen.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_makePenFragment)
        }

        binding.inclBar.bttnImAchivments.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_achivmentFragment)
        }

        binding.inclBar.bttnOptionGame.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_optionFragment)
        }
    }

}