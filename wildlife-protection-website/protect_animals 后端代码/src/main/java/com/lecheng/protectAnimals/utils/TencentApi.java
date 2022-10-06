package com.lecheng.protectAnimals.utils;

import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.asr.v20190614.models.CreateRecTaskRequest;
import com.tencentcloudapi.asr.v20190614.models.CreateRecTaskResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tiia.v20190529.TiiaClient;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelRequest;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class TencentApi {
        /**
         * 图片标签识别
         * @param path
         * @return
         * @throws TencentCloudSDKException
         */
    public static String imgApi(String path) throws TencentCloudSDKException {
            try{

                    Credential cred = new Credential("AKIDpX2JL6NkrszyiBh8qHUK3h7nqlU2sTrP", "Pi4D9BzWQPAgyAYjplbDk9YYVO7VlomZ");

                    HttpProfile httpProfile = new HttpProfile();
                    httpProfile.setEndpoint("tiia.tencentcloudapi.com");

                    ClientProfile clientProfile = new ClientProfile();
                    clientProfile.setHttpProfile(httpProfile);

                    TiiaClient client = new TiiaClient(cred, "ap-guangzhou", clientProfile);

                    DetectLabelRequest req = new DetectLabelRequest();
                    req.setImageUrl(path);
//            req.setImageBase64("");
                    DetectLabelResponse resp = client.DetectLabel(req);
                    System.out.println(DetectLabelResponse.toJsonString(resp));
                    return DetectLabelResponse.toJsonString(resp);
            } catch (TencentCloudSDKException e) {
                    System.out.println(e.toString());
            }
            return "检测不出";
    }

    public static void voiceApi() throws IOException {
            //通过本地音频方式
            try{
                    //重要，此处<Your SecretId><Your SecretKey>需要替换成客户自己的账号信息，获取方法：
                    //请参考接口说明（https://cloud.tencent.com/document/product/1093/37139）中的使用步骤 1 进行获取。
                    Credential cred = new Credential("AKIDpX2JL6NkrszyiBh8qHUK3h7nqlU2sTrP", "Pi4D9BzWQPAgyAYjplbDk9YYVO7VlomZ");

                    HttpProfile httpProfile = new HttpProfile();
                    httpProfile.setEndpoint("asr.tencentcloudapi.com");

                    ClientProfile clientProfile = new ClientProfile();
                    clientProfile.setHttpProfile(httpProfile);

                    AsrClient client = new AsrClient(cred, "ap-shanghai", clientProfile);

                    String params = "{\"EngineModelType\":\"16k_zh\",\"ChannelNum\":1,\"ResTextFormat\":0,\"SourceType\":1}";
                    CreateRecTaskRequest req = CreateRecTaskRequest.fromJsonString(params, CreateRecTaskRequest.class);

                    File file = new File("/Users/ruskinli/eclipse-workspace/TencentSentence/src/test.wav");
                    FileInputStream inputFile = new FileInputStream(file);
                    byte[] buffer = new byte[(int)file.length()];
                    req.setDataLen(file.length());
                    inputFile.read(buffer);
                    inputFile.close();
                    String encodeData = Base64.getEncoder().encodeToString(buffer);
                    req.setData(encodeData);
                    CreateRecTaskResponse resp = client.CreateRecTask(req);
                    System.out.println(CreateRecTaskRequest.toJsonString(resp));
            } catch (TencentCloudSDKException e) {
                    System.out.println(e.toString());
            }

    }

}
