package com.barges

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import kotlinx.coroutines.flow.StateFlow

@BindingAdapter("android:visibility")
fun View.bindVisibility(value: StateFlow<Boolean>?) {
    this.isVisible = value?.value ?: false
}