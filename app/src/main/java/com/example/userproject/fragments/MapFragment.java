package com.example.userproject.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.userproject.R;
import com.example.userproject.activities.Cart;
import com.example.userproject.activities.MapActivity;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Locale;
//import com.google.android.gms.maps.SupportMapFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        setHasOptionsMenu(true);

        supportMapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

        client = LocationServices.getFusedLocationProviderClient(getContext());


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();

        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        return v;
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {

            LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @SuppressLint("MissingPermission")
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
//                                    Toast.makeText(MapActivity.this, "latitude" + location1.getLatitude(), Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(MapActivity.this, "longitude" + location1.getLongitude(), Toast.LENGTH_SHORT).show();

                                }
                            };
                            client.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());

                        }
                    }
                });
            } else

            {
                startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                Toast.makeText(getContext(), "Flag", Toast.LENGTH_SHORT).show();
            }

//        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
//                            options.position(latLng).title(latLng+"");
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
////                                    googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
////                                        @Override
////                                        public boolean onMarkerClick(Marker marker) {
////                                            Intent intent = new Intent(getContext(), Cart.class);
////                                            startActivity(intent);
////                                            return true;
////                                        }
////                                    });
//                                    googleMap.clear();
//                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
//                                            latLng, 10));
//
////                                        LatLng gaza = new LatLng(31.4167, 34.3333);
////                                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(gaza));
//                                    googleMap.addMarker(markerOptions);
//
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
            Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

}