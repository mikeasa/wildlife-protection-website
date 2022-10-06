package com.lecheng.protectAnimals.utils;


import com.baidu.aip.imageclassify.AipImageClassify;
import org.json.JSONObject;

import java.util.HashMap;

public class BaiduApi {
    //设置APPID/AK/SK
    public static final String APP_ID = "23392097";
    public static final String API_KEY = "ZwFjsWUCtzEcWKMCESp4mShN";
    public static final String SECRET_KEY = "tcMgT0Rj5YUx68nPCn3URplmKMgMdFbE";

    public static JSONObject imgApi(String path) {
        // 初始化一个AipImageClassify
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
//            client.setConnectionTimeoutInMillis(2000);
//            client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//            client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//            client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("top_num", "1");
        options.put("baike_num", "1");

        // 参数为本地路径
        String image = path;
        org.json.JSONObject res = client.animalDetect(image, options);
        return res;
    }
}
