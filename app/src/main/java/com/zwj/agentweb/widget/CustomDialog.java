package com.zwj.agentweb.widget;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.zwj.agentweb.R;

public class CustomDialog extends Dialog {

    private Activity context;

    private static TextView tv_des;

    public CustomDialog(Activity context, int theme) {
        super(context, theme);
        this.context = context;
    }

    public CustomDialog(Activity context) {
        super(context);
        this.context = context;
    }

    public void setDes(String des) {
        tv_des.setText(des);
    }

    /**
     * Create the custom dialog
     */
    public CustomDialog create(View view, boolean isTouchOutsideCancel, float w, float h, float gravity) {
        // instantiate the dialog with the custom Theme
        CustomDialog dialog = new CustomDialog(context, R.style.Dialog);
        dialog.setCanceledOnTouchOutside(isTouchOutsideCancel);

        dialog.setContentView(view);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);

        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值

        // WindowManager m = context.getWindowManager();
        // Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        // p.height = (int) (d.getHeight() * 0.5); // 高度设置为屏幕的0.6
        // p.width = (int) (d.getWidth() * 0.65); // 宽度设置为屏幕的0.65

        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 屏幕宽度（像素）
        int height = metric.heightPixels;
        p.width = (int) (width * w); // 宽度设置为屏幕的0.65
        if (h > 0) {
            p.height = (int) (height * h); // 高度设置为屏幕的0.6
        }
        p.y = (int) ((height - p.height) * gravity);

        dialogWindow.setAttributes(p);

        return dialog;
    }
}
