package com.example.searchanimation

import android.animation.AnimatorListenerAdapter




import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var searchBox: View
    private lateinit var backButton: ImageButton
    private var isSearchBoxExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBox = findViewById(R.id.searchBox)
        backButton = findViewById(R.id.backButton)

        searchBox.setOnClickListener {
            if (!isSearchBoxExpanded) {
                animateSearchBox()
            }
        }

        backButton.setOnClickListener {
            resetSearchBox()
        }
    }

    private fun animateSearchBox() {
        // Move the search box to the top
        val moveUpAnimator = ValueAnimator.ofFloat(searchBox.y, 50f)
        moveUpAnimator.addUpdateListener {
            searchBox.y = it.animatedValue as Float
        }

        // Animate the width of the search box
        val widthAnimator = ValueAnimator.ofInt(searchBox.width, 800) // Adjust as needed
        widthAnimator.addUpdateListener {
            val params = searchBox.layoutParams as LinearLayout.LayoutParams
            params.width = it.animatedValue as Int
            searchBox.layoutParams = params
        }

        moveUpAnimator.duration = 300
        widthAnimator.duration = 300

        moveUpAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                isSearchBoxExpanded = true
                backButton.visibility = View.VISIBLE
            }
        })

        moveUpAnimator.start()
        widthAnimator.start()
    }

    private fun resetSearchBox() {
        // Reset search box to initial state
        val moveDownAnimator = ValueAnimator.ofFloat(searchBox.y, resources.displayMetrics.heightPixels / 2f)
        moveDownAnimator.addUpdateListener {
            searchBox.y = it.animatedValue as Float
        }

        val widthAnimator = ValueAnimator.ofInt(searchBox.width, 600) // Set to initial width
        widthAnimator.addUpdateListener {
            val params = searchBox.layoutParams as LinearLayout.LayoutParams
            params.width = it.animatedValue as Int
            searchBox.layoutParams = params
        }

        moveDownAnimator.duration = 300
        widthAnimator.duration = 300

        moveDownAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                isSearchBoxExpanded = false
                backButton.visibility = View.GONE
            }
        })

        moveDownAnimator.start()
        widthAnimator.start()
    }
}
