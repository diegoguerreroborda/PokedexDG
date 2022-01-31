package com.dhgb.pokeapidg.ui.component

import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar

class ProgressBarAnimation(private val progressBar: ProgressBar, private val from: Int, private val to: Int) : Animation() {
    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        val value = from + (to - from) * interpolatedTime
        progressBar.progress = value.toInt()
        this.duration = 1000
    }
}