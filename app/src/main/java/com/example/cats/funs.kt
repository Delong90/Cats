package com.example.cats

import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide


fun placePictureInView(view:ImageView, pictureUrl: String?){
    Glide.with(view.context).load(pictureUrl).into(view)
}

fun setBackGroundAnimation(animationDrawable: AnimationDrawable){
        animationDrawable.apply {
            setEnterFadeDuration(1000)
            setExitFadeDuration(3000)
            start()
        }
    }





