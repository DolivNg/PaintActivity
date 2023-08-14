package com.vassa.paintactivity.ui.fragments.game.multiplayer

import android.annotation.SuppressLint
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vassa.paintactivity.databinding.FragmentGameMultiplayerBinding
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.URISyntaxException


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameMultiplayerFragment : Fragment() {

    private var _binding: FragmentGameMultiplayerBinding? = null

    private val viewModel : GameMultiplayerViewModel by viewModel<GameMultiplayerViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGameMultiplayerBinding.inflate(inflater, container, false)
        return binding.root

    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button3.setOnClickListener {
            //viewModel.connect()
            //mSocket.connect()
        }
        binding.button4.setOnClickListener {
            //viewModel.send()
        }
        binding.button2.setOnClickListener {
            //viewModel.disconnect()
        }

        binding.game.setOnTouchListener { v, event ->
            val list = ArrayList<PointF>(10)
            // событие
            val actionMask = event.actionMasked

            val pointerIndex = event.actionIndex
            // число касаний
            val pointerCount = event.pointerCount
            when (actionMask) {

                MotionEvent.ACTION_DOWN -> {
                    for ( i in 0 until pointerCount){
                        list.add(PointF(event.getX(i),event.getY(i)))
                    }
                    binding.game.multiTouch(list,MotionEvent.ACTION_DOWN)
                } // нажатие

                MotionEvent.ACTION_MOVE -> {
                    for ( i in 0 until pointerCount){
                        list.add(PointF(event.getX(i),event.getY(i)))
                    }
                    binding.game.multiTouch(list,MotionEvent.ACTION_MOVE)
                } // движение

                MotionEvent.ACTION_UP -> {
                    for ( i in 0 until pointerCount){
                        list.add(PointF(event.getX(i),event.getY(i)))
                    }
                    binding.game.multiTouch(list,MotionEvent.ACTION_UP )
                } // отпускание

                MotionEvent.ACTION_CANCEL -> {
                    for ( i in 0 until pointerCount){
                        list.add(PointF(event.getX(i),event.getY(i)))
                    }
                    binding.game.multiTouch(list,MotionEvent.ACTION_CANCEL)
                }
            }
            true;
        }
    }

    override fun onResume() {
        super.onResume()
        //binding.game.
    }

    override fun onPause() {
        super.onPause()
        binding.game.pause()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}