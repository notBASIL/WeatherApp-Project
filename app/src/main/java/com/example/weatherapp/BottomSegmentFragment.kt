package com.example.weatherapp

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import java.text.SimpleDateFormat
import java.util.*

class BottomSegmentFragment : Fragment() {

    private lateinit var dateTimeTextView: TextView
    private lateinit var turningWheelImageView: ImageView

    private lateinit var rotationAnimator: ObjectAnimator
    private lateinit var currentTimeHandler: Handler
    private lateinit var currentTimeRunnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_segment, container, false)

        dateTimeTextView = view.findViewById(R.id.bottomDateTimeTextView)
        turningWheelImageView = view.findViewById(R.id.bottomTurningWheelImageView)

        currentTimeHandler = Handler()
        currentTimeRunnable = object : Runnable {
            override fun run() {
                updateCurrentTime()
                currentTimeHandler.postDelayed(this, 1000)
            }
        }
        currentTimeHandler.post(currentTimeRunnable)

        rotationAnimator = ObjectAnimator.ofFloat(turningWheelImageView, "rotation", 0f, 360f)
        rotationAnimator.duration = 3000
        rotationAnimator.interpolator = LinearInterpolator()
        rotationAnimator.repeatCount = ObjectAnimator.INFINITE
        rotationAnimator.repeatMode = ObjectAnimator.RESTART
        rotationAnimator.start()

        return view
    }

    private fun updateCurrentTime() {
        val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        dateTimeTextView.text = currentTime
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rotationAnimator.cancel()
        currentTimeHandler.removeCallbacks(currentTimeRunnable)
    }
}

