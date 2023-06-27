package com.vassa.paintactivity.ui.fragments.multyplayer.create

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vassa.paintactivity.R

class MultiplayerCreateFragment : Fragment() {

    companion object {
        fun newInstance() = MultiplayerCreateFragment()
    }

    private lateinit var viewModel: MultiplayerCreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_multiplayer_create, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MultiplayerCreateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}