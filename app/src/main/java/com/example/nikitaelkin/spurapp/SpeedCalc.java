package com.example.nikitaelkin.spurapp;

import android.location.Location;

public class SpeedCalc {
    private double lat1;
    private double lat2;
    private double lon1;
    private double lon2;
    private double time_diff;
    private double speed;

    public SpeedCalc(Location loc1, Location loc2, double time)
    {
        lat1=java.lang.Math.abs(loc1.getLatitude());
        lat2=java.lang.Math.abs(loc2.getLatitude());
        lon1=java.lang.Math.abs(loc1.getLongitude());
        lon2=java.lang.Math.abs(loc2.getLongitude());
        time_diff=time;
        speedCalc(distCalc());
    }

    private double distCalc()
    {
        double M_PI= 3.14159;
        // Convert degrees to radians
        lat1 = lat1 * M_PI / 180.0;
        lon1 = lon1 * M_PI / 180.0;

        lat2 = lat2 * M_PI / 180.0;
        lon2 = lon2 * M_PI / 180.0;

        // radius of earth in metres
        double r = 6368652;

        // P
        double rho1 = r * java.lang.Math.cos(lat1);
        double z1 = r * java.lang.Math.sin(lat1);
        double x1 = rho1 * java.lang.Math.cos(lon1);
        double y1 = rho1 * java.lang.Math.sin(lon1);

        // Q
        double rho2 = r * java.lang.Math.cos(lat2);
        double z2 = r * java.lang.Math.sin(lat2);
        double x2 = rho2 * java.lang.Math.cos(lon2);
        double y2 = rho2 * java.lang.Math.sin(lon2);

        // Dot product
        double dot = (x1 * x2 + y1 * y2 + z1 * z2);
        double cos_theta = dot / (r * r);

        double theta = java.lang.Math.acos(cos_theta);

        // Distance in Metres
        return(r * theta);
    }

    private void speedCalc(double distance)
    {
        //gives m/s
        speed=distance/(time_diff/1000);
    }

    public double getSpeed()
    {
        return speed;
    }
}
