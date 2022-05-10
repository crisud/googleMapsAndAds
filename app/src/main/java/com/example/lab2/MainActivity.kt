package com.example.lab2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map:GoogleMap
    private lateinit var mapView:MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.map)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        MobileAds.initialize(this)
        val adView = findViewById<AdView>(R.id.ad)
        val adRequest = AdRequest.Builder().build()

        adView.adListener = object: AdListener()
        {
            override fun onAdClicked() {
                map.uiSettings.isZoomControlsEnabled = true
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                adView.loadAd(adRequest)
            }
        }

        adView.loadAd(adRequest)
    }

    override fun onMapReady(googleMap: GoogleMap) { //evento
        map =  googleMap

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