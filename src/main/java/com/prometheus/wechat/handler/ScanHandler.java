package com.prometheus.wechat.handler;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class ScanHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        // todo 扫码事件处理     可以在这里根据 扫描到的条形码 去数据库对比
        this.logger.info("\n接收到请求消息，内容：【{}】", wxMpXmlMessage.toString());
        this.logger.info("\n接收到请求消息，map：【{}】", map);
        System.out.println("wxMessage:" + wxMpXmlMessage );
        System.out.println("map:" + map);
        return null;
    }
}
