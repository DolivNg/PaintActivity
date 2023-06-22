package com.vassa.paintactivity.ui.fragments.rules

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vassa.paintactivity.R

class RuleFragment : Fragment() {

    companion object {
        fun newInstance() = RuleFragment()
    }

    private lateinit var viewModel: RuleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rule, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RuleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}