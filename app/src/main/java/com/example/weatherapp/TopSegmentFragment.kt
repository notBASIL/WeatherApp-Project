package com.example.weatherapp

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.res.Resources
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.weatherapp.R

class TopSegmentFragment : Fragment() {

    private lateinit var startButton: Button
    private lateinit var stopButton: Button

    private lateinit var backgroundColorAnimator: ObjectAnimator
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_top_segment, container, false)

        startButton = view.findViewById(R.id.startButton)
        stopButton = view.findViewById(R.id.stopButton)

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
        val startColor = Resources.getSystem().getColor(android.R.color.holo_red_light, null)
        val endColor = Resources.getSystem().getColor(android.R.color.holo_blue_light, null)

        backgroundColorAnimator = ObjectAnimator.ofInt(
            view?.findViewById(R.id.topLayout), // Replace with the actual ID of your fragment's root layout
            "backgroundColor",
            startColor, endColor
        )
        backgroundColorAnimator.setEvaluator(ArgbEvaluator())
        backgroundColorAnimator.duration = 5000
        backgroundColorAnimator.start()
    }


    private fun startMusic() {
        mediaPlayer = MediaPlayer.create(context, R.raw.spring_song)
        mediaPlayer.start()
    }

    private fun stopAnimations() {
        backgroundColorAnimator.cancel()
    }

    private fun stopMusic() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}

