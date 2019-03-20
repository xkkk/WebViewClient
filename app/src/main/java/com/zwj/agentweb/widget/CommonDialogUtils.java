package com.zwj.agentweb.widget;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zwj.agentweb.R;
import com.zwj.agentweb.util.UiUtil;


public class CommonDialogUtils {

    public static void showSetPermissionDialog(final Activity activity, String title,
                                               final DialogInterface.OnClickListener cancelClick,
                                               final DialogInterface.OnClickListener confirmClick) {
        new AlertDialog.Builder(activity)
                .setCancelable(true)
                .setTitle(title)
                .setNegativeButton(activity.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (cancelClick != null) {
                            cancelClick.onClick(dialog, which);
                        }
                    }
                })
                .setPositiveButton(activity.getString(R.string.goSetting), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (confirmClick != null) {
                            confirmClick.onClick(dialog, which);
                            return;
                        }
                        Intent intent = UiUtil.getAppDetailSettingIntent(activity);
                        activity.startActivity(intent);
                    }
                }).show();
    }

    /**
     * 显示确认弹窗
     *
     * @param activity
     * @param message        提示消息
     * @param onConfirmClick 确认点击监听
     */
    public static void showOnlyConfirmDialog(Activity activity, String message, final View.OnClickListener onConfirmClick) {
        final CustomDialog dialog = new CustomDialog(activity, R.style.Dialog);
        View layout = LayoutInflater.from(activity).inflate(R.layout.dialog_layout_only_confirm, null);
        dialog.addContentView(layout, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView tv_message = (TextView) layout.findViewById(R.id.tv_message);
        TextView tv_confirm = (TextView) layout.findViewById(R.id.tv_confirm);

        tv_message.setText(message);

        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onConfirmClick != null) {
                    onConfirmClick.onClick(v);
                }
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

}
