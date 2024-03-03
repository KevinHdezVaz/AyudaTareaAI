package com.hevaz.quizK.ayudaia

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.hevaz.quizK.R
import com.hevaz.quizK.activity.MainActivity

class SplasAYUDA : AppCompatActivity() {
    private lateinit var animationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splas_ayuda)

        animationView = findViewById(R.id.animation_view)
        animationView.addAnimatorListener(object : Animator.AnimatorListener {

            override fun onAnimationStart(p0: Animator) {

            }

            override fun onAnimationEnd(p0: Animator) {
                // Se ejecutará después de que termine la animación
                val intent = Intent(this@SplasAYUDA, MainActivity::class.java)
                startActivity(intent)
                finish() // Finalizar la actividad de splash
            }

            override fun onAnimationCancel(p0: Animator) {

            }

            override fun onAnimationRepeat(p0: Animator) {

            }
        })
    }
}