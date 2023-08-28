package com.vassa.paintactivity.ui.fragments.multyplayer.create

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.vassa.paintactivity.R
import com.vassa.paintactivity.databinding.FragmentMultiplayerCreateBinding
import com.vassa.paintactivity.domain.entity.pack.FullPackDomEntity
import com.vassa.paintactivity.data.constants.SocketConst.Companion.ADMIN_LOBBY_KEY
import com.vassa.paintactivity.data.constants.SocketConst.Companion.CLIENT_LOBBY_KEY
import com.vassa.paintactivity.data.constants.VectorAssets
import com.vassa.paintactivity.ui.dialogs.pack.PackListDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class MultiplayerCreateFragment : Fragment(), PackListDialog.PackFragmentDialogCallBack {
    private var _binding: FragmentMultiplayerCreateBinding? = null
    private val binding get() = _binding!!
    private lateinit var dialogList: PackListDialog
    private var packId = -1

    companion object {
        fun newInstance() = MultiplayerCreateFragment()
    }


    val viewModel: MultiplayerCreateViewModel by viewModel<MultiplayerCreateViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMultiplayerCreateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadGlobalProfile()
        viewModel.loadPack()
        dialogList = PackListDialog(this)

        viewModel.getGameSetting().observe(viewLifecycleOwner) {
            binding.tVPlayerCountMult.text = it.playerCount.toString()
            binding.tVImposterCountMult.text = it.imposter.toString()
            binding.tVInkCountMult.text = it.inkCount.toString()
            binding.tVTimeCountMult.text = it.timeSek.toString()
            if (it.author) {
                binding.tVPackShowerMult.visibility = View.GONE
                binding.tVPackMult.visibility = View.GONE
                binding.swRandomPackMult.visibility = View.GONE
            } else {
                binding.tVPackShowerMult.visibility = View.VISIBLE
                binding.tVPackMult.visibility = View.VISIBLE
                binding.swRandomPackMult.visibility = View.VISIBLE
            }

            if (it.timer) {
                binding.lLTimer.visibility = View.GONE
            } else {
                binding.lLTimer.visibility = View.VISIBLE
            }
        }


        binding.swAutorMult.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.changeAuthor(isChecked)
        }

        binding.swTimerMult.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.changeTimer(isChecked)
        }
        viewModel.getPackList().observe(viewLifecycleOwner) { list ->
            dialogList.changeList(list)
            if (packId == -1) {
                viewModel.setPack(list[0].id)
                packId = list[0].id
                binding.tVPackShowerMult.text = list[0].name
            } else {
                list.forEach {
                    if (it.id == packId) {
                        viewModel.setPack(it.id)
                        packId = it.id
                        binding.tVPackShowerMult.text = it.name
                    }
                }
            }
        }

        viewModel.getGlobalProfile().observe(viewLifecycleOwner) {
            binding.includedMultiProfile.tVNameProfile.text = it.name
            binding.includedMultiProfile.imVAvatarProfil.setBackgroundColor(it.color)
            binding.includedMultiProfile.imVAvatarProfil.setImageResource(VectorAssets.vectors[it.avatar])
            binding.includedMultiProfile.bttnOptionGame.visibility = View.GONE
            binding.includedMultiProfile.bttnImAchivments.visibility = View.GONE
        }
        //create connect
        binding.bttnConnect.setOnClickListener {
            viewModel.setGroup(binding.edTConnect.text.toString())
            if (viewModel.getGameSetting().value?.group !="")
            {
                var bundle = Bundle()
                bundle.putParcelable(CLIENT_LOBBY_KEY,viewModel.getGameSetting().value)
                bundle.putParcelable(ADMIN_LOBBY_KEY,null)
                findNavController().navigate(R.id.action_multiplayerCreateFragment_to_multiplayerLobbyFragment,bundle)
                //TODO TOAST
            }
        }
        //create lobby
        binding.bttnCreateLobby.setOnClickListener {
            var bundle = Bundle()
            bundle.putParcelable(ADMIN_LOBBY_KEY,viewModel.getGameSetting().value)
            bundle.putParcelable(CLIENT_LOBBY_KEY,null)
            findNavController().navigate(R.id.action_multiplayerCreateFragment_to_multiplayerLobbyFragment,bundle)
        }

        binding.edTConnect.setOnClickListener {
            binding.edTConnect.isCursorVisible = true
        }
        binding.edTConnect.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                viewModel.setGroup(binding.edTConnect.text.toString())
                binding.edTConnect.clearFocus()
                binding.edTConnect.isCursorVisible = false
                val imm =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                return@setOnEditorActionListener true;
            }
            return@setOnEditorActionListener false;
        }


        binding.tVPackShowerMult.setOnClickListener { dialogList.show(parentFragmentManager,
            "local") }

        binding.bttnMinusImposterMult.setOnClickListener {
            viewModel.setImposters(-1)

        }
        binding.bttnPlusImposterMult.setOnClickListener {
            viewModel.setImposters(1)
        }

        binding.bttnPlusPlayerMult.setOnClickListener {
            viewModel.setPlayer(1)
        }
        binding.bttnMinusPlayerMult.setOnClickListener {
            viewModel.setPlayer(-1)
        }

        binding.bttnPlusTimerMult.setOnClickListener {
            viewModel.setTime(1)
        }
        binding.bttnMinusTimerMult.setOnClickListener {
            viewModel.setTime(-1)
        }

        binding.bttnPlusInkMult.setOnClickListener {
            viewModel.setInk(1)
        }
        binding.bttnMinusInkMult.setOnClickListener {
            viewModel.setInk(-1)
        }

    }

    override fun fragmentDialog(pack: FullPackDomEntity) {
        packId = pack.id
        viewModel.setPack(pack.id)
        binding.tVPackShowerMult.text = pack.name
    }

}