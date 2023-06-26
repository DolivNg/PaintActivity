package com.vassa.paintactivity.ui.fragments.local

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vassa.paintactivity.R

class LocalGameFragment : Fragment() {

    companion object {
        fun newInstance() = LocalGameFragment()
    }

    private lateinit var viewModel: LocalGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_local_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LocalGameViewModel::class.java)
        // TODO: Use the ViewModel
    }

}