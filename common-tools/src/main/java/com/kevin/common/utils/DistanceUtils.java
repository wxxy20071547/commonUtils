package com.kevin.common.utils;

/**
 * Created by kevin on 2018/8/15.
 */
public class DistanceUtils {
    private final static double PI = 3.14159265358979323;
    private final static double R = 6371229;


    public static void main(String[] args) {
        double distance = getDistance(120.213116, 30.290998, 0, 0);
        System.out.println("Distance is:" + distance);
    }

    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double x, y, distance;
        x = (lng2 - lng1) * PI * R
                * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
        y = (lat2 - lat1) * PI * R / 180;
        distance = Math.hypot(x, y);
        distance = NumberUtil.formatDouble(distance / 1000, 3);
        return distance;
    }
}
