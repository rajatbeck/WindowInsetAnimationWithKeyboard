package com.rajat.windowinset


import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsAnimation

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_test.*


class TestActivity : AppCompatActivity() {
    companion object {
        val TAG: String = TestActivity::class.java.simpleName
    }

    private var finalImeHeight: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        supportActionBar?.hide()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        listenForSpecificInset()


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            editText12.setWindowInsetsAnimationCallback(object : WindowInsetsAnimation.Callback(DISPATCH_MODE_STOP) {

                var startBottom = 0
                var endBottom = 0
                var upperBound = 0
                var lowerBound = 0

                override fun onPrepare(animation: WindowInsetsAnimation) {
                    super.onPrepare(animation)
                    startBottom = editText12.calculateBottomInWindow() // calculate the height from bottom excluding IME's
                }


                override fun onStart(
                    animation: WindowInsetsAnimation,
                    bounds: WindowInsetsAnimation.Bounds
                ): WindowInsetsAnimation.Bounds {
                    super.onStart(animation, bounds)
                    upperBound = bounds.upperBound.bottom
                    lowerBound = bounds.lowerBound.bottom
                    endBottom = finalImeHeight
                    return bounds
                }


                @SuppressLint("RestrictedApi")
                override fun onProgress(
                    p0: WindowInsets,
                    p1: MutableList<WindowInsetsAnimation>
                ): WindowInsets {
                    val newBottom = p0.getInsets(WindowInsets.Type.ime()).bottom
                    val offset = if (endBottom == upperBound) {
                        AnimationUtils.lerp(
                            0f,
                            (0 - newBottom).toFloat(),
                            AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR.getInterpolation(p1[0].interpolatedFraction)
                        )

                    } else {
                        AnimationUtils.lerp(
                            (endBottom - newBottom).toFloat(),
                            0f,
                            AnimationUtils.LINEAR_INTERPOLATOR.getInterpolation(p1[0].fraction)
                        )
                    }
                    rootView.translationY = offset
                    return p0
                }

                override fun onEnd(animation: WindowInsetsAnimation) {
                    super.onEnd(animation)

                }
            })
    }


    /**
     *  @param insetsType Log visibility and bottom height for any inset
     */
    private fun listenForSpecificInset(@WindowInsetsCompat.Type.InsetsType insetsType: Int = WindowInsetsCompat.Type.ime()) {
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { _, insets ->
            finalImeHeight = insets.getInsets(insetsType).bottom
            insets
        }
    }

    fun View.calculateBottomInWindow(additionalSpace: Int = 0): Int {
        val screenHeight = getScreenHeight()
        val loc = IntArray(2)
        getLocationOnScreen(loc)
        return screenHeight - loc[1] + additionalSpace
    }
}