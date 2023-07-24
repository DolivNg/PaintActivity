package com.vassa.paintactivity.ui.dialogs.edittext

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.vassa.paintactivity.databinding.DialogEditTextBinding

class EditTextDialog(var writer: EditTextDialogCallBack) : DialogFragment() {

    private var _binding: DialogEditTextBinding? = null
    private val binding get() = _binding!!

    private var tag = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogEditTextBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
        this.tag = tag!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bttnApplyEdit.setOnClickListener {
            if (binding.editTDialog.text.toString() != "") {
                writer.writeTextCallBack(binding.editTDialog.text.toString(),tag)
                dismiss()
            }
        }
        binding.bttnCancelEdit.setOnClickListener {
            binding.editTDialog.clearFocus()
            binding.editTDialog.isCursorVisible = false
            val imm =
                context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            dismiss()
        }

        binding.editTDialog.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                binding.editTDialog.clearFocus()
                binding.editTDialog.isCursorVisible = false
                val imm =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)

                return@setOnEditorActionListener true;
            }
            return@setOnEditorActionListener false;
        }
    }

    interface EditTextDialogCallBack {
        fun writeTextCallBack(text: String,tag: String)
    }
}