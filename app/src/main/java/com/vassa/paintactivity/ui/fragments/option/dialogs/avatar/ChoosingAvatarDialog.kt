package com.vassa.paintactivity.ui.fragments.option.dialogs.avatar

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.vassa.paintactivity.databinding.DialogListTextBinding
import com.vassa.paintactivity.databinding.LayoutSelectAvatarBinding
import com.vassa.paintactivity.domain.entity.avatar.AvatarDomEntity
import com.vassa.paintactivity.ui.constants.VectorAssets

class ChoosingAvatarDialog(val chooser : ChooseAvatarCallBack) : DialogFragment() {

    private var _binding: LayoutSelectAvatarBinding? = null
    private val binding get() = _binding!!
    private var columns = 4
    private var row = VectorAssets.vectors.size / columns + 1
    private var dataAvatar = ArrayList<AvatarDomEntity>()
    private var tag = ""

    //TODO TEST
    init {
        for (i in 0 until VectorAssets.vectors.size)
            dataAvatar.add(AvatarDomEntity(i,i<5))
    }

    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
        this.tag = tag!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutSelectAvatarBinding.inflate(layoutInflater)
        return binding.root
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

        for (i in 0 until VectorAssets.vectors.size) {
            val im = ImageView(context)
            im.apply {
                id = i
                maxWidth = widthOfCell
                maxHeight = widthOfCell
                layoutParams = ViewGroup.LayoutParams(widthOfCell, widthOfCell)
                if (i < dataAvatar.size)
                    if (dataAvatar[i].open)
                        setImageResource(VectorAssets.vectors[i])
                    else
                        setImageResource(VectorAssets.close)
            }
            im.setOnClickListener {
                if (dataAvatar[im.id].open){
                    chooser.avatarChoose(im.id,tag)
                    dismiss()
                }
            }
            binding.grLAvatar.addView(im)
            binding.bttnAvatarCancel.setOnClickListener {
                dismiss()
            }
        }

    }

    interface ChooseAvatarCallBack {
        fun avatarChoose(id : Int,tag : String)

    }
}