package com.zhubajie.test;

import com.alibaba.fastjson.JSONObject;
import com.zhubajie.tool.tool.SingUtils;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.util.HashMap;
import java.util.Map;

public class ContractInfo extends AbstractJavaSamplerClient{
    private String secret;
    private String url;
    private String contractNo;

    @Override
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("url","http://49.4.69.48/zwork/contractInfo");
        params.addArgument("secret","9f1cd5f4d8e46e7cae43e5dcb8b5ff2c");
        params.addArgument("contractNo","2018-06-26-001");
        return params;
    }

    @Override
    public void setupTest(JavaSamplerContext arg0) {
        this.secret=arg0.getParameter("secret");
        this.url=arg0.getParameter("url");
        this.contractNo=arg0.getParameter("contractNo");
    }

    @Override
    public SampleResult runTest(JavaSamplerContext arg0) {
        SampleResult sr = new SampleResult();
        Map<String,String> params = new HashMap<>();
        params.put("contractNo",arg0.getParameter("contractNo"));
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

    @Override
    public void teardownTest(JavaSamplerContext arg0) {

    }

    public static void main(String[] args) {
        ContractInfo cp=new ContractInfo();
        JavaSamplerContext arg0 = new JavaSamplerContext(cp.getDefaultParameters());
        cp.runTest(arg0);
    }
}
