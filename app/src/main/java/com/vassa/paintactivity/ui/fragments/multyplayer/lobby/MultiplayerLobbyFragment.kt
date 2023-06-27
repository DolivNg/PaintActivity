package com.vassa.paintactivity.ui.fragments.multyplayer.lobby

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vassa.paintactivity.R

class MultiplayerLobbyFragment : Fragment() {

    companion object {
        fun newInstance() = MultiplayerLobbyFragment()
    }

    private lateinit var viewModel: MultiplayerLobbyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_multiplayer_lobby, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MultiplayerLobbyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}