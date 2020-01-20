package com.prometheus.wechat.controller;

import com.prometheus.framework.web.domain.AjaxResult;
import com.prometheus.wechat.utils.Sha1Util;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

@Controller
@RequestMapping("/weChat")
public class WxConfigController {
    @Autowired
    protected WxMpService wxMpService;


    /**
     * @param url url
     */
    @PostMapping("/getConfig")
    @ResponseBody
    public AjaxResult getConfig(HttpServletResponse response, @RequestParam(value = "url") String url) {
        String jsapiTicket = "";
        try {
            jsapiTicket = this.wxMpService.getJsapiTicket();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        AjaxResult ajaxResult = AjaxResult.success();
        String nonce_str = Sha1Util.getNonceStr();
        String timestamp = Sha1Util.getTimeStamp();
        // 注意这里参数名必须全部小写，且必须有序
        String string1 = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
        String signature = "";
        try {
            // 微信支付配置SHA-1
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("获取配置出错，" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("获取配置出错，" + e.getMessage());
        }
        // 返回
        ajaxResult.put("url", url);
        ajaxResult.put("jsapi_ticket", jsapiTicket);
        ajaxResult.put("nonceStr", nonce_str);
        ajaxResult.put("timestamp", timestamp);
        ajaxResult.put("signature", signature);
        ajaxResult.put("appid", this.wxMpService.getWxMpConfigStorage().getAppId());
        return ajaxResult;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }


}
