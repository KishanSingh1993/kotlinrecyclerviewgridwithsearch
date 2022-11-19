package com.example.kotlinsampleapp.utils

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(condext: Context, recyclerView: RecyclerView, private val listener: OnRecyclerClickListener):
    RecyclerView.SimpleOnItemTouchListener(), RecyclerView.OnItemTouchListener {

    interface OnRecyclerClickListener {
        fun onItemPress(view: View, position: Int)
    }

    private val gestureDetector = GestureDetectorCompat(condext, object: GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            val v = recyclerView.findChildViewUnder(e.x, e.y)
            if (v != null) {
                listener.onItemPress(v, recyclerView.getChildAdapterPosition(v))
            }
            return true
        }
    })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val result = gestureDetector.onTouchEvent(e)
        return  result
    }
}