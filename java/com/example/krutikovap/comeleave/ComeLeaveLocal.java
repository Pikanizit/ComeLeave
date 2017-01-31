package com.example.krutikovap.comeleave;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.example.krutikovap.comeleave.data.CiclCL;

public class ComeLeaveLocal implements LocationListener {
    private LocationManager locationManager;
    private String provider;

    private double latitude;

    private double longitude;

    private Context ctx;

    //Location location;

    public ComeLeaveLocal(Context ctx) {
        this.ctx = ctx;

        // Get the location manager
        this.locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);

        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        this.provider = this.locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = this.locationManager.getLastKnownLocation(getProvider());

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + getProvider() + " has been selected.");
            onLocationChanged(location);


        } else {
            //latituteField.setText("Location not available");
            //longitudeField.setText("Location not available");
        }


    }


    public Location getLocation() {
        if (ActivityCompat.checkSelfPermission(this.ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        return this.locationManager.getLastKnownLocation(getProvider());
    }

    public void onLocationChanged(){
        Location location = getLocation();

        this.latitude =  (location.getLatitude());
        this.longitude =  (location.getLongitude());
    }

    @Override
    public void onLocationChanged(Location location) {
        this.latitude =  (location.getLatitude());
        this.longitude =  (location.getLongitude());

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        //Новый провайдер найден
        Toast.makeText(this.ctx, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        //Провайдер отключился
        Toast.makeText(this.ctx, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    public LocationManager getLocationManager() {
        return this.locationManager;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getProvider() {
        return this.provider;
    }
}
