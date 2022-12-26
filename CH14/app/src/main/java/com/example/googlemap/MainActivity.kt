package com.example.googlemap

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.gms.maps.SupportMapFragment


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isEmpty() && requestCode == 0) {
            for (i in grantResults)
                if (i != PackageManager.PERMISSION_GRANTED)
                    finish()
            val map = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            map.getMapAsync(this)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val map = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        map.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 0)
        } else {
            map.isMyLocationEnabled = true
            val m1 = MarkerOptions()
            m1.position(LatLng(25.033611, 121.565000))
            m1.title("Taipei 101")
            m1.draggable(true)
            map.addMarker(m1)
            val m2 = MarkerOptions()
            m2.position(LatLng(25.047924, 121.517081))
            m2.title("Taipei Station")
            m2.draggable(true)
            map.addMarker(m2)
            val polylineOpt = PolylineOptions()
            polylineOpt.add(LatLng(25.033611, 121.565000))
            polylineOpt.add(LatLng(25.032728, 121.565137))
            polylineOpt.add(LatLng(25.047924, 121.517081))
            polylineOpt.color(Color.BLUE)
            val polyline = map.addPolyline(polylineOpt)
            polyline.width = 10f
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(25.034, 121.545), 13f))
        }
    }
}
