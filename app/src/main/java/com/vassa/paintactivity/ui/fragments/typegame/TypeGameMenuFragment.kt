package com.vassa.paintactivity.ui.fragments.typegame

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vassa.paintactivity.R
import com.vassa.paintactivity.databinding.FragmentMainMenuBinding
import com.vassa.paintactivity.databinding.FragmentTypeGameMenuBinding
import com.vassa.paintactivity.data.constants.VectorAssets
import com.vassa.paintactivity.ui.fragments.menu.viewmodel.MainMenuViewModel
import com.vassa.paintactivity.ui.fragments.typegame.viewmodel.TypeGameMenuViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TypeGameMenuFragment : Fragment() {
    private var _binding: FragmentTypeGameMenuBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = TypeGameMenuFragment()
    }


    private val viewModel: TypeGameMenuViewModel by viewModel<TypeGameMenuViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTypeGameMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getGlobalProfile().observe(viewLifecycleOwner) {
            binding.include.tVNameProfile.text = it.name
            binding.include.imVAvatarProfil.setBackgroundColor(it.color)
            binding.include.imVAvatarProfil.setImageResource(VectorAssets.vectors[it.avatar])
        }
        viewModel.loadGlobalProfile()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bttnMultiplayer.setOnClickListener {
            findNavController().navigate(R.id.action_typeGameMenuFragment_to_multiplayerCreateFragment)
        }

        binding.bttnLocalGame.setOnClickListener {
            findNavController().navigate(R.id.action_typeGameMenuFragment_to_localGameFragment)
        }

        binding.bttnPartyGame.setOnClickListener {
            findNavController().navigate(R.id.action_typeGameMenuFragment_to_partyGameFragment)
        }

        binding.include.bttnImAchivments.setOnClickListener {
            findNavController().navigate(R.id.action_typeGameMenuFragment_to_achivmentFragment)
        }

    }

}