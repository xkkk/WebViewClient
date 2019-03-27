  function scan(){
         if(window.android!=null&&typeof(window.android)!="undefined"){
            window.android.goToScan();
         }else{
                 alert(typeof(window.android));
              }
        }

        function goUrl(){
         if(window.android!=null&&typeof(window.android)!="undefined"){
                window.android.goUrl("https://www.baidu.com");
              }else{
                 alert(typeof(window.android));
              }
        }

        function showToast() {
              // body...
              <!--console.log("call android"    )-->
              <!--alert("call android");-->
              if(window.android!=null&&typeof(window.android)!="undefined"){
                window.android.callAndroid("你好，Android! ");
              }else{
                 alert(typeof(window.android));
              }
          }

        function showAlert() {
              // body...
              //console.log("call android")
              if(window.android!=null&&typeof(window.android)!="undefined"){
                window.android.alertMessage("你好，Android! ");
              }else{
                 alert(typeof(window.android));
              }
          }

         function wakeupApp() {
              // body...
              //console.log("call android")
              if(window.android!=null&&typeof(window.android)!="undefined"){
                window.android.wakeUpAPP("com.taguxdesign.yixi","aaa");
              }else{
                 alert(typeof(window.android));
              }
          }

          function getData(){
           if(window.android!=null&&typeof(window.android)!="undefined"){
                window.android.getData("http://wthrcdn.etouch.cn/weather_mini?city=北京","aaa","display");
              }else{
                 alert(typeof(window.android));
              }
          }
         function setTitle(){
           if(window.layout!=null&&typeof(window.layout)!="undefined"){
                window.layout.isTitleBarShowing(function(responseData) {
                    document.getElementById("show").innerHTML = "send get responseData from java, data = " + responseData
                });
              }else{
                 alert(typeof(window.layout));
              }
          }

          function hideTitleBar(){
          if(window.layout!=null&&typeof(window.layout)!="undefined"){
                window.layout.hideTitleBar();
              }else{
                 alert(typeof(window.layout));
              }
          }
          function showTitleBar(){
          if(window.layout!=null&&typeof(window.layout)!="undefined"){
                window.layout.showTitleBar();
              }else{
                 alert(typeof(window.layout));
              }
          }
          function setTitleColor(){
          if(window.layout!=null&&typeof(window.layout)!="undefined"){
                window.layout.setTitleColor("#000000");
              }else{
                 alert(typeof(window.layout));
              }
          }

          function display(message){
                alert(message)
                showElement("Js收到消息-->方法callByAndroidParam被调用,参数:"+message);
          }
