package com.vassa.paintactivity.ui.fragments.game.multiplayer.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vassa.paintactivity.data.constants.SocketConst
import com.vassa.paintactivity.data.constants.VectorAssets
import com.vassa.paintactivity.databinding.FragmentGameMultiplayerBinding
import com.vassa.paintactivity.domain.entity.socket.InfoClientDomEntity
import com.vassa.paintactivity.domain.entity.socket.RoleEntity
import com.vassa.paintactivity.domain.entity.socket.RoomOptionDomEntity
import com.vassa.paintactivity.domain.entity.socket.SuspicionDomEntity
import com.vassa.paintactivity.ui.UiConstants.Companion.ROLE_AUTHOR
import com.vassa.paintactivity.ui.UiConstants.Companion.ROLE_IMPOSTER
import com.vassa.paintactivity.ui.UiConstants.Companion.ROLE_NORMIS
import com.vassa.paintactivity.ui.dialogs.authorwrite.AuthorWriteDialog
import com.vassa.paintactivity.ui.dialogs.chat.ChatDialog
import com.vassa.paintactivity.ui.dialogs.color.ChoosingColorDialog
import com.vassa.paintactivity.ui.dialogs.turn.NewTurnDialog
import com.vassa.paintactivity.ui.dialogs.vote.VoteDialog
import com.vassa.paintactivity.ui.intent.RoomOptionIntent
import com.vassa.paintactivity.ui.view.Game
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameMultiplayerFragment : Fragment(), GameMultiplayerViewModel.GameMultiplayer,
    ChoosingColorDialog.ChooseColorCallBack, AuthorWriteDialog.AuthorWroteCallBack,
    NewTurnDialog.NewTurnCallBack, Game.GameCallBack, ChatDialog.ChatDialogCallBack, VoteDialog.VoteCallBack {

    private var dialogColorDialog = ChoosingColorDialog(this)
    private var authorWriteDialog = AuthorWriteDialog(this)
    private var newTurnDialog = NewTurnDialog(this)
    private var chatDialog = ChatDialog(this)
    private var voteDialog = VoteDialog(this)

    private var _binding: FragmentGameMultiplayerBinding? = null
    private val viewModel: GameMultiplayerViewModel by viewModel<GameMultiplayerViewModel>()
    private val binding get() = _binding!!
    private var word = ""
    private var testPlayerId = 0

    private var countDownTimer: CountDownTimer? = null
    private var totalTimeMillis = 0L // 10 minutes

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameMultiplayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.game.callGameBack = this
        val client = arguments?.getParcelable<RoomOptionIntent>(SocketConst.CLIENT_LOBBY_KEY)
        val admin = arguments?.getParcelable<RoomOptionIntent>(SocketConst.ADMIN_LOBBY_KEY)

        //TODO give data about all players 1
        getListPlayer() // отримати з сервера список гравців
        //TODO give data about game 2
        getOptionGame() // отримати правила з сервера
        initRules() // ініцілізація правил
        initRoles()//3 in admin // назначення ролей

        if (client != null) {

        }

        if (admin != null) {

        }
        viewModel.massageList.observe(viewLifecycleOwner){
            chatDialog.setListMassages(it)
        }

        viewModel.wordList.observe(viewLifecycleOwner) {
            initQuest()//спитати в автора яка задача
        }
        viewModel.playerList.observe(viewLifecycleOwner){
            var list = ArrayList<InfoClientDomEntity>(it)
            //remove self
            voteDialog.setListClient(it)
        }
        viewModel.suspicion.observe(viewLifecycleOwner){
            viewModel.sendNewSusp()
            for (v in viewModel.playerList.value!!)
                if (viewModel.suspicion.value!!.suspKey == v.key) {
                    voteDialog.setClientSelected(v)
                    break
                }
        }

        initToolBar()
    }

    private fun startGame() {
        testPlayerId =
            Random(10).nextInt(viewModel.wordList.value!!.size - 1) //вибыр першого гравця
        newTurn()
    }

    private fun getListPlayer() {
        //test local debug
        val arrayList = ArrayList<InfoClientDomEntity>()
        for (i in 1..6)
            arrayList.add(
                InfoClientDomEntity(
                    Random(10).nextInt(6),
                    Random(10).nextInt(VectorAssets.colors.size),
                    "v + $i", "aaaaa", if (i == 1) {
                        1
                    } else {
                        2
                    }, 0, Random(System.currentTimeMillis() + i).nextInt(10000, 99999)
                )
            )
        viewModel.playerList.value = arrayList
    }

    private fun getOptionGame() {
        viewModel.roomOption.value = RoomOptionDomEntity(6, 1, true, 1, 1, 50, true, 30, "aaaa")
    }


    /**
     * init tool bar
     */
    private fun initToolBar() {
        binding.imVPencil.setOnClickListener {
            Log.d("vasa", "pencil")
            binding.game.choosePencil()
        }
        binding.imVChColor.setOnClickListener { dialogColorDialog.show(parentFragmentManager, "Color") }
        binding.imVEraser.setOnClickListener {
            Log.d("vasa", "stertcka")
            binding.game.chooseEraser()
        }

        binding.imBttnChat.setOnClickListener {
            chatDialog.show(parentFragmentManager, "Color")
        }
        binding.imBttnTurn.setOnClickListener {
            endTurn()

            if (countDownTimer != null)
                countDownTimer!!.cancel()
            newTurn()
        }
        binding.imBttnWarning.setOnClickListener {
            voteDialog.show(parentFragmentManager, "Color")
        }

    }

    /**
     *  init Game Rules
     * */
    private fun initRules() {

        if (viewModel.roomOption.value!!.timer) {
            totalTimeMillis = viewModel.roomOption.value!!.timeSek * 1000L//настройка таймера

            binding.progBarTimer.max = viewModel.roomOption.value!!.timeSek
            binding.progBarTimer.progress = viewModel.roomOption.value!!.timeSek
        }
        binding.progBarInk.max = 100
        viewModel.loadListWord(
            viewModel.roomOption.value!!.pack_id,
            viewModel.roomOption.value!!.lang
        )
        binding.game.initRules()
    }

    /**
     *  initialization  Roles for players
     */
    private fun initRoles() {
        val list = ArrayList<RoleEntity>()

        val array: ArrayList<InfoClientDomEntity> =
            viewModel.playerList.value!!.clone() as ArrayList<InfoClientDomEntity>

        if (viewModel.roomOption.value!!.author) {
            val rand = Random(System.currentTimeMillis()).nextInt(array.size)
            list.add(RoleEntity(array[rand].key, ROLE_AUTHOR))
            array.remove(array[rand])
        }

        for (n in 1..viewModel.roomOption.value!!.imposter) {
            val rand = Random(System.currentTimeMillis()).nextInt(array.size)
            list.add(RoleEntity(array[rand].key, ROLE_IMPOSTER))
            array.remove(array[rand])
        }

        for (n in array) {
            list.add(RoleEntity(n.key, ROLE_NORMIS))
        }

        list.forEach { role ->
            viewModel.playerList.value!!.forEach {
                if (it.key == role.key) it.role = role.role
            }
        }
        Log.d("vasa", viewModel.playerList.value!!.toString())
        //TODO send role's list to server 3
    }

    private fun startCountDownTimer() {
        countDownTimer = object : CountDownTimer(totalTimeMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //val minutes = millisUntilFinished / (1000 * 60)
                val seconds = (millisUntilFinished / 1000) % 60
                binding.progBarTimer.progress = seconds.toInt()
            }

            override fun onFinish() {
                endTurn()
                newTurn()
            }
        }.start()
    }

    private fun initQuest() {
        if (!viewModel.roomOption.value!!.author) {
            //TODO load word's list 4
            word =
                viewModel.wordList.value!![Random(10).nextInt(viewModel.wordList.value!!.size - 1)].word
            binding.tVTask.text = word
            startGame()
            //TODO choose word 5
            //TODO Send Word 6
        } else {
            authorMustWrite()
        }
    }

    private fun authorMustWrite() {
        authorWriteDialog.show(parentFragmentManager, "Color")
        //TODO send author must write word 4
    }


    private fun newTurn() {
        testPlayerId += 1
        if (testPlayerId >= viewModel.playerList.value!!.size)
            testPlayerId = 0
        binding.progBarInk.progress = 100
        if (viewModel.playerList.value!![testPlayerId].role == ROLE_IMPOSTER) {
            binding.tVTask.visibility = View.GONE
            binding.imBttnImpostAnsw.visibility = View.VISIBLE
        } else {
            binding.tVTask.visibility = View.VISIBLE
            binding.imBttnImpostAnsw.visibility = View.GONE
        }
        newTurnDialog.show(parentFragmentManager, viewModel.playerList.value!![testPlayerId].name)
    }


    fun endTurn() {
        viewModel.tempGameData.value!!.touch = false
        binding.game.endTurn()
        //viewModel.tempGameData.value!!.ink
    }

    override fun onResume() {
        super.onResume()
        //binding.game.
    }

    override fun onPause() {
        super.onPause()
        binding.game.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun yourTurn() {
        //TODO begin turn client
        newTurn()
    }

    override fun getPoint() {
        //TODO set new point to draw
    }

    override fun authorWriteQuest() {
        //TODO show
    }

    override fun colorChoose(id: Int, tag: String) {
        binding.game.changeColor(id)
    }

    override fun authorWrote(word: String) {
        this.word = word
        binding.tVTask.text = this.word
        startGame()
        //TODO SEND QUESTION
    }

    /**
     * approve turn
     */
    override fun newTurnCallBack() {
        if (viewModel.roomOption.value!!.timer) {
            binding.progBarTimer.max = viewModel.roomOption.value!!.timeSek
            binding.progBarTimer.progress = viewModel.roomOption.value!!.timeSek
            startCountDownTimer()
        }

        viewModel.tempGameData.value!!.touch = true
        binding.game.newTurn()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }

    override fun timerUsed(sec: Int) {
        TODO("Not yet implemented")
    }

    override fun inkUsed(count: Int) {

        binding.progBarInk.progress = (100 -  count / (viewModel.roomOption.value!!.inkCount/100.0)).toInt()
        if (viewModel.roomOption.value!!.inkCount < count) {
            binding.game.inkLose()
        }
    }

    override fun chatCallBack(str: String) {
        viewModel.sendMassage(str, viewModel.playerList.value!![testPlayerId])
    }

    override fun suspicRemoved(susp: SuspicionDomEntity) {
        TODO("Not yet implemented")
    }

    override fun suspicConfirm(susp: SuspicionDomEntity) {
        viewModel.sendRemoveSusp()
        viewModel.suspicion.value = susp
    }

    override fun votedSuspicion() {
        TODO("Not yet implemented")
    }
}