package com.zwj.agentweb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.zwj.agentweb.R;
import com.zwj.agentweb.web.AgentWebFragment;
import com.zwj.agentweb.web.FragmentKeyDown;
import com.zwj.agentweb.web.JsbridgeWebFragment;

import static com.zwj.agentweb.activity.MainActivity.FLAG_GUIDE_DICTIONARY_FILE_DOWNLOAD;
import static com.zwj.agentweb.activity.MainActivity.FLAG_GUIDE_DICTIONARY_INPUT_TAG_PROBLEM;
import static com.zwj.agentweb.activity.MainActivity.FLAG_GUIDE_DICTIONARY_JSBRIDGE_SAMPLE;
import static com.zwj.agentweb.activity.MainActivity.FLAG_GUIDE_DICTIONARY_JS_JAVA_COMUNICATION_UPLOAD_FILE;
import static com.zwj.agentweb.activity.MainActivity.FLAG_GUIDE_DICTIONARY_LINKS;
import static com.zwj.agentweb.activity.MainActivity.FLAG_GUIDE_DICTIONARY_MAP;
import static com.zwj.agentweb.activity.MainActivity.FLAG_GUIDE_DICTIONARY_USE_IN_FRAGMENT;
import static com.zwj.agentweb.activity.MainActivity.FLAG_GUIDE_DICTIONARY_VIDEO_FULL_SCREEN;

public class SingleWebActivity extends AppCompatActivity {


    private FrameLayout mFrameLayout;
    public static final String TYPE_KEY = "type_key";
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web);

        mFrameLayout = (FrameLayout) this.findViewById(R.id.container_framelayout);
        int key = getIntent().getIntExtra(TYPE_KEY, -1);
        mFragmentManager = this.getSupportFragmentManager();
        openFragment(key);
    }


    private AgentWebFragment mAgentWebFragment;

    private void openFragment(int key) {

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Bundle mBundle = null;


        switch (key) {

            /*Fragment 使用AgenWeb*/
            case FLAG_GUIDE_DICTIONARY_USE_IN_FRAGMENT: //项目中请使用常量代替0 ， 代码可读性更高
                ft.add(R.id.container_framelayout, mAgentWebFragment = AgentWebFragment.getInstance(mBundle = new Bundle()), AgentWebFragment.class.getName());
                mBundle.putString(AgentWebFragment.URL_KEY, "https://m.vip.com/?source=www&jump_https=1");
                break;
            /*下载文件*/
            case FLAG_GUIDE_DICTIONARY_FILE_DOWNLOAD:
                ft.add(R.id.container_framelayout, mAgentWebFragment = AgentWebFragment.getInstance(mBundle = new Bundle()), AgentWebFragment.class.getName());
                mBundle.putString(AgentWebFragment.URL_KEY, "http://android.myapp.com/");
                break;
            /*input标签上传文件*/
            case FLAG_GUIDE_DICTIONARY_INPUT_TAG_PROBLEM:
                ft.add(R.id.container_framelayout, mAgentWebFragment = AgentWebFragment.getInstance(mBundle = new Bundle()), AgentWebFragment.class.getName());
                mBundle.putString(AgentWebFragment.URL_KEY, "file:///android_asset/upload_file/uploadfile.html");
                break;
            /*Js上传文件*/
            case FLAG_GUIDE_DICTIONARY_JS_JAVA_COMUNICATION_UPLOAD_FILE:
                ft.add(R.id.container_framelayout, mAgentWebFragment = AgentWebFragment.getInstance(mBundle = new Bundle()), AgentWebFragment.class.getName());
                mBundle.putString(AgentWebFragment.URL_KEY, "file:///android_asset/js_interaction/agentwebdemo.html");
                break;
            /*优酷全屏播放视屏*/
            case FLAG_GUIDE_DICTIONARY_VIDEO_FULL_SCREEN:
                ft.add(R.id.container_framelayout, mAgentWebFragment = AgentWebFragment.getInstance(mBundle = new Bundle()), AgentWebFragment.class.getName());
                mBundle.putString(AgentWebFragment.URL_KEY, "http://m.youku.com/video/id_XODEzMjU1MTI4.html");
//                mBundle.putString(AgentWebFragment.URL_KEY, "https://v.qq.com/x/page/i0530nu6z1a.html");
                break;
            /*短信*/
            case FLAG_GUIDE_DICTIONARY_LINKS:
                ft.add(R.id.container_framelayout, mAgentWebFragment = AgentWebFragment.getInstance(mBundle = new Bundle()), AgentWebFragment.class.getName());
                mBundle.putString(AgentWebFragment.URL_KEY, "file:///android_asset/sms/sms.html");
                break;
            case FLAG_GUIDE_DICTIONARY_JSBRIDGE_SAMPLE:
                ft.add(R.id.container_framelayout,mAgentWebFragment = JsbridgeWebFragment.getInstance(mBundle = new Bundle()), JsbridgeWebFragment.class.getName());
                mBundle.putString(JsbridgeWebFragment.URL_KEY, "file:///android_asset/jsbridge/demo.html");
                break;
            /*地图*/
            case FLAG_GUIDE_DICTIONARY_MAP:
                ft.add(R.id.container_framelayout, mAgentWebFragment = AgentWebFragment.getInstance(mBundle = new Bundle()), AgentWebFragment.class.getName());
                mBundle.putString(AgentWebFragment.URL_KEY, "https://map.baidu.com/mobile/webapp/index/index/#index/index/foo=bar/vt=map");
                break;
            /*首屏秒开*/
//            case FLAG_GUIDE_DICTIONARY_VASSONIC_SAMPLE:
//                ft.add(R.id.container_framelayout, mAgentWebFragment = VasSonicFragment.create(mBundle = new Bundle()), AgentWebFragment.class.getName());
//                mBundle.putLong(PARAM_CLICK_TIME, getIntent().getLongExtra(PARAM_CLICK_TIME, -1L));
//                mBundle.putString(AgentWebFragment.URL_KEY, "http://mc.vip.qq.com/demo/indexv3");
//                break;
            default:
                break;

        }
        ft.commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //一定要保证 mAentWebFragemnt 回调
//		mAgentWebFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        AgentWebFragment mAgentWebFragment = this.mAgentWebFragment;
        if (mAgentWebFragment != null) {
            FragmentKeyDown mFragmentKeyDown = mAgentWebFragment;
            if (mFragmentKeyDown.onFragmentKeyDown(keyCode, event)) {
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }

        return super.onKeyDown(keyCode, event);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

