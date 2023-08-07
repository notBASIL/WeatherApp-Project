package com.example.weatherapp

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Path
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.PathInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import java.text.SimpleDateFormat
import java.util.*

class TopSegmentFragment : Fragment() {

    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var sunImageView: View
    private lateinit var cloudImageView: View
    private lateinit var birdsImageView: View

    private lateinit var sunAnimator: ObjectAnimator
    private lateinit var cloudAnimator: ObjectAnimator
    private lateinit var birdsAnimator: ObjectAnimator
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var currentTimeHandler: Handler
    private lateinit var currentTimeRunnable: Runnable

    private lateinit var dateTimeTextView: TextView

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

        currentTimeHandler = Handler()
        currentTimeRunnable = object : Runnable {
            override fun run() {
                updateCurrentTime()
                currentTimeHandler.postDelayed(this, 1000)
            }
        }
        currentTimeHandler.post(currentTimeRunnable)

        return view
    }

    private fun startAnimations() {
        // Animation code
        // ...
    }

    private fun startMusic() {
        // Start playing music
        // ...
    }

    private fun stopAnimations() {
        // Stop animation
        // ...
    }

    private fun stopMusic() {
        // Stop music
        // ...
    }

    private fun updateCurrentTime() {
        val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        dateTimeTextView.text = currentTime
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentTimeHandler.removeCallbacks(currentTimeRunnable)
    }
}

