package es.rfvl.f1

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle;
import android.os.Handler
import android.os.Looper
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast
import es.rfvl.f1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)


        val logoF1: ImageView = findViewById(R.id.imagenLogoF1);
        val textF1: TextView = findViewById(R.id.textFormula1);

        val logoAnimator = ObjectAnimator.ofFloat(logoF1, "alpha", 0f, 1f)
        logoAnimator.duration = 3000
        logoAnimator.interpolator = AccelerateDecelerateInterpolator()
        logoAnimator.startDelay = 50
        logoAnimator.start()

        val textViewAnimator = ObjectAnimator.ofFloat(textF1, "alpha", 0f, 1f)
        textViewAnimator.duration = 3000
        textViewAnimator.interpolator = AccelerateDecelerateInterpolator()
        textViewAnimator.startDelay = 50

        logoAnimator.start()
        textViewAnimator.start()

        logoAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    val intent = Intent(this@MainActivity, MainActivity2::class.java )
                    startActivity(intent)
                    finish()
                },2000)

            }
        })
    }


}