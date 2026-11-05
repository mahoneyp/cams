package com.vladpen

import android.animation.ValueAnimator
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.view.WindowCompat.enableEdgeToEdge
import androidx.core.view.WindowCompat.getInsetsController
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.vladpen.cams.MainApp.Companion.context
import com.vladpen.cams.R


object Effects {
    private var animation: Animation? = null
    private var handler: Handler? = null

    fun edgeToEdge(window: Window) {
        enableEdgeToEdge(window)  // Android 14-
        val windowInsetsController = getInsetsController(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = false
        windowInsetsController.isAppearanceLightNavigationBars = false
    }

    fun delayedFadeOut(views: Array<View>, window: Window? = null, delay: Long = 3000) {
        handler = Handler(Looper.getMainLooper())
        handler?.postDelayed({
            fadeOut(views, window)
        }, delay)
    }

    fun fadeOut(views: Array<View>, window: Window? = null, duration: Long = 500) {
        animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)
        animation?.duration = duration
        animation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(a: Animation) {
                for (view in views)
                    view.visibility = View.GONE
            }
            override fun onAnimationStart(a: Animation) {
                if (window != null) {
                    hideSystemBars(window)
                }
            }
            override fun onAnimationRepeat(a: Animation) {
            }
        })
        for (view in views)
            view.startAnimation(animation)
    }

    fun showSystemBars(window: Window) {
        val controller = getInsetsController(window, window.decorView)
        controller.show(WindowInsetsCompat.Type.systemBars())
        if (android.os.Build.VERSION.SDK_INT < 30) {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }
    }

    fun hideSystemBars(window: Window) {
        val controller = getInsetsController(window, window.decorView)
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        controller.hide(WindowInsetsCompat.Type.systemBars())
        if (android.os.Build.VERSION.SDK_INT < 30) {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }

    fun toggle(view: LinearLayout, duration: Long = 300) {
        // show/hide given view with slideDown/slideUp effect
        var from = 0
        var to = 0
        if (view.height > 0)
            from = view.height
        else
            to = getHeight(view)

        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, from)

        val valueAnimator = ValueAnimator.ofFloat(from.toFloat(), to.toFloat())
        valueAnimator.addUpdateListener {
            params.height = (it.animatedValue as Float).toInt()
            view.layoutParams = params
        }
        valueAnimator.duration = duration
        valueAnimator.start()
    }

    private fun getHeight(view: View): Int {
        // calculate total height including hidden views
        view.measure(
            View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        return view.measuredHeight
    }

    fun dimmer(view: ViewGroup) {
        val overlay = RelativeLayout(context)
        overlay.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        overlay.setBackgroundColor(context.getColor(R.color.overlay_foreground))
        view.addView(overlay)
    }

    fun cancel() {
        if (handler != null) {
            handler?.removeCallbacksAndMessages(null)
            handler = null
        }
        if (animation != null) {
            animation?.cancel()
            animation?.setAnimationListener(null)
            animation = null
        }
    }
}