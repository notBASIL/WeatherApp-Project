package com.example.weatherapp

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.PathInterpolator
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.weatherapp.R

class TopSegmentFragment : Fragment() {

    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var sunImageView: View
    private lateinit var cloudImageView: View
    private lateinit var birdsImageView: View

    private lateinit var sunAnimator: ObjectAnimator
    private lateinit var cloudAnimator: ObjectAnimator
    private lateinit var birdsAnimator: ObjectAnimator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_top_segment, container, false)

        startButton = view.findViewById(R.id.startButton)
        stopButton = view.findViewById(R.id.stopButton)
        sunImageView = view.findViewById(R.id.sunImageView)
        cloudImageView = view.findViewById(R.id.cloudImageView)
        birdsImageView = view.findViewById(R.id.birdsImageView)

        startButton.setOnClickListener {
            startAnimations()
            startMusic()
        }

        stopButton.setOnClickListener {
            stopAnimations()
            stopMusic()
        }

        return view
    }

    private fun startAnimations() {
        // Animate sun and clouds
        val screenWidth = resources.displayMetrics.widthPixels.toFloat()
        val sunCloudPath = Path().apply {
            moveTo(-sunImageView.width.toFloat(), sunImageView.y)
            lineTo(screenWidth, sunImageView.y)
        }
        val sunCloudAnimator = ObjectAnimator.ofFloat(sunImageView, View.X, View.Y, sunCloudPath)
        sunCloudAnimator.interpolator = PathInterpolator(0.5f, 0f, 0.5f, 1f)
        sunCloudAnimator.duration = 8000
        sunCloudAnimator.repeatCount = ValueAnimator.INFINITE
        sunCloudAnimator.start()

        val cloudPath = Path().apply {
            moveTo(screenWidth, cloudImageView.y)
            lineTo(-cloudImageView.width.toFloat(), cloudImageView.y)
        }
        cloudAnimator = ObjectAnimator.ofFloat(cloudImageView, View.X, View.Y, cloudPath)
        cloudAnimator.interpolator = PathInterpolator(0.5f, 0f, 0.5f, 1f)
        cloudAnimator.duration = 8000
        cloudAnimator.repeatCount = ValueAnimator.INFINITE
        cloudAnimator.start()

        // Animate birds
        birdsAnimator = ObjectAnimator.ofFloat(
            birdsImageView, View.TRANSLATION_X,
            -birdsImageView.width.toFloat(), screenWidth
        )
        birdsAnimator.interpolator = PathInterpolator(0.5f, 0f, 0.5f, 1f)
        birdsAnimator.duration = 8000
        birdsAnimator.repeatCount = ValueAnimator.INFINITE
        birdsAnimator.start()
    }


    private fun startMusic() {
        // Start playing music
    }

    private fun stopAnimations() {
        sunAnimator.cancel()
        cloudAnimator.cancel()
        birdsAnimator.cancel()
    }

    private fun stopMusic() {
        // Stop playing music
    }
}
