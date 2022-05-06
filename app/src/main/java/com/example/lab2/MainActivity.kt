package com.example.lab2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map:GoogleMap
    private lateinit var mapView:MapView
    private lateinit var mAdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.map)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        MobileAds.initialize(this){}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map =  googleMap
        //eventos
        map.uiSettings.isZoomControlsEnabled = true
        coordenadaInicial()

    }

    private fun coordenadaInicial()
    {
        val coordenadas = LatLng(9.970704, -84.129047)
        val marcador:MarkerOptions = MarkerOptions().position(coordenadas).title("Escuela de informatica")
        map.addMarker(marcador)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordenadas, 18f), 4500, null
        )

    }


    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}