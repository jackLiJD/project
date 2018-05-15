package com.example.lijinduo.mydemo.location;

import android.Manifest;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/4/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class LocationAct extends BaseActivity {
    @BindView(R.id.button15)
    Button button15;
    private LocationManager locationManager;
    private String TAG = "location";
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_location);
        ButterKnife.bind(this);
        getPermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION});
    }

    @Override
    public void doSmoething() {
        getLngAndLatWithNetwork();
    }

    //从网络获取经纬度
    public void getLngAndLatWithNetwork() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = locationManager
                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 0, locationListener);
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            Log.d(TAG, getAddress(location).get(0).getLocality());
            button15.setText(getAddress(location).get(0).getLocality());
        }
    }

    // 获取地址信息
    private  List<Address> getAddress(Location location) {
        //用来接收位置的详细信息
        List<Address> result = null;
        try {
            if (location != null) {
                Geocoder gc = new Geocoder(this, Locale.getDefault());
                result = gc.getFromLocation(location.getLatitude(),
                        location.getLongitude(), 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @OnClick(R.id.button15)
    public void onViewClicked() {
    }
    LocationListener locationListener = new LocationListener() {

        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {

        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {

        }

        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
            Log.d(TAG, "onLocationChanged: "+location.getLongitude()+"||"+location.getLatitude());
        }
    };
}
