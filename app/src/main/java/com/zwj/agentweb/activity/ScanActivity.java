package com.zwj.agentweb.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.zwj.agentweb.R;
import com.zwj.agentweb.util.UiUtil;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ScanActivity extends AppCompatActivity implements QRCodeView.Delegate{
    private static final int REQUEST_CODE_CAMERA = 999;
    ZXingView zXingView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        zXingView = findViewById(R.id.zxingview);
        zXingView.setDelegate(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //打开后置摄像头预览,但并没有开始扫描
        zXingView.startCamera();
//        开启扫描框
        zXingView.showScanRect();
        zXingView.startSpot();
    }

    @Override
    protected void onStop() {
        zXingView.stopCamera();
        super.onStop();

    }


    private void vibrator(){
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
    @Override
    public void onScanQRCodeSuccess(String result) {
        //扫描成功后调用震动器
//        vibrator();
        //显示扫描结果
//        UiUtil.showToast(this,result);
        //再次延时1.5秒后启动
//        zXingView.startSpot();
        Intent intent = new Intent();
        intent.putExtra("result",result);
        setResult(666,intent);
        finish();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        UiUtil.showToast(this,getString(R.string.qrCodePermissionTip));
        ActivityCompat.requestPermissions(this,

                new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_CAMERA) {
            zXingView.startCamera();
            zXingView.startSpot();
        }
    }
}
