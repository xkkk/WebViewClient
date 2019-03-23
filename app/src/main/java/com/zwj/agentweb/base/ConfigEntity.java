package com.zwj.agentweb.base;

public class ConfigEntity {


    /**
     * splash : {"url":"www.baidu.com","duration":3000}
     * toolBar : {"display":true,"color":"#3F51B5"}
     */

    private SplashBean splash;
    private ToolBarBean toolBar;

    public SplashBean getSplash() {
        return splash;
    }

    public void setSplash(SplashBean splash) {
        this.splash = splash;
    }

    public ToolBarBean getToolBar() {
        return toolBar;
    }

    public void setToolBar(ToolBarBean toolBar) {
        this.toolBar = toolBar;
    }

    public static class SplashBean {
        /**
         * url : www.baidu.com
         * duration : 3000
         */

        private String url;
        private int duration;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }
    }

    public static class ToolBarBean {
        /**
         * display : true
         * color : #3F51B5
         */

        private boolean display;
        private String color;

        public boolean isDisplay() {
            return display;
        }

        public void setDisplay(boolean display) {
            this.display = display;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
