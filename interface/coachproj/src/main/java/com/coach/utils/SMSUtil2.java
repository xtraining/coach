package com.coach.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.common.Constants.SMS_STATUS;
/**
 * 调用CTC-SMS API客户端接口
 * 
 */
public class SMSUtil2 {
	private static final Logger log = LoggerFactory.getLogger(SMSUtil2.class);
	@SuppressWarnings("rawtypes")
	public static SMS_STATUS send(String phone, String content) {
			String mtUrl=Config.getProperty("sms_url2");
			//操作命令、SP编号、SP密码，必填参数
	        String command = "MULTI_MT_REQUEST";
	        String spid = Config.getProperty("sms_spid2");
	        String sppassword = Config.getProperty("sms_password2");
	        //sp服务代码，可选参数，默认为 00
	        String spsc = Config.getProperty("sms_spsc2");
	        //源号码，可选参数
	        String sa = Config.getProperty("sms_sender2");
	        
	        String das = "86" + phone;
	        //目标号码组，必填参数
	        //下行内容以及编码格式，必填参数
	        int dc = 15;
	        String sm = encodeHexStr(dc, content);//下行内容进行Hex编码，此处dc设为15，即使用GBK编码格式
	
	        //组成url字符串
	        String smsUrl = mtUrl + "?command=" + command + "&spid=" + spid + "&sppassword=" + sppassword + "&spsc=" + spsc + "&sa=" + sa + "&das=" + das + "&sm=" + sm + "&dc=" + dc;
	        log.info("smsUrl = " + smsUrl);
	        //发送http请求，并接收http响应
	        String resStr = doGetRequest(smsUrl.toString());
	        log.info("resStr = " + resStr);
	
	        //解析响应字符串
	        HashMap pp = parseResStr(resStr);
	        if(pp != null && pp.get("mterrcode") != null && StringUtils.equals("000", pp.get("mterrcode").toString())){
	        	return SMS_STATUS.SENT_SUCCESS;
	        } else {
	        	return SMS_STATUS.SENT_FAILED;
	        }
	}
	
	private static String doGetRequest(String urlstr) {
        String res = null;
        try {
            URL url = new URL(urlstr);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Content-Type", "text/html; charset=GB2312");
            System.setProperty("sun.net.client.defaultConnectTimeout", "5000");//jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout", "10000"); //jdk1.4换成这个,读操作超时
            //httpConn.setConnectTimeout(5000);//jdk 1.5换成这个,连接超时
            //httpConn.setReadTimeout(10000);//jdk 1.5换成这个,读操作超时
            httpConn.setDoInput(true);
            int rescode = httpConn.getResponseCode();
            if (rescode == 200) {
                BufferedReader bfw = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                res = bfw.readLine();
            } else {
                res = "Http request error code :" + rescode;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return res;
    }
	 /** 
	 * Hex编码字符组
	 */
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	private static String encodeHexStr(int dataCoding, String realStr) {
	    String hexStr = null;
	
	    if (realStr != null) {
	        byte[] data = null;
	        try {
	            if (dataCoding == 15) {
	                data = realStr.getBytes("GBK");
	            } else if ((dataCoding & 0x0C) == 0x08) {
	                data = realStr.getBytes("UnicodeBigUnmarked");
	            } else {
	                data = realStr.getBytes("ISO8859-1");
	            }
	        } catch (UnsupportedEncodingException e) {
	            System.out.println(e.toString());
	        }
	
	        if (data != null) {
	            int len = data.length;
	            char[] out = new char[len << 1];
	            // two characters form the hex value.
	            for (int i = 0, j = 0; i < len; i++) {
	                out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
	                out[j++] = DIGITS[0x0F & data[i]];
	            }
	            hexStr = new String(out);
	        }
	    }
	    return hexStr;
	}
	 /**
	 * 将 短信下行 请求响应字符串解析到一个HashMap中
	 * @param resStr
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static HashMap parseResStr(String resStr) {
	    HashMap pp = new HashMap();
	    try {
	        String[] ps = resStr.split("&");
	        for (int i = 0; i < ps.length; i++) {
	            int ix = ps[i].indexOf("=");
	            if (ix != -1) {
	                pp.put(ps[i].substring(0, ix), ps[i].substring(ix + 1));
	            }
	        }
	    } catch (Exception e) {
	        System.out.println(e.toString());
	    }
	    return pp;
	}
}
