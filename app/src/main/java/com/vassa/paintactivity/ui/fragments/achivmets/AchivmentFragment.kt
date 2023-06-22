package com.vassa.paintactivity.ui.fragments.achivmets

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vassa.paintactivity.R

class AchivmentFragment : Fragment() {

    companion object {
        fun newInstance() = AchivmentFragment()
    }

    private lateinit var viewModel: AchivmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_achivment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AchivmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}