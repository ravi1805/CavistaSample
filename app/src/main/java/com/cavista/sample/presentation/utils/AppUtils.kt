package com.cavista.sample.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.cavista.sample.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

object AppUtils {

    const val noNetworkMsg = "Oops!! Not connected to internet."
    const val item_key = "ITEM_KEY"
    /**
     * @param context activity reference
     * @param imageUrl image web url
     * @param imageView reference of image view to display image
     */
    fun displayImage(context: Context, imageUrl: String, imageView: ImageView) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.cavista_placeholder)
        requestOptions.format(DecodeFormat.PREFER_ARGB_8888)
        if (context != null) {
            Glide.with(context).setDefaultRequestOptions(requestOptions).asBitmap().load(imageUrl)
                .into(object : BitmapImageViewTarget(imageView) {
                    override fun setResource(resource: Bitmap?) {
                        val circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.resources, resource)
                        circularBitmapDrawable.isCircular = false
                        imageView.setImageDrawable(circularBitmapDrawable)
                    }
                })

        }
    }

    fun getCurrentDateNTime(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy, hh:mm a")
        return sdf.format(Date())
    }

}