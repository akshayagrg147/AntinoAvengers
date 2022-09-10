package com.antino.avengers.Others

import android.content.Context
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.antino.avengers.R

class Utils {
    companion object{
    fun setUpRecyclerOneItemLayoutStaggered(context: Context, recyclerView: RecyclerView) {

        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context,1)
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