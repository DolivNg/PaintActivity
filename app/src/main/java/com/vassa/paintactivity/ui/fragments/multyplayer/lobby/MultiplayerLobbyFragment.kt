package com.vassa.paintactivity.ui.fragments.multyplayer.lobby

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vassa.paintactivity.data.constants.SocketConst.Companion.ADMIN_LOBBY_KEY
import com.vassa.paintactivity.data.constants.SocketConst.Companion.CLIENT_LOBBY_KEY
import com.vassa.paintactivity.databinding.FragmentMultiplayerLobbyBinding
import com.vassa.paintactivity.domain.entity.socket.InfoClientDomEntity
import com.vassa.paintactivity.ui.fragments.multyplayer.lobby.adapter.LobbyClientAdapter
import com.vassa.paintactivity.ui.intent.RoomOptionIntent
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random


class MultiplayerLobbyFragment : Fragment(),LobbyClientAdapter.LobbyAdapterCallback,MultiplayerLobbyViewModel.LobbyCallBack {
    private var _binding: FragmentMultiplayerLobbyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MultiplayerLobbyViewModel by viewModel<MultiplayerLobbyViewModel>()
    private lateinit var adapter : LobbyClientAdapter


    companion object {
        fun newInstance() = MultiplayerLobbyFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMultiplayerLobbyBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.disconnect()
                // Блокируйте действия кнопки "Back" здесь
                // Например, ничего не делайте или покажите сообщение
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)

        viewModel.loadGlobalProfile()
        viewModel.setListener(this)
        val client = arguments?.getParcelable<RoomOptionIntent>(CLIENT_LOBBY_KEY)
        val admin = arguments?.getParcelable<RoomOptionIntent>(ADMIN_LOBBY_KEY)

        if (client != null){
            viewModel.setRoomOption(client)
            viewModel.setTypeCl(0)
            viewModel.setRoom(client.group.toInt())
        }
        if (admin != null){
            viewModel.setRoomOption(admin)
            viewModel.setTypeCl(1)
            roomGeneration()
        }
        adapter = LobbyClientAdapter(this,viewModel.getTypeClient().value!!)
        binding.recVLobby.layoutManager = LinearLayoutManager(context)
        binding.recVLobby.adapter = adapter

        viewModel.getInfoClients().observe(viewLifecycleOwner){
            adapter.setList(it)
        }

        viewModel.getCountPLayer().observe(viewLifecycleOwner){
            binding.tVCountPlayerLobby.text = "$it/${viewModel.getRoomOption().value!!.playerCount}"
        }

        viewModel.getRoomOption().observe(viewLifecycleOwner){
            binding.tVIp.text = it.group
        }
        viewModel.connect()
    }

    private fun roomGeneration(){
        var room  = Random(System.currentTimeMillis()).nextInt(9)*10000+
                Random(System.currentTimeMillis()+2).nextInt(9)*1000+
                Random(System.currentTimeMillis()+5).nextInt(9)*100+
                Random(System.currentTimeMillis()+7).nextInt(9)*10+
                Random(System.currentTimeMillis()+1).nextInt(9)
        viewModel.setRoom(room)
    }

    override fun lobbyAdapterCallBack(local: InfoClientDomEntity) {
        viewModel.kickPlayer(local)
    }
    override fun disconnect() {
        findNavController().navigateUp()
    }

}