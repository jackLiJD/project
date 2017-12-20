package com.example.lijinduo.mydemo.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.view.FloatMessagerMainWindow;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/12/18
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class CameraAct extends BaseActivity{
    @BindView(R.id.open_camera)
    Button openCamera;
    @BindView(R.id.camera_img)
    ImageView cameraImg;
    private Context context = CameraAct.this;
    LinearLayout tostlin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_camera);
        ButterKnife.bind(this);
        Log.d("值得",java.lang.StrictMath.pow(1000,1.0/3)+ "onCreate: "+java.lang.StrictMath.pow(10000,1.0/3));
        double a=0.6233333333;
        double b=0.0833333333;
        Log.d("结果", "onCreate: "+(a+b));
        Log.d("结果1", "onCreate: "+moneyDouble(a+b));
        Log.d("结果2", (moneyDouble(a)+moneyDouble(b))+"onCreate: "+moneyDouble(a)+moneyDouble(b));
        Log.d("结果3", (money(a)+money(b))+"onCreate: "+money(a)+money(b));
        double c=1232112.62888888;
        Log.d("结果4", money(c)+"onCreate: "+moneyDouble(c)+"结果"+moneyDouble2(c));
        tostlin= (LinearLayout) findViewById(R.id.tostlin);
        tostlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"1",Toast.LENGTH_SHORT).show();
            }
        });
        cameraImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    int checkCallPhonePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.SYSTEM_ALERT_WINDOW);
                    if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW},2);
                        return;
                    }else{
                        //上面已经写好的拨号方法
                        creat();
                    }
                } else {
                    //上面已经写好的拨号方法
                    creat();
                }

            }
        });
    }

    private void creat(){
        FloatMessagerMainWindow.getFloatMessagerMainWindow(getApplicationContext(), tostlin);
    }

    @OnClick(R.id.open_camera)
    public void onViewClicked() {
    }

    /**
     * @param requestCode 请求code
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 2:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    creat();
                } else {
                    Toast.makeText(context, "权限未确认", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    //金额转换
    public static double moneyDouble(double money) {
        DecimalFormat df = new DecimalFormat("0.00");
        String money1 = df.format(money);
        return Double.valueOf(money1);
    }

    public static String moneyDouble2(double money) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.FLOOR);
//        df.setMinimumFractionDigits(2);
        String money1 = df.format(money);
        return money1;
    }

    public static String money(double money) {
        DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(2);
        formater.setMinimumFractionDigits(2);
        formater.setGroupingSize(2);
        formater.setRoundingMode(RoundingMode.CEILING);
        return formater.format(money);
    }

}
