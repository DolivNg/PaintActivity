package com.vassa.paintactivity.ui.fragments.option

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vassa.paintactivity.databinding.FragmentOptionBinding
import com.vassa.paintactivity.domain.entity.pack.FullPackDomEntity
import com.vassa.paintactivity.domain.entity.pack.WordDomEntity
import com.vassa.paintactivity.domain.entity.profile.GlobalProfileDomEntity
import com.vassa.paintactivity.ui.constants.VectorAssets
import com.vassa.paintactivity.ui.fragments.option.dialogs.color.ChoosingColorDialog
import com.vassa.paintactivity.ui.fragments.option.dialogs.avatar.ChoosingAvatarDialog
import com.vassa.paintactivity.ui.fragments.option.dialogs.pack.PackListDialog
import com.vassa.paintactivity.ui.fragments.option.modelview.OptionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class OptionFragment : Fragment(), PackListDialog.FragmentDialogCallBack,
    WordAdapter.WordAdapterCallBack,ChoosingAvatarDialog.ChooseAvatarCallBack, ChoosingColorDialog.ChooseColorCallBack {

    private var _binding: FragmentOptionBinding? = null
    private val binding get() = _binding!!

    private var wordAdapter = WordAdapter(this)
    private var choosePackId = 1
    private val viewModel: OptionViewModel by viewModel<OptionViewModel>()
    private lateinit var dialogPack: PackListDialog
    private lateinit var dialogChoosingAvatar : ChoosingAvatarDialog

    private lateinit var globalProfileDomEntity : GlobalProfileDomEntity
    private lateinit var colorGlobalDialog : ChoosingColorDialog
    companion object {
        fun newInstance() = OptionFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOptionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Dialog Init
        //Pack list dialog window
        dialogPack = PackListDialog(this)
        //Avatar dialog window
        dialogChoosingAvatar = ChoosingAvatarDialog(this)
        //choosing color
        colorGlobalDialog = ChoosingColorDialog(this)

        //Init RecycleView
        binding.recycleVPackWord.layoutManager = LinearLayoutManager(context)
        binding.recycleVPackWord.adapter = wordAdapter

        //viewModel Action
        viewModel.loadFullPack()
        viewModel.loadGlobalProfile()


        //Observe  GlobalProfile
        viewModel.getGlobalProfile().observe(viewLifecycleOwner) {
            globalProfileDomEntity = it
            binding.edTGlobalProfileName.setText(it.name.subSequence(0,it.name.length))
            binding.imVProfileAvatar.setBackgroundColor(it.color)
            binding.imVGlobalProfileColor.setBackgroundColor(it.color)
            binding.imVProfileAvatar.setImageResource(VectorAssets.vectors[it.avatar])
        }

        //Observe LocalProfile
        viewModel.getLocalProfileList().observe(viewLifecycleOwner) {
        }

        //Observe FullPack
        viewModel.getFullPackListProfile().observe(viewLifecycleOwner) { list ->
            dialogPack.changeList(list)
            list.forEach {
                if (choosePackId == it.id) {
                    binding.tVChoosePack.text = it.name
                    wordAdapter.setList(it.wordArray)
                }
            }
        }
        //Listener Keyboard edTGlobalProfileName
        binding.edTGlobalProfileName.setOnClickListener { binding.edTGlobalProfileName.isCursorVisible = true }
        binding.edTGlobalProfileName.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                globalProfileDomEntity.name = binding.edTGlobalProfileName.text.toString()
                viewModel.updateGlobalProfile(globalProfileDomEntity)
                binding.edTGlobalProfileName.clearFocus()
                binding.edTGlobalProfileName.isCursorVisible = false
                val imm =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)

                return@setOnEditorActionListener true;
            }
            return@setOnEditorActionListener false;
        }

        //Show Dialog
        binding.imVGlobalProfileColor.setOnClickListener { colorGlobalDialog.show(parentFragmentManager, "dialogColor") }
        binding.tVChoosePack.setOnClickListener { dialogPack.show(parentFragmentManager, "dialogPack") }
        binding.imVProfileAvatar.setOnClickListener { dialogChoosingAvatar.show(parentFragmentManager, "dialogAvatar") }
    }

    override fun fragmentDialog(pack: FullPackDomEntity) {
        binding.tVChoosePack.text = pack.name
        wordAdapter.setList(pack.wordArray)
        choosePackId = pack.id
    }

    override fun wordAdapterDeleteCallBack(word: WordDomEntity) {
        viewModel.deleteWord(word)
    }

    override fun wordAdapterEditCallBack(word: WordDomEntity) {
        //viewModel.up
    }

    override fun avatarChoose(id: Int) {
        globalProfileDomEntity.avatar = id
        viewModel.updateGlobalProfile(globalProfileDomEntity)
    }

    override fun colorChoose(id: Int) {
        globalProfileDomEntity.color = id
        viewModel.updateGlobalProfile(globalProfileDomEntity)
    }


}