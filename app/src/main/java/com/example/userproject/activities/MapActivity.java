package com.example.userproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.widget.Toast;

import com.example.userproject.R;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Map;

public class MapActivity extends AppCompatActivity {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
//        toolbar = findViewById(R.id.main_toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle("حدد موقعك");
        supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_activity);

        client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();

        } else {
            ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {

                    Location location = task.getResult();
                    if (location != null) {
                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(GoogleMap googleMap) {
                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                MarkerOptions options = new MarkerOptions();
                                options.position(latLng).title(""+latLng);
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                                googleMap.addMarker(options);
//                        Toast.makeText(MapActivity.this, "latitude"+location.getLatitude(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(MapActivity.this, "longitude"+location.getLongitude(), Toast.LENGTH_SHORT).show();

                            }});
                    } else

                            {
                                LocationRequest locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                        .setInterval(1000)
                                        .setFastestInterval(1000)
                                        .setNumUpdates(1);

                                LocationCallback locationCallback = new LocationCallback() {
                                    @Override
                                    public void onLocationResult(@NonNull LocationResult locationResult) {
                                        Location location1 = locationResult.getLastLocation();
                                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                                            @Override
                                            public void onMapReady(GoogleMap googleMap) {
                                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                                MarkerOptions options = new MarkerOptions();
                                                options.position(latLng).title(""+latLng);
                                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                                                googleMap.addMarker(options);
//                        Toast.makeText(MapActivity.this, "latitude"+location.getLatitude(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(MapActivity.this, "longitude"+location.getLongitude(), Toast.LENGTH_SHORT).show();

                                            }});
                                        Toast.makeText(MapActivity.this, "latitude" + location1.getLatitude(), Toast.LENGTH_SHORT).show();
                                        Toast.makeText(MapActivity.this, "longitude" + location1.getLongitude(), Toast.LENGTH_SHORT).show();

                                    }
                                };
                                client.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());

                            }
                        }
                    });
                } else

                {
//                    startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//                    Toast.makeText(this, "Flag", Toast.LENGTH_SHORT).show();
                }
//        if (ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Task<Location> task = client.getLastLocation();
//        task.addOnSuccessListener(new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if (location != null) {
//                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
//                        @Override
//                        public void onMapReady(GoogleMap googleMap) {
//                            LatLng latLng = new LatLng(31.4167, 34.3333);
//                            MarkerOptions options = new MarkerOptions();
//                            options.position(latLng).title("هذا موقعك");
//                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//                            googleMap.addMarker(options);
//
//                            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                                @Override
//                                public void onMapClick(LatLng latLng) {
//                                    MarkerOptions markerOptions = new MarkerOptions();
//                                    markerOptions.position(latLng);
////                                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
//                                    markerOptions.title(""+latLng);
//                                    googleMap.clear();
//                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
//                                            latLng, 10));
//
////                                        LatLng gaza = new LatLng(31.4167, 34.3333);
////                                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(gaza));
//                                    googleMap.addMarker(markerOptions);
//
//                                }
//                            });
//                        }
//                    });
//                }
//            }
//        });

            }

            @Override
            public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults){
                if (requestCode == 44 && grantResults.length > 0 && (grantResults[0] + grantResults[1]
                        == PackageManager.PERMISSION_GRANTED)) {

                    getCurrentLocation();
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getCurrentLocation();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }


        }