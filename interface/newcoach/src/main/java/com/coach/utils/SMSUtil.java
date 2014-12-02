package com.coach.utils;

import java.security.MessageDigest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinatricom.message.IDeliverMsg;
import com.chinatricom.message.IReportMsg;
import com.chinatricom.message.ISubmitMsg;
import com.chinatricom.slidewindow.SMSCallback;
/**
 * 调用CTC-SMS API客户端接口
 * 
 */
public class SMSUtil implements SMSCallback {
	private static final Logger log = LoggerFactory.getLogger(SMSUtil.class);
	private static String userName = Config.getProperty("sms_username"); // 接口账号
	private static String password = Config.getProperty("sms_password");// 密码
	private static String sign = "";// 短信签名，该签名必须提前报备，管理平台已设置
	private static String msgid ="";	//自定义msgid
	private static String subcode ="";	//扩展字号
	private static String sendtime =""; //定时发送时间，时间格式201305051230
	private static String url = Config.getProperty("sms_url"); //三网通使用地址
	
	public static void main(String[] args) {
		// 发送短信
		log.debug("*************发送短信*************");
		send("13636426042", "哇哈哈哈");
		// 获取状态报告
		log.debug("*************状态报告*************");
		getReport();
		// 获取余额
		log.debug("*************获取余额*************");
		getBalance();	
	}

	// MD5加密函数
	private static String MD5Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	private static final String byte2hexString(byte[] bytes) {
		StringBuffer bf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				bf.append("0");
			}
			bf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return bf.toString();
	}

	// 发送短信  
	/**
	 * 发送短信方法使用document 方法封装XML字符串
	 */
	public static void send(String phone, String content) {
		String posturl = url+"/http/sms/Submit";
		Map<String, String> params = new LinkedHashMap<String, String>();
		SMSUtil docXml = new SMSUtil();
		String message=docXml.DocXml(userName, MD5Encode(password), msgid, phone, content, sign, subcode, sendtime);
		log.debug(message);
		params.put("message", message);
		String resp = doPost(posturl, params);
		log.info(resp);
		getReport();
	}
	// 状态报告
	private static void getReport() {
		String posturl = url+"/http/sms/Report";
		Map<String, String> params = new LinkedHashMap<String, String>();
		String message = "<?xml version='1.0' encoding='UTF-8'?><message>"
				+ "<account>" + userName + "</account>" + "<password>"
				+ MD5Encode(password) + "</password>"
				+ "<msgid></msgid><phone></phone></message>";
		params.put("message", message);
		String resp = doPost(posturl, params);
		log.info(resp);
	}

	// 查询余额
	private static void getBalance() {
		String posturl = url+"/http/sms/Balance";
		Map<String, String> params = new LinkedHashMap<String, String>();
		String message = "<?xml version='1.0' encoding='UTF-8'?><message><account>"
				+ userName
				+ "</account>"
				+ "<password>"
				+ MD5Encode(password)
				+ "</password></message>";
		params.put("message", message);
		log.debug(message);
		String resp = doPost(posturl, params);
		log.debug(resp);
	}

	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的HTML
	 */
	private static String doPost(String url, Map<String, String> params) {
		String response = null;
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		// 设置Post数据
		if (!params.isEmpty()) {
			int i = 0;
			NameValuePair[] data = new NameValuePair[params.size()];
			for (Entry<String, String> entry : params.entrySet()) {
				data[i] = new NameValuePair(entry.getKey(), entry.getValue());
				i++;
			}
			postMethod.setRequestBody(data);
		}
		try {
			client.executeMethod(postMethod);
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return response;
	}
	/**
	 * 使用document 对象封装XML
	 */
	private  String DocXml(String userName,String pwd,String msgid,String  phone,String contents,String sign,String  subcode,String sendtime) {
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		Element message = doc.addElement("message");
		Element account = message.addElement("account");
		account.setText(userName);
		Element password = message.addElement("password");
		password.setText(pwd);
		Element msgid1 = message.addElement("msgid");
		msgid1.setText(msgid);
		Element phones = message.addElement("phones");
		phones.setText(phone);
		Element content = message.addElement("content");
		content.setText(contents);
		Element sign1 = message.addElement("sign");
		sign1.setText(sign);
		Element subcode1 = message.addElement("subcode");
		subcode1.setText(subcode);
		Element sendtime1 = message.addElement("sendtime");
		sendtime1.setText(sendtime);
		return message.asXML();
		}

	@Override
	public boolean onDeliverSMS(IDeliverMsg arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onMTReportSMS(IReportMsg arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onSubmitedSMS(ISubmitMsg arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
