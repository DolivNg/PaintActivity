package com.vassa.paintactivity.ui.fragments.option

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vassa.paintactivity.R
import com.vassa.paintactivity.ui.fragments.option.modelview.OptionViewModel

class OptionFragment : Fragment() {

    companion object {
        fun newInstance() = OptionFragment()
    }

    private lateinit var viewModel: OptionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_option, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OptionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}