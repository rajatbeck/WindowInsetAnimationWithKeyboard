package com.rajat.windowinset

import android.graphics.Insets
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.view.WindowMetrics
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.getScreenHeight():Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
        val insets: Insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        windowMetrics.bounds.height() - (insets.top + insets.bottom)
    } else {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        displayMetrics.heightPixels
    }
}

fun AppCompatActivity.getTopAndBottomInset():Int{
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
        val insets: Insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        insets.top + insets.bottom
    }else{
        return 0
    }
}

fun Float.remap(from1:Float,to1:Float,from2:Float,to2:Float):Float
        = ((this - from1) / (to1 - from1) * (to2 - from2) + from2)