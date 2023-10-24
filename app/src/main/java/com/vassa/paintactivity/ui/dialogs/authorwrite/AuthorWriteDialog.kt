package com.vassa.paintactivity.ui.dialogs.authorwrite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import com.vassa.paintactivity.databinding.DialogAuthorWriteBinding
import com.vassa.paintactivity.databinding.LayoutSelectAvatarBinding

class AuthorWriteDialog(var callBack: AuthorWroteCallBack) : DialogFragment() {
    private var _binding: DialogAuthorWriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAuthorWriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.edTAuthorWrite.setOnClickListener {
            binding.edTAuthorWrite.isCursorVisible = true
        }
        binding.edTAuthorWrite.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                callBack.authorWrote(binding.edTAuthorWrite.text.toString())

                val imm =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                dismiss()
                return@setOnEditorActionListener true;
            }
            return@setOnEditorActionListener false;
        }
        binding.bttnAuthorWrite.setOnClickListener {
            callBack.authorWrote(binding.edTAuthorWrite.text.toString())
            dismiss()
        }

    }

    interface AuthorWroteCallBack {
        fun authorWrote(word: String)
    }
}