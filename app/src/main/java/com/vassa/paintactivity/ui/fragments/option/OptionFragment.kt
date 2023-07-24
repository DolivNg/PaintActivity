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
import com.vassa.paintactivity.domain.entity.pack.PackDomEntity
import com.vassa.paintactivity.domain.entity.pack.WordDomEntity
import com.vassa.paintactivity.domain.entity.profile.GlobalProfileDomEntity
import com.vassa.paintactivity.domain.entity.profile.LocalProfileDomEntity
import com.vassa.paintactivity.ui.constants.VectorAssets
import com.vassa.paintactivity.ui.dialogs.edittext.EditTextDialog
import com.vassa.paintactivity.ui.dialogs.color.ChoosingColorDialog
import com.vassa.paintactivity.ui.dialogs.avatar.ChoosingAvatarDialog
import com.vassa.paintactivity.ui.dialogs.localprofile.LocalProfileDialog
import com.vassa.paintactivity.ui.dialogs.pack.PackListDialog
import com.vassa.paintactivity.ui.fragments.option.adapter.WordAdapter
import com.vassa.paintactivity.ui.fragments.option.modelview.OptionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Date
import kotlin.random.Random


class OptionFragment : Fragment(), PackListDialog.PackFragmentDialogCallBack,
    WordAdapter.WordAdapterCallBack, ChoosingAvatarDialog.ChooseAvatarCallBack,
    ChoosingColorDialog.ChooseColorCallBack, LocalProfileDialog.LocalFragmentDialogCallBack,
    EditTextDialog.EditTextDialogCallBack {

    private var _binding: FragmentOptionBinding? = null
    private val binding get() = _binding!!

    private var wordAdapter = WordAdapter(this)
    private var choosePackId = -1
    private var chooseLocalProf = -1

    private val viewModel: OptionViewModel by viewModel<OptionViewModel>()


    private lateinit var packListLoadDialog: PackListDialog
    private lateinit var choosingAvatarDialog: ChoosingAvatarDialog
    private lateinit var colorDialog: ChoosingColorDialog
    private lateinit var editTextDialog: EditTextDialog
    private lateinit var localProfileListLoadDialog: LocalProfileDialog

    private lateinit var packFull: FullPackDomEntity
    private var localProfileChoose: LocalProfileDomEntity = LocalProfileDomEntity(0, "", 0, 0, 0, 0)
    private lateinit var globalProfileDomEntity: GlobalProfileDomEntity

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


    private fun localProfile() {
        //Dialog Init
        localProfileListLoadDialog = LocalProfileDialog(this)
        //
        editTextDialog = EditTextDialog(this)
        //Lister Keyboard edTLocalProfileName
        binding.edTLocalProfileName.setOnClickListener {
            binding.edTLocalProfileName.isCursorVisible = true
        }
        binding.edTLocalProfileName.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                localProfileChoose.name = binding.edTLocalProfileName.text.toString()
                viewModel.updateLocalProfile(localProfileChoose)
                binding.edTLocalProfileName.clearFocus()
                binding.edTLocalProfileName.isCursorVisible = false
                val imm =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                return@setOnEditorActionListener true;
            }
            return@setOnEditorActionListener false;
        }

        binding.imVLocalProfile.setOnClickListener {
            choosingAvatarDialog.show(
                parentFragmentManager,
                "local"
            )
        }

        binding.imVLocalProfileColor.setOnClickListener {
            colorDialog.show(
                parentFragmentManager,
                "local"
            )
        }
        //
        binding.bttnLoadProf.setOnClickListener {
            localProfileListLoadDialog.show(
                parentFragmentManager,
                "dialogLocal"
            )
        }
        //
        binding.bttnAddProf.setOnClickListener {
            editTextDialog.show(
                parentFragmentManager,
                "localProfile"
            )
        }

        binding.bttnDeleteProf.setOnClickListener {
            chooseLocalProf = -1
            viewModel.deleteLocalProfile(localProfileChoose)
        }
    }

    private fun globalProfile() {
        //Listener Keyboard edTGlobalProfileName
        binding.edTGlobalProfileName.setOnClickListener {
            binding.edTGlobalProfileName.isCursorVisible = true
        }
        binding.edTGlobalProfileName.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (binding.edTGlobalProfileName.text.toString() != "") {
                } else
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
        binding.imVGlobalProfileColor.setOnClickListener {
            colorDialog.show(
                parentFragmentManager,
                "global"
            )
        }

        binding.imVProfileAvatar.setOnClickListener {
            choosingAvatarDialog.show(
                parentFragmentManager,
                "global"
            )
        }

    }

    private fun packActions() {
        //viewModel Action
        viewModel.loadFullPack()
        //Pack list dialog window
        packListLoadDialog = PackListDialog(this)
        //
        binding.tVChoosePack.setOnClickListener {
            packListLoadDialog.show(
                parentFragmentManager,
                "dialogPack"
            )
        }
        //
        binding.bttnDeletePack.setOnClickListener {
            if (packFull.standard == 0)
                viewModel.deletePack(PackDomEntity(packFull.id,packFull.name,packFull.version,packFull.standard))
        }

        binding.bttnNewPack.setOnClickListener {
            editTextDialog.show(parentFragmentManager, "pack")
        }

        binding.bttnAddWord.setOnClickListener {
            editTextDialog.show(parentFragmentManager, "word")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Avatar dialog window
        choosingAvatarDialog = ChoosingAvatarDialog(this)
        //choosing color
        colorDialog = ChoosingColorDialog(this)

        packActions()
        localProfile()
        globalProfile()

        //Init RecycleView
        binding.recycleVPackWord.layoutManager = LinearLayoutManager(context)
        binding.recycleVPackWord.adapter = wordAdapter

        viewModel.loadGlobalProfile()
        viewModel.loadLocalProfile()

        //Observe  GlobalProfile
        viewModel.getGlobalProfile().observe(viewLifecycleOwner) {
            globalProfileDomEntity = it
            binding.edTGlobalProfileName.setText(it.name.subSequence(0, it.name.length))
            binding.imVProfileAvatar.setBackgroundColor(it.color)
            binding.imVGlobalProfileColor.setBackgroundColor(it.color)
            binding.imVProfileAvatar.setImageResource(VectorAssets.vectors[it.avatar])
        }

        //Observe LocalProfile
        viewModel.getLocalProfileList().observe(viewLifecycleOwner) { list ->
            if (list.size == 0) {
                binding.imVLocalProfile.setImageResource(VectorAssets.close)
            } else {
                if (chooseLocalProf == -1) {
                    localProfileChoose = list[0]
                    chooseLocalProf = list[0].id
                } else
                    if (chooseLocalProf == -2) {
                        localProfileChoose = list[list.size - 1]
                        chooseLocalProf = list[list.size - 1].id
                    } else
                        list.forEach {
                            if (chooseLocalProf == it.id) {
                                localProfileChoose = it
                            }
                        }
                localProfileListLoadDialog.changeList(list)
                binding.edTLocalProfileName.setText(
                    localProfileChoose.name.subSequence(
                        0,
                        localProfileChoose.name.length
                    )
                )
                binding.imVLocalProfile.setBackgroundColor(localProfileChoose.color)
                binding.imVLocalProfileColor.setBackgroundColor(localProfileChoose.color)
                binding.imVLocalProfile.setImageResource(VectorAssets.vectors[localProfileChoose.avatar])
            }
        }

        //Observe FullPack
        viewModel.getFullPackListProfile().observe(viewLifecycleOwner) { list ->
            packListLoadDialog.changeList(list)
            if (choosePackId == -2) {
                packFull = list[list.size-1]
                binding.tVChoosePack.text = packFull.name
                wordAdapter.setList(packFull.wordArray)
            } else
            if (choosePackId == -1) {
                packFull = list[0]
                binding.tVChoosePack.text = packFull.name
                wordAdapter.setList(packFull.wordArray)
            } else
                list.forEach {
                    if (choosePackId == it.id) {
                        packFull = it
                        binding.tVChoosePack.text = it.name
                        wordAdapter.setList(it.wordArray)
                    }
                }
        }
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

    override fun avatarChoose(id: Int, tag: String) {
        if (tag == "global") {
            globalProfileDomEntity.avatar = id
            viewModel.updateGlobalProfile(globalProfileDomEntity)
        }
        if (tag == "local") {
            localProfileChoose.avatar = id
            viewModel.updateLocalProfile(localProfileChoose)
        }

    }

    override fun colorChoose(id: Int, tag: String) {
        if (tag == "global") {
            globalProfileDomEntity.color = id
            viewModel.updateGlobalProfile(globalProfileDomEntity)
        }
        if (tag == "local") {
            localProfileChoose.color = id
            viewModel.updateLocalProfile(localProfileChoose)
        }
    }

    override fun localFragmentDialog(local: LocalProfileDomEntity) {
        localProfileChoose = local
        chooseLocalProf = localProfileChoose.id
        binding.edTLocalProfileName.setText(
            localProfileChoose.name.subSequence(
                0,
                localProfileChoose.name.length
            )
        )
        binding.imVLocalProfile.setBackgroundColor(localProfileChoose.color)
        binding.imVLocalProfileColor.setBackgroundColor(localProfileChoose.color)
        binding.imVLocalProfile.setImageResource(VectorAssets.vectors[localProfileChoose.avatar])
    }

    override fun writeTextCallBack(text: String, tag: String) {
        if (tag == "localProfile") {
            chooseLocalProf = -2
            viewModel.insertLocalProfile(
                LocalProfileDomEntity(
                    0,
                    text,
                    Random(Date().time).nextInt(VectorAssets.vectors.size),
                    VectorAssets.colors[Random(Date().time).nextInt(VectorAssets.vectors.size)],
                    0,
                    0
                )
            )
        }

        if (tag == "pack") {
            choosePackId = -2
            viewModel.insertPack(PackDomEntity(0, text, 1, 0))
        }

        if (tag == "word") {
            viewModel.insertWord(WordDomEntity(0, packFull.id,3,text))
        }
    }


}