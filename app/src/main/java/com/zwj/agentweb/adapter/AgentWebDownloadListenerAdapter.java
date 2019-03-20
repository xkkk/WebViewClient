package com.zwj.agentweb.adapter;

import com.just.agentweb.LogUtils;
import com.just.agentweb.download.AgentWebDownloader;
import com.just.agentweb.download.DownloadListenerAdapter;
import com.just.agentweb.download.DownloadingService;
import com.zwj.agentweb.R;

public class AgentWebDownloadListenerAdapter extends DownloadListenerAdapter{

    private DownloadingService mDownloadingService;
    public AgentWebDownloadListenerAdapter() {
        super();
    }

    /**
     *
     * @param url                下载链接
     * @param userAgent          UserAgent
     * @param contentDisposition ContentDisposition
     * @param mimetype           资源的媒体类型
     * @param contentLength      文件长度
     * @param extra              下载配置 ， 用户可以通过 Extra 修改下载icon ， 关闭进度条 ， 是否强制下载。
     * @return true 表示用户处理了该下载事件 ， false 交给 AgentWeb 下载
     */
    @Override
    public boolean onStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength, AgentWebDownloader.Extra extra) {
//        return super.onStart(url, userAgent, contentDisposition, mimetype, contentLength, extra);
        LogUtils.i("AgentWebDownloadListenerAdapter", "onStart:" + url);
        extra.setOpenBreakPointDownload(true) // 是否开启断点续传
                .setIcon(R.drawable.ic_file_download_black_24dp) //下载通知的icon
                .setConnectTimeOut(6000) // 连接最大时长
                .setBlockMaxTime(10 * 60 * 1000)  // 以8KB位单位，默认60s ，如果60s内无法从网络流中读满8KB数据，则抛出异常
                .setDownloadTimeOut(Long.MAX_VALUE) // 下载最大时长
                .setParallelDownload(false)  // 串行下载更节省资源哦
                .setEnableIndicator(true)  // false 关闭进度通知
                .addHeader("Cookie", "xx") // 自定义请求头
                .setAutoOpen(true) // 下载完成自动打开
                .setForceDownload(true); // 强制下载，不管网络网络类型
        return false;
    }

    @Override
    public void onBindService(String url, DownloadingService downloadingService) {
        super.onBindService(url, downloadingService);
        mDownloadingService = downloadingService;
        LogUtils.i("AgentWebDownloadListenerAdapter", "onBindService:" + url + "  DownloadingService:" + downloadingService);
    }

    @Override
    public void onProgress(String url, long downloaded, long length, long usedTime) {
        int mProgress = (int) ((downloaded) / Float.valueOf(length) * 100);
        LogUtils.i("AgentWebDownloadListenerAdapter", "onProgress:" + mProgress);
        super.onProgress(url, downloaded, length, usedTime);
    }

    @Override
    public void onUnbindService(String url, DownloadingService downloadingService) {
        super.onUnbindService(url, downloadingService);
        mDownloadingService = null;
        LogUtils.i("AgentWebDownloadListenerAdapter", "onUnbindService:" + url);
    }

    @Override
    public boolean onResult(String path, String url, Throwable e) {
        if (null == e) { //下载成功
            //do you work
        } else {//下载失败

        }
        return false; // true  不会发出下载完成的通知 , 或者打开文件
    }

    public DownloadingService getDownloadingService(){
        return this.mDownloadingService;
    }
}
