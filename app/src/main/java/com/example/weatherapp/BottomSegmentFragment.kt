package com.example.weatherapp

import android.animation.ObjectAnimator
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.view.animation.PathInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BottomSegmentFragment : Fragment() {

    private lateinit var rotationAnimator: ObjectAnimator

    private lateinit var dateTimeTextView: TextView

    private lateinit var turningWheelImageView: ImageView
    private lateinit var currentImageView: ImageView
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var colorAnimator: ObjectAnimator
    private lateinit var colorHandler: Handler
    private lateinit var colorRunnable: Runnable

    private val colors = listOf(
        Color.parseColor("#FF4500"), // OrangeRed
        Color.parseColor("#8FBC8F"), // DarkSeaGreen
        Color.parseColor("#FFFF00"), // Yellow
        Color.WHITE
    )

    private val images = listOf(
        R.drawable.spring,
        R.drawable.summer,
        R.drawable.autumn,
        R.drawable.winter
    )

    private val musicFiles = listOf(
        R.raw.spring_song,
        R.raw.summer_song,
        R.raw.autumn_song,
        R.raw.winter_song
    )

    private var colorIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_segment, container, false)

        turningWheelImageView = view.findViewById(R.id.bottomTurningWheelImageView)
        currentImageView = view.findViewById(R.id.bottomCurrentImageView)
        dateTimeTextView = view.findViewById(R.id.bottomDateTimeTextView)

        //turning wheel rotation animation
        rotationAnimator = ObjectAnimator.ofFloat(turningWheelImageView, "rotation", 0f, 360f)
        rotationAnimator.duration = 3000
        rotationAnimator.interpolator = LinearInterpolator()
        rotationAnimator.repeatCount = ObjectAnimator.INFINITE
        rotationAnimator.repeatMode = ObjectAnimator.RESTART
        rotationAnimator.start()

        mediaPlayer = MediaPlayer.create(requireContext(), musicFiles[colorIndex])

        colorHandler = Handler()
        colorRunnable = object : Runnable {
            override fun run() {
                changeBackgroundColorAndImage()
                mediaPlayer.release()
                mediaPlayer = MediaPlayer.create(requireContext(), musicFiles[colorIndex])
                mediaPlayer.start()
                colorHandler.postDelayed(this, 15000)
            }
        }
        updateTime()
        return view
    }

    override fun onResume() {
        super.onResume()
        colorHandler.post(colorRunnable)
    }

    override fun onPause() {
        super.onPause()
        colorHandler.removeCallbacks(colorRunnable)
    }

    private fun changeBackgroundColorAndImage() {
        colorIndex = (colorIndex + 1) % colors.size
        val color = colors[colorIndex]
        val image = images[colorIndex]
        currentImageView.setImageResource(image)

        val startColor = requireContext().getColor(R.color.white)
        val endColor = color
        colorAnimator = ObjectAnimator.ofArgb(currentImageView, "backgroundColor", startColor, endColor)
        colorAnimator.interpolator = PathInterpolator(0.5f, 0f, 0.5f, 1f)
        colorAnimator.duration = 3000
        colorAnimator.start()
    }

    private fun updateTime() {
        val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        dateTimeTextView.text = currentTime

        Handler().postDelayed({ updateTime() }, 1000)
    }
}
