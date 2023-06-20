package com.vassa.paintactivity.ui.fragments.typegame

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vassa.paintactivity.R
import com.vassa.paintactivity.ui.fragments.typegame.viewmodel.TypeGameMenuViewModel

class TypeGameMenuFragment : Fragment() {

    companion object {
        fun newInstance() = TypeGameMenuFragment()
    }

    private lateinit var viewModel: TypeGameMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_type_game_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TypeGameMenuViewModel::class.java)
        // TODO: Use the ViewModel
    }

}