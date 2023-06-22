package com.vassa.paintactivity.ui.fragments.party

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vassa.paintactivity.R

class PartyGameFragment : Fragment() {

    companion object {
        fun newInstance() = PartyGameFragment()
    }

    private lateinit var viewModel: PartyGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_party_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PartyGameViewModel::class.java)
        // TODO: Use the ViewModel
    }

}