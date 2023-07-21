package com.vassa.paintactivity.ui.fragments.option.dialogs.color

import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.vassa.paintactivity.databinding.LayoutSelectAvatarBinding
import com.vassa.paintactivity.ui.constants.VectorAssets

class ChoosingColorDialog(var chooser : ChooseColorCallBack) : DialogFragment() {


    private var _binding: LayoutSelectAvatarBinding? = null
    private val binding get() = _binding!!
    private var columns = 4
    private var row = VectorAssets.vectors.size / columns + 1
    private var tag = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutSelectAvatarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
        this.tag = tag!!
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

        val widthOfScreen = displayMetrics.widthPixels
        val widthOfCell = widthOfScreen / (columns + 1)

        binding.grLAvatar.apply {
            columnCount = columns
            rowCount = row
        }

        for (i in 0 until VectorAssets.colors.size) {
            val im = ImageView(context)
            im.apply {
                id = i
                maxWidth = widthOfCell
                maxHeight = widthOfCell
                layoutParams = ViewGroup.LayoutParams(widthOfCell, widthOfCell)
                setBackgroundColor(VectorAssets.colors[i])

            }
            im.setOnClickListener {
                    chooser.colorChoose(VectorAssets.colors[im.id],tag)
                    dismiss()
            }
            binding.grLAvatar.addView(im)
            binding.bttnAvatarCancel.setOnClickListener {
                dismiss()
            }
        }

    }

    interface ChooseColorCallBack {
        fun colorChoose(id : Int,tag: String)
    }
}