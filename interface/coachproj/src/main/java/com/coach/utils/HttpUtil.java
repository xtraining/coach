package com.coach.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class HttpUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	public static String getServer(String url, String queryString){
		return getServer(url, queryString, false);	
	}
	
    /** 
     * 执行一个HTTP GET请求，返回请求响应的HTML 
     * 
     * @param url 请求的URL地址 
     * @param queryString 请求的查询参数,可以为null 
     * @return 返回请求响应的HTML 
     */ 
    public static String getServer(String url, String queryString, boolean useProxy) { 
            String response = null; 
            HttpClient client = new HttpClient(); 
            if(useProxy){
    	        //设置HTTP代理IP和端口
    	        client.getHostConfiguration().setProxy("192.168.19.9", 80);
    	        //代理认证
    	        UsernamePasswordCredentials creds = new UsernamePasswordCredentials("fanfangming", "abcd_123");
    	        client.getState().setProxyCredentials(AuthScope.ANY, creds);
            }
            HttpMethod method = new GetMethod(url); 
            try { 
                if (StringUtils.isNotBlank(queryString)) 
                    method.setQueryString(URIUtil.encodeQuery(queryString)); 
                	client.executeMethod(method); 
                if (method.getStatusCode() == HttpStatus.SC_OK) { 
                    response = method.getResponseBodyAsString(); 
                } 
            } catch (URIException e) { 
            } catch (IOException e) { 
            } finally { 
            	method.releaseConnection(); 
            } 
            return response; 
    }
	
	public static String postServer(String url, Map<String, String> params){
		return postServer(url, params, false);	
	}
	
	public static String postServer(String url, Map<String, String> params, boolean useProxy){
		StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        if(useProxy){
	        //设置HTTP代理IP和端口
	        client.getHostConfiguration().setProxy("192.168.19.9", 80);
	        //代理认证
	        UsernamePasswordCredentials creds = new UsernamePasswordCredentials("fanfangming", "abcd_123");
	        client.getState().setProxyCredentials(AuthScope.ANY, creds);
        }
        PostMethod postMethod = new PostMethod(url);
        //表单域的值
        NameValuePair[] data = new NameValuePair[params.size()];
        Iterator<String> it = params.keySet().iterator();
        int i = 0;
        while(it.hasNext()){
        	String key = it.next();
        	String value = params.get(key);
        	data[i] = new NameValuePair(key, value);
        	i++;
        }
        postMethod.setRequestBody(data);
        //解决中文乱码问题
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
        try {
            int statusCode = client.executeMethod(postMethod);
            System.out.println("statusCode:"+statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                    postMethod.getResponseBodyAsStream(), "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
            }
        } catch (Throwable e) {
            logger.error("Post Third Party Failed", e);
        }finally {
            postMethod.releaseConnection();
        }
        return response.toString();
    }
}
