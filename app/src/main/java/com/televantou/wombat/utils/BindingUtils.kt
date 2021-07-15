package com.televantou.wombat.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * Created by Eirini Televantou on 03/02/21 for Retail inMotion.
 */
//Binding Adapter to set recycler view adapter from XML
@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}

//Binding Adapter to set the image URL for Glide from XML
@BindingAdapter(value = ["setImageUrl"])
fun ImageView.bindImageUrl(url: String?) {
    Glide.with(context).load(url).into(this)
}

