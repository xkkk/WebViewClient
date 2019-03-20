package com.zwj.agentweb.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zwj.agentweb.CustomApplication;
import com.zwj.agentweb.R;
import com.zwj.agentweb.base.AppConstant;
import com.zwj.agentweb.http.HttpManager;
import com.zwj.agentweb.util.SharedPreferencesUtil;
import com.zwj.agentweb.util.UiUtil;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_save;
    private ImageView iv_splash;
    private EditText et_splah_url;
    private EditText et_version;
    private EditText et_index;
    private SharedPreferencesUtil sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        sp = SharedPreferencesUtil.getInstance();

    }

    private void initView() {

        iv_splash = findViewById(R.id.iv_splash);
        et_index = findViewById(R.id.et_index);
        et_splah_url = findViewById(R.id.et_splash_url);
        et_version = findViewById(R.id.et_version);
        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        iv_splash.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            try {

                if (data != null) {
                    Uri uri = data.getData();
                    String img_url = null;//这是本机的图片路径
                    if (uri != null) {
                        img_url = uri.getPath();
                        sp.put(AppConstant.SpConstant.SPLASH_URL, img_url);
                        ContentResolver cr = this.getContentResolver();
                        Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                        /* 将Bitmap设定到ImageView */
                        iv_splash.setBackground(new BitmapDrawable(bitmap));
                    }

                }

            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
//                String baseUrl = AppConstant.BASE_URL;
//                String url =baseUrl+"weather_mini?";
//                Map<String,String> map = new HashMap<>();
//                map.put("city","北京");
//                HttpManager.getRequest()
//                        .methodGet(url,map)
//                        .subscribeOn(Schedulers.io())//IO线程加载数据
//                        .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
//                        .subscribe(new Consumer<ResponseBody>() {
//                            @Override
//                            public void accept(ResponseBody responseBody) throws Exception {
//                               String data = responseBody.string();
//                               UiUtil.showToast(CustomApplication.getContext(),data);
//                            }
//                        });
                String splash_url = et_splah_url.getText().toString();
                if (!TextUtils.isEmpty(splash_url)) {
                    sp.put(AppConstant.SpConstant.SPLASH_URL,splash_url);
                }

                String index_url = et_index.getText().toString();
                if(!TextUtils.isEmpty(index_url)){
                    sp.put(AppConstant.SpConstant.INDEX_URL,index_url);
                }

                String version_url = et_version.getText().toString();
                if(!TextUtils.isEmpty(version_url)){
                    sp.put(AppConstant.SpConstant.VERSION_URK,version_url);
                }


                break;
            case R.id.iv_splash:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 999);
                break;
        }
    }
}
