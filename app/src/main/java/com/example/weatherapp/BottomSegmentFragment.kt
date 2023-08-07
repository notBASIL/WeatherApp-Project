package com.example.weatherapp

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.weatherapp.R

class BottomSegmentFragment : Fragment() {

    private lateinit var turningWheelImageView: ImageView
    private lateinit var rotationAnimator: ObjectAnimator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_segment, container, false)

        turningWheelImageView = view.findViewById(R.id.bottomTurningWheelImageView)

        rotationAnimator = ObjectAnimator.ofFloat(turningWheelImageView, "rotation", 0f, 360f)
        rotationAnimator.duration = 3000
        rotationAnimator.interpolator = LinearInterpolator()
        rotationAnimator.repeatCount = ObjectAnimator.INFINITE
        rotationAnimator.repeatMode = ObjectAnimator.RESTART
        rotationAnimator.start()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rotationAnimator.cancel()
    }
}

