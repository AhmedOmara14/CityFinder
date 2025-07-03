package com.omaradev.cityfinder.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri

fun navigateToMapWithLatAndLng(context: Context, latitude: Double, longitude: Double) {
    val uri = "geo:$latitude,$longitude?q=$latitude,$longitude".toUri()
    val intent = Intent(Intent.ACTION_VIEW, uri).apply {
        setPackage("com.google.android.apps.maps")
    }
    context.startActivity(intent)
}
