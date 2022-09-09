package com.antino.avengers

import android.content.Context
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Utils {
    companion object{
    fun setUpRecyclerOneItemLayoutStaggered(context: Context, recyclerView: RecyclerView) {

        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        setRecyclerItemAnimation(context, recyclerView)
    }
    fun setRecyclerItemAnimation(context: Context, recyclerView: RecyclerView) {
        val list = listOf(
            R.anim.layout_animation_fall_down,
            R.anim.layout_animation_from_bottom,
            R.anim.layout_animation_from_left,
            R.anim.layout_animation_from_right
        )
        val animation = AnimationUtils.loadLayoutAnimation(context, list.random())
        recyclerView.layoutAnimation = animation
        recyclerView.scheduleLayoutAnimation()
    }
}}