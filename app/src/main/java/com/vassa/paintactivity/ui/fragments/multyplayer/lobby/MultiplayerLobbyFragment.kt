package com.vassa.paintactivity.ui.fragments.multyplayer.lobby

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vassa.paintactivity.R
import io.socket.client.IO
import io.socket.client.Socket


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
        viewModel = ViewModelProvider(this)[MultiplayerLobbyViewModel::class.java]
        // TODO: Use the ViewModel
    }

}