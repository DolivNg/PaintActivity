package com.vassa.paintactivity.ui.fragments.makepen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vassa.paintactivity.R
import com.vassa.paintactivity.ui.fragments.makepen.modelview.MakePenViewModel

class MakePenFragment : Fragment() {

    companion object {
        fun newInstance() = MakePenFragment()
    }

    private lateinit var viewModel: MakePenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_make_pen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MakePenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}