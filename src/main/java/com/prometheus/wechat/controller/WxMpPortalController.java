package com.prometheus.wechat.controller;

/**
 * @description: TODO
 * @author: ChenZhi
 * @create: 2020/1/8 15:25
 * @version: 1.0
 **/


import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @ClassName: WxMpPortalController
 * @Description: 微信主入口
 * @author CaoWenCao
 * @date 2018年6月12日 下午10:13:57
 */
@RestController
@RequestMapping("/wechat/portal")
public class WxMpPortalController {
    /*@Autowired
    private WeixinService wxService;
    @Autowired
    private WXLogService wXLogService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    *//*
     * 微信验签
     *//*
    @ResponseBody
    @GetMapping(produces = "text/plain;charset=utf-8")
    public String authGet(@RequestParam(name = "signature", required = false) String signature, @RequestParam(name = "timestamp", required = false) String timestamp, @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echostr) {
        this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        if (this.getWxService().checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }

        return "非法请求";
    }

    *//*
     * 消息转发---中转站 每次微信端的消息都会来到这里进行分发 对微信公众号相关的一些动作，都以报文形式推送到该接口，根据请求的类型，进行路由分发处理
     *//*
    @ResponseBody
    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String post(@RequestBody String requestBody, @RequestParam("signature") String signature, @RequestParam(name = "encrypt_type", required = false) String encType, @RequestParam(name = "msg_signature", required = false) String msgSignature,
                       @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce) {
        this.logger.info("\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}]," + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ", signature, encType, msgSignature, timestamp, nonce, requestBody);

        if (!this.wxService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        String out = null;
        if (encType == null) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            // 如果是明文传输 ，保存 微信接收 信息,保存到数据库，用于微信消息管理中的客服互动
            wXLogService.doSaveReceiveLog(inMessage);
            //this.logger.info("\n保存微信接受信息WxMpXmlMessage：\n{}", inMessage);
            WxMpXmlOutMessage outMessage = this.getWxService().route(inMessage);
            if (outMessage == null) {
                return "";
            }

            out = outMessage.toXml();
            // 日志出口时，保存微信发出去的XML（给用户）
            wXLogService.doSaveMsgLogOut(outMessage);
            //this.logger.info("\n保存微信输出日志WxMpXmlOutMessage信息：\n{}", outMessage);

        }
        else if ("aes".equals(encType)) {
            // aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody, this.getWxService().getWxMpConfigStorage(), timestamp, nonce, msgSignature);
            this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
            // 如果是密文传输 ，保存 微信接收 信息
            wXLogService.doSaveReceiveLog(inMessage);
            WxMpXmlOutMessage outMessage = this.getWxService().route(inMessage);
            if (outMessage == null) {
                return "";
            }
            out = outMessage.toEncryptedXml(this.getWxService().getWxMpConfigStorage());
            // 日志出口时，保存微信发出去的XML（给用户）
            wXLogService.doSaveMsgLogOut(outMessage);
            this.logger.info("\n保存微信输出日志WxMpXmlOutMessage信息：\n{}", outMessage);
            // 保存回复消息记录
            //wXLogService.doSaveReplyLog(outMessage);
        }

        this.logger.debug("\n组装回复信息：{}", out);

        return out;
    }

    protected WeixinService getWxService() {
        return this.wxService;
    }*/

}