package com.vassa.paintactivity.ui.dialogs.vote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vassa.paintactivity.data.constants.VectorAssets
import com.vassa.paintactivity.databinding.DialogVoteMultiplayerBinding
import com.vassa.paintactivity.domain.entity.massage.MassageChatDomEntity
import com.vassa.paintactivity.domain.entity.socket.InfoClientDomEntity
import com.vassa.paintactivity.domain.entity.socket.SuspicionDomEntity
import com.vassa.paintactivity.ui.dialogs.vote.adapter.SuspicionAdapter

class VoteDialog(var fragment: VoteCallBack) : DialogFragment(), SuspicionAdapter.AdapterCallBack {
    private var _binding: DialogVoteMultiplayerBinding? = null
    private val binding get() = _binding!!
    private var vote: Boolean = false
    private val adapter = SuspicionAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogVoteMultiplayerBinding.inflate(layoutInflater)
        return binding.root
    }

    fun setListClient(arrayList: ArrayList<InfoClientDomEntity>) {
        adapter.setList(arrayList)
        //recyclerView.adapter = adapter
    }

    fun voteMay(v: Boolean) {
        vote = v
    }

    fun setClientSelected(info: InfoClientDomEntity?) {
        if (info == null) {
            binding.inclSupicPlayer.lLayautElmeent.visibility = View.GONE
        } else {
            binding.inclSupicPlayer.lLayautElmeent.visibility = View.VISIBLE
            binding.inclSupicPlayer.tVProfileName.text = info.name
            binding.inclSupicPlayer.imVAvatarProfile.setImageResource(VectorAssets.vectors[info.avatarId])
            binding.inclSupicPlayer.imVAvatarProfile.setBackgroundColor(VectorAssets.colors[info.colorId])
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inclSupicPlayer.bttnCencelProfile.visibility = View.GONE

        binding.bttnVote.setOnClickListener {
            if (vote && binding.inclSupicPlayer.lLayautElmeent.visibility == View.VISIBLE) {
                fragment.votedSuspicion()
            }
        }

        binding.bttnWait.setOnClickListener { dismiss() }

        binding.rVSuspincionList.layoutManager = LinearLayoutManager(context)
        binding.rVSuspincionList.adapter = adapter
    }

    override fun sendSuspicion(susp: SuspicionDomEntity) {
        fragment.suspicConfirm(susp)
    }

    interface VoteCallBack {
        fun suspicRemoved(susp: SuspicionDomEntity)
        fun suspicConfirm(susp: SuspicionDomEntity)
        fun votedSuspicion()
    }
}