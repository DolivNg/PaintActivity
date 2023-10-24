package com.vassa.paintactivity.ui.dialogs.chat

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vassa.paintactivity.databinding.DialogChatBinding
import com.vassa.paintactivity.domain.entity.massage.MassageChatDomEntity
import com.vassa.paintactivity.ui.dialogs.chat.adapter.ChatRecycleAdapter

class ChatDialog(var callBack : ChatDialogCallBack ) : DialogFragment() {

    private var _binding: DialogChatBinding? = null
    private val binding get() = _binding!!

    private val adapter : ChatRecycleAdapter = ChatRecycleAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogChatBinding.inflate(layoutInflater)
        return binding.root
    }

    fun setListMassages(arrayList: ArrayList<MassageChatDomEntity>){
        adapter.setList(arrayList)
        //binding.recyclerView.adapter = adapter
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        binding.editTextChatDialog.setOnClickListener {
            binding.editTextChatDialog.isCursorVisible = true
        }
        binding.imBttnSend.setOnClickListener {
            callBack.chatCallBack(binding.editTextChatDialog.text.toString())
            val lastPosition = adapter.itemCount - 1
            if (lastPosition >= 0) {
                binding.recyclerView.scrollToPosition(lastPosition)
            }
            binding.editTextChatDialog.setText("")
        }
        binding.editTextChatDialog.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                callBack.chatCallBack(binding.editTextChatDialog.text.toString())
                binding.editTextChatDialog.setText("")
                val lastPosition = adapter.itemCount - 1
                if (lastPosition >= 0) {
                    binding.recyclerView.scrollToPosition(lastPosition)
                }
                val imm =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)

                return@setOnEditorActionListener true;
            }
            return@setOnEditorActionListener false;
        }
    }


    interface ChatDialogCallBack{
        fun chatCallBack(str :String)
    }
}