package hr.algebra.android_api_manager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import hr.algebra.android_api_manager.databinding.ActivitySplashScreenBinding
import hr.algebra.android_api_manager.framework.startActivity
import hr.algebra.android_api_manager.framework.startAnimation

private const val DELAY = 4000L
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startAnimations()
        redirect()
    }

    private fun startAnimations() {
        binding.ivSplash.startAnimation(R.anim.rotate)
        binding.tvSplash.startAnimation(R.anim.blink)
    }

    private fun redirect() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity<HostActivity>()
            }, DELAY
        )
    }
}