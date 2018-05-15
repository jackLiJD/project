package com.example.lijinduo.mydemo.tool;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/4/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class LocationTools {
    private Location location;
    public static LocationManager getLocationManager(Context context)
    {
        return (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
    }
    //获取位置信息
    public String getAddress(Context context)
    {
        LocationManager locationManager = this.getLocationManager(context);
        if(!locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER))
        {
            //打开GPS 需Android2.2以上系统支持
            android.provider.Settings.Secure.setLocationProviderEnabled(context.getContentResolver(), LocationManager.GPS_PROVIDER, false);
        }
        return doWork(context);
    }

    private String doWork(Context context)
    {
        String addres="";
        LocationManager locationManager = this.getLocationManager(context);
        Criteria criteria = new Criteria();
        // 获得最好的定位效果
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        // 使用省电模式
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider =locationManager.getBestProvider(criteria, true);
        Log.i("provider>>>>>>", provider);
        //获得当前位置  location为空是一直取  从onLocationChanged里面取
        while(location==null)
        {
            location =locationManager.getLastKnownLocation(provider);
        }
        //locationListener
        LocationListener locationListener = new LocationListener()
        {

            @Override
            public void onLocationChanged(Location location)
            {
                LocationTools.this.location=location;
            }

            @Override
            public void onProviderDisabled(String provider)
            {

            }

            @Override
            public void onProviderEnabled(String provider)
            {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras)
            {

            }

        };
        locationManager.requestLocationUpdates(provider, 1000, 10, locationListener);

        Geocoder geo = new Geocoder(context, Locale.getDefault());
        try
        {
            List<Address> address=geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if(address.size()>0)
            {
                addres=address.get(0).getAddressLine(0);
            }
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return addres;
    }
}