package com.cavista.sample.presentation

import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.cavista.sample.R
import com.cavista.sample.navigation.Navigation
import com.cavista.sample.presentation.view.SearchActivity

private const val SPLASH_SCREEN_TIMEOUT = 3000L

class SplashActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)
        startSearchListActivity()
    }


    private fun startSearchListActivity() {
        Handler().postDelayed({
            Navigation.navigateScreen(this, SearchActivity::class.java, true)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, SPLASH_SCREEN_TIMEOUT)
    }
}
