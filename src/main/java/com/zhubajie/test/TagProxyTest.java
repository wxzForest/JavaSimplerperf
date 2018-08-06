package com.zhubajie.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhubajie.common.dto.Result;
import com.zhubajie.works.center.dto.market.MkWorksDO;
import com.zhubajie.works.center.service.MkWorksService;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import com.zhubajie.tool.tool.SingUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagProxyTest extends AbstractJavaSamplerClient{

    private String secret;
    private String url;

    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("url","http://49.4.69.48/zwork/getTagList");
        params.addArgument("secret","9f1cd5f4d8e46e7cae43e5dcb8b5ff2c");
        return params;
    }

    public void setupTest(JavaSamplerContext arg0) {
        this.secret=arg0.getParameter("secret");
        this.url=arg0.getParameter("url");
    }


    public SampleResult runTest(JavaSamplerContext arg0) {
        SampleResult sr = new SampleResult();
        Map<String,String> params = new HashMap<>();
        params.put("sign", SingUtils.sign(params,arg0.getParameter("secret")));
        JSONObject jsonObject = null;
        jsonObject = SingUtils.requestHttpPostWithoutEncryptNew(arg0.getParameter("url"), params);
        if (null == jsonObject) {
            sr.setResponseData("结果是空", "UTF-8");
            sr.setDataType(SampleResult.TEXT);
            sr.setSuccessful(false);
            System.out.println("结果是空");
            return sr;
        }
        String res=com.alibaba.fastjson.JSONObject.toJSONString(jsonObject);
        sr.setResponseData("结果是：" + res, "UTF-8");
        sr.setDataType(SampleResult.TEXT);
        System.out.println(res);
        sr.setSuccessful(true);
        return sr;
    }

    public void teardownTest(JavaSamplerContext arg0) {
        // System.out.println(end);
        // System.out.println("The cost is"+(end-start)/1000);
    }

    public static void main(String[] args) {
        TagProxyTest tp=new TagProxyTest();
        JavaSamplerContext arg0 = new JavaSamplerContext(tp.getDefaultParameters());
        tp.runTest(arg0);
    }
}
