package com.televantou.wombat.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * Created by Eirini Televantou on 15/07/21 for Wombat.
 */

//Binding Adapter to set the image URL for Glide from XML
@BindingAdapter(value = ["setImageUrl"])
fun ImageView.bindImageUrl(url: String?) {
    Glide.with(context).load(url).into(this)
}

