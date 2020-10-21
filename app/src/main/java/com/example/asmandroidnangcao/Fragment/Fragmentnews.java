package com.example.asmandroidnangcao.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.asmandroidnangcao.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragmentnews extends Fragment {
    private SupportMapFragment mapFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragmentnews,container,false);
//       isPermissionGranted();
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {

                    LatLng fpt = new LatLng(10.8529444,106.6273561);
                    googleMap.addMarker(new MarkerOptions().position(fpt).title("FPT Polytechnic HCM CS3"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(fpt));
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(19.0f));
                    UiSettings uisetting = googleMap.getUiSettings();
                    uisetting.setCompassEnabled(true);
                    uisetting.setZoomControlsEnabled(true);
                    uisetting.setScrollGesturesEnabled(true);
                    uisetting.setTiltGesturesEnabled(true);

                    uisetting.setMyLocationButtonEnabled(true);
                    googleMap.setMyLocationEnabled(true);
                }
            });
        }

        // R.id.map is a FrameLayout, not a Fragment
        getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
        return view;
    }


}
