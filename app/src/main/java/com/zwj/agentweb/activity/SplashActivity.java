package com.zwj.agentweb.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zwj.agentweb.R;
import com.zwj.agentweb.base.AppConstant;
import com.zwj.agentweb.util.SharedPreferencesUtil;

public class SplashActivity extends AppCompatActivity {
    private ImageView imageView;
    private SharedPreferencesUtil sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = SharedPreferencesUtil.getInstance();
        String splash_url = sp.get(AppConstant.SpConstant.SPLASH_URL);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.iv);

//        if(!TextUtils.isEmpty(splash_url)){
//            Glide.with(this).load(splash_url).into(imageView);
//        }

        mHandle.sendMessageDelayed(Message.obtain(),3000);
    }



    private Handler mHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
        }
    };

}
