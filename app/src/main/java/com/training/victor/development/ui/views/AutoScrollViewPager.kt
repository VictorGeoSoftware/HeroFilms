package com.training.victor.development.ui.views

import android.content.Context
import android.os.Handler
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import com.training.victor.development.BuildConfig
import java.lang.reflect.Field

class AutoScrollViewPager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    ViewPager(context, attrs) {

    private var currentPage = 0
    private val mHandler = Handler()
    private val turnPageRunnable = Runnable { this.turnPage() }
    private var autoScrollActive = false
    private var turnPageDelay =
        DEFAULT_TURN_PAGE_DELAY

    init {
        init()
    }

    /*
        Custom methods
        */
    private fun init() {
        addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    stopAutScroll()
                } else if (state == ViewPager.SCROLL_STATE_IDLE) {
                    startAutoScroll()
                }
            }
        })

        slowDownScroll()
    }

    private fun slowDownScroll() {
        try {
            val mScroller: Field = ViewPager::class.java.getDeclaredField("mScroller")
            mScroller.isAccessible = true
            val scroller = FixedSpeedScroller(context)
            mScroller.set(this, scroller)
        } catch (e: NoSuchFieldException) {

        } catch (e: IllegalArgumentException) {

        } catch (e: IllegalAccessException) {

        }

    }

    fun startAutoScroll() {
        if (!autoScrollActive) {
            autoScrollActive = true
            mHandler.postDelayed(turnPageRunnable, turnPageDelay.toLong())
        }
    }

    fun stopAutScroll() {
        mHandler.removeCallbacks(turnPageRunnable)
        autoScrollActive = false
    }

    private fun turnPage() {
        if (adapter == null) {

            stopAutScroll()
        } else if (autoScrollActive) {
            if (currentPage >= adapter!!.count - 1) {
                currentPage = 0
            } else {
                currentPage++
            }

            try {
                setCurrentItem(currentPage, true)
                mHandler.postDelayed(turnPageRunnable, turnPageDelay.toLong())
            } catch (e: Exception) {
                stopAutScroll()
                if (BuildConfig.DEBUG) {
                    e.printStackTrace()
                } else {

                }
            }

        }
    }

    companion object {
        private const val DEFAULT_TURN_PAGE_DELAY = 5000
    }
}