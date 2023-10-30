package com.vassa.paintactivity.ui.dialogs.turn

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.vassa.paintactivity.databinding.DialogAuthorWriteBinding
import com.vassa.paintactivity.databinding.DialogNewTurnBinding

class NewTurnDialog(private val callBack: NewTurnCallBack) : DialogFragment() {
    private var locTag = ""
    private var _binding : DialogNewTurnBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogNewTurnBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bttnNewTurnOkey.setOnClickListener { dismiss() }
        binding.tVPlayerNameTurn.text = locTag
    }
    

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        callBack.newTurnCallBack()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
        locTag = tag!!
       // binding.tVPlayerNameTurn.text = locTag
    }

    interface NewTurnCallBack {
        fun newTurnCallBack()
    }
}