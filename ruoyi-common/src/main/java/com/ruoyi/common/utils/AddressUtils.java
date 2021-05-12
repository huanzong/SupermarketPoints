package com.ruoyi.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.json.JSON;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.utils.http.HttpUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取地址类
 */
public class AddressUtils {
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

//    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    private static final String UNKNOWN = "unknown";

    public static String getRealAddressByIP(String ip) {
        String address = "XX XX";
        if (Global.isAddressEnabled()) {
//            String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip);
//            if (StringUtils.isEmpty(rspStr)) {
//                log.error("获取地理位置异常 {}", ip);
//                return address;
//            }
//            JSONObject obj;
//            try {
//                obj = JSON.unmarshal(rspStr, JSONObject.class);
//                JSONObject data = obj.getObj("data");
//                String region = data.getStr("region");
//                String city = data.getStr("city");
//                address = region + " " + city;
//            } catch (Exception e) {
//                log.error("获取地理位置异常 {}", ip);
//            }
        }
        return address;
    }


    /**
     * 获取ip的方法
     *
     * @param request request
     * @return 返回
     */
    private static String getIpAddress(HttpServletRequest request) {
        if (request == null) {
            return UNKNOWN;
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        String localIp = "127.0";
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            localIp = localIp + ".0.1";
            if (localIp.equals(ip)) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("获取IP地址出现异常:{}", e.getMessage(), e);
                }
                if (inet != null) {
                    ip = inet.getHostAddress();
                }
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15 && ip.indexOf(',') > 0) {
            ip = ip.substring(0, ip.indexOf(','));
        }
        return ip;
    }
}
