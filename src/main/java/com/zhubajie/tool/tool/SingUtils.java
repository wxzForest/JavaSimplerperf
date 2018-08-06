package com.zhubajie.tool.tool;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.*;

public class SingUtils {

    private static final String UTF8 = "UTF-8";
    private static Logger logger = LoggerFactory.getLogger(SingUtils.class);
    private static String safariUserAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2";


    /**
     * 签名算法
     * @title 签名算法
     * @desc 使用  secret  对paramValues按以下算法进行签名
     *       uppercase(hex(sha1(secretkey1value1key2value2...secret))
     * @param paramValues 请求参数
     * @param
     * @return String 签名串
     */
    public static String sign(Map<String, String> paramValues, String secret) {
        try {
            StringBuilder sb = new StringBuilder();
            List<String> paramNames = new ArrayList<String>(paramValues.size());
            paramNames.addAll(paramValues.keySet());
            Collections.sort(paramNames);
            sb.append(secret);
            for (String paramName : paramNames) {
                sb.append(paramName).append(paramValues.get(paramName));
            }
            sb.append(secret);
            byte[] sha1Digest = getSHA1Digest(sb.toString());
            return byte2hex(sha1Digest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static byte[] getSHA1Digest(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            bytes = md.digest(data.getBytes(UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.getMessage());
        }
        return bytes;
    }
    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toLowerCase());
        }
        return sign.toString();
    }

    /**
     * 发送http请求
     *
     * @param url
     * @param param
     * @return
     * @author Spurs
     * @date 2018年03月14日
     */
    public static com.alibaba.fastjson.JSONObject requestHttpPostWithoutEncryptNew(String url, Map<String, String> param) {
        com.alibaba.fastjson.JSONObject result = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = HttpClients.createDefault();
            HttpPost request = new HttpPost(url);
            request.setHeader("Content-Type", "application/x-www-form-urlencoded");
            request.setHeader("User-Agent", SingUtils.safariUserAgent);
            List<NameValuePair> parms = new ArrayList<NameValuePair>();
            if (param != null) {
                Iterator<Map.Entry<String, String>> iterator = param.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> item = iterator.next();
                    parms.add(new BasicNameValuePair(item.getKey(), item.getValue()));
                }
            }
            // 创建UrlEncodedFormEntity对象
            UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(parms, "UTF-8");
            request.setEntity(formEntiry);
            // 执行请求
            response = client.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String jsonString = EntityUtils.toString(entity, "UTF-8");
                logger.info("{}", jsonString);
                result = com.alibaba.fastjson.JSONObject.parseObject(jsonString);
            }
        } catch (Exception e) {
            logger.error("调用HTTP请求接口异常: ," + SingUtils.class.getCanonicalName() + ",ERROR=", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {

            }
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {

            }
        }
        return result;
    }
}
