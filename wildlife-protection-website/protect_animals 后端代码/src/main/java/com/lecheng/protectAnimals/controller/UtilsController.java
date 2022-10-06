package com.lecheng.protectAnimals.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lecheng.protectAnimals.alipay.config.AlipayConfig;
import com.lecheng.protectAnimals.pojo.AliPay;
import com.lecheng.protectAnimals.pojo.LatAndLng;
import com.lecheng.protectAnimals.pojo.ResponseMessage;
import com.lecheng.protectAnimals.pojo.User;
import com.lecheng.protectAnimals.service.LatAndLngService;
import com.lecheng.protectAnimals.service.UserService;
import com.lecheng.protectAnimals.service.VolunteerOrgService;
import com.lecheng.protectAnimals.utils.BaiduApi;
import com.lecheng.protectAnimals.utils.CreateUUID;
import com.lecheng.protectAnimals.utils.TencentApi;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/util")
public class UtilsController {
    @Autowired
    private UserService userService;
    @Autowired
    private LatAndLngService latAndLngService;
    @Autowired
    private VolunteerOrgService volunteerOrgService;
    private static Logger logger = Logger.getLogger(UtilsController.class);

    /**
     * 上传头像图片并保存路径到数据库
     *
     * @param file
     * @param user
     * @return
     * @throws IOException
     */
    @RequestMapping("/uploadHeader_img")
    public ResponseMessage uploadHeaderImg(@RequestParam("file") MultipartFile file, HttpServletRequest req, User user) throws IOException {
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        //上传路径保存设置
        String path = req.getServletContext().getRealPath("/cache");
        System.out.println("看着里！！！！！！！！！！！！！图片储存路径：" + path);
        try {
            if (!file.isEmpty()) {
                File rootFile = new File(path);
                if (!rootFile.exists()) {
                    rootFile.mkdirs();
                }
                String img_name = CreateUUID.create() + ".jpg";
                File file2 = new File(rootFile, img_name);
                file.transferTo(file2);
                user.setHead_img("http://47.108.95.77/protect_animals/cache/" + img_name);
                userService.insertUserImg(user);
                return responseMessage.Success(user.getHead_img());
            } else return responseMessage.Remind("没有图片上传");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传头像失败");
            return responseMessage.False("上传头像失败");
        }

    }

    /**
     * 上传图片
     *
     * @param file
     * @param req
     * @return
     */
    @RequestMapping("/uploadImg")
    public ResponseMessage uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            String path = req.getServletContext().getRealPath("/cache");
            System.out.println("看着里！！！！！！！！！！！！！图片储存路径：" + path);
            if (!file.isEmpty()) {
                File rootFile = new File(path);
                if (!rootFile.exists()) {
                    rootFile.mkdirs();
                }
                String img_name = CreateUUID.create() + ".jpg";
                File file2 = new File(rootFile, img_name);
                file.transferTo(file2);
                return responseMessage.Success("http://47.108.95.77/protect_animals/cache/" + img_name);
            } else return responseMessage.Remind("没有图片上传");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传图片失败");
            return responseMessage.False("图片上传失败");
        }
    }

    /**
     * 识别本地动物图片中动物的详细信息
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/baiduApi")
    public ResponseMessage qqAip(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest req) throws IOException {
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            String path = req.getServletContext().getRealPath("/cache");
            if (!file.isEmpty()) {
                File rootFile = new File(path);
                if (!rootFile.exists()) {
                    rootFile.mkdirs();
                }
                String img_name = CreateUUID.create() + ".jpg";
                File file2 = new File(rootFile, img_name);
                file.transferTo(file2);
                JSONObject json = BaiduApi.imgApi(path + "/" + img_name);
                File file1 = new File(path + "/" + img_name);
                file1.delete();
                String temp = json.getJSONArray("result").get(0).toString();
                com.alibaba.fastjson.JSONObject res = com.alibaba.fastjson.JSONObject.parseObject(temp);
                com.alibaba.fastjson.JSONObject res2 = com.alibaba.fastjson.JSONObject.parseObject(res.getString("baike_info"));
                HashMap<Object, Object> map = new HashMap<>();
                map.put("name", res.getString("name"));
                map.put("baike_url", res2.getString("baike_url"));
                map.put("image_url", res2.getString("image_url"));
                map.put("description", res2.getString("description"));
                return responseMessage.Success(map);
            } else return responseMessage.False("没有发现图片");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("识别本地动物图片中动物失败");
            return responseMessage.False();
        }
    }

    /**
     * 通过图片地址识别动物标签
     *
     * @param path
     * @return
     */
    @RequestMapping("/tencentApi")
    public ResponseMessage tencentAip(String path) {
        logger.info("tencentAip-path:" + path);
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            return responseMessage.Success(TencentApi.imgApi(path));
        } catch (TencentCloudSDKException e) {
            return responseMessage.False("通过图片地址识别动物标签失败");
        }
    }

    /**
     * 定位最近的前三个动物救助所
     *
     * @param latAndLng
     * @return
     */
    @RequestMapping("/latAndLng")
    public ResponseMessage latAndLng(LatAndLng latAndLng) {
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            List<LatAndLng> addressInfo = latAndLngService.getAddressInfo(latAndLng);
            if (addressInfo.size() > 0) {
                responseMessage.setData(addressInfo);
                responseMessage.setMsg("获取成功");
                responseMessage.setCode(200);
            } else {
                responseMessage.setData(addressInfo);
                responseMessage.setMsg("暂未发现");
                responseMessage.setCode(200);
            }
            return responseMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return responseMessage.False("latAndLng异常");
        }
    }

    /**
     * 志愿活动资金募集,支付宝沙箱模拟支付
     *
     * @param aliPay
     * @return
     */
    @RequestMapping("/pay")
    public ResponseMessage pay(AliPay aliPay) {
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        System.out.println("aliPay.getWIDsubject():" + aliPay.getWIDsubject());
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + aliPay.getWIDout_trade_no() + "\","
                + "\"total_amount\":\"" + aliPay.getWIDtotal_amount() + "\","
                + "\"subject\":\"" + aliPay.getWIDsubject() + "\","
                + "\"body\":\"" + aliPay.getWIDbody() + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        try {
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            logger.info(result);
            String s = HtmlUtils.htmlEscapeHex(result);
            HashMap<Object, Object> map = new HashMap<>();
            map.put("WIDtotal_amount", 20);
            map.put("id", aliPay.getId());
            if (volunteerOrgService.addMoneyById(map)) {
                return responseMessage.Success(s);
            }
            return responseMessage.False();
        } catch (AlipayApiException e) {
            logger.error(e.getErrCode());
            return responseMessage.False("付款失败！");
        }
    }

    /**
     * 沙箱支付验签
     *
     * @param request
     * @throws UnsupportedEncodingException
     * @throws AlipayApiException
     */
    @RequestMapping("/return_url")
    public ResponseMessage notify_url(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified =
                AlipaySignature.rsaCheckV1
                        (params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名


        if (signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            logger.info("trade_no:" + trade_no + "out_trade_no:" + out_trade_no + "total_amount:" + total_amount);
            return responseMessage.Remind("success");
        } else {
            logger.error("验签失败");
            return responseMessage.False("验签失败");
        }
    }
}
