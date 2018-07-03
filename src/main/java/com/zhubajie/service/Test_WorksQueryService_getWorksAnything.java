package com.zhubajie.service;

import com.alibaba.fastjson.JSON;
import com.zhubajie.common.dto.Request;
import com.zhubajie.common.dto.Result;
import com.zhubajie.tool.tool.DubboConnect;
import com.zhubajie.trade.center.dto.market.MkTaskDO;
import com.zhubajie.trade.center.service.MkTaskService;
import com.zhubajie.works.manager.dto.MkWorksDTO;
import com.zhubajie.works.manager.dto.select.QueryWorksDO;
import com.zhubajie.works.manager.service.WorksQueryService;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/11.
 */
public class Test_WorksQueryService_getWorksAnything extends AbstractJavaSamplerClient {
    private String dubbourl;
    private String port;
    private String taskId;
    private DubboConnect dubboConnect = new DubboConnect();
    private WorksQueryService worksQueryService;

    // params.addArgument("dubbourl","");表示入参名字叫dubbourl，默认值为空。
    //设置可用参数及的默认值；
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("dubbourl", "");
        params.addArgument("port", "20889");
        params.addArgument("taskId", "13118925");
        return params;
    }

    //每个线程测试前执行一次，做一些初始化工作；
    public void setupTest(JavaSamplerContext arg0) {
        dubbourl = arg0.getParameter("dubbourl");
        port = arg0.getParameter("port");
        if (worksQueryService == null) {
            worksQueryService = dubboConnect.getService(dubbourl, port, WorksQueryService.class);
        }
    }

    //开始测试，从arg0参数可以获得参数值；
    public SampleResult runTest(JavaSamplerContext arg0) {
        taskId = arg0.getParameter("taskId");
        SampleResult sr = new SampleResult();
        sr.setSampleLabel("Test_WorksQueryService_getWorksAnything");
        try {
            Request<QueryWorksDO> request = new Request<QueryWorksDO>();
            QueryWorksDO queryWorksDO = new QueryWorksDO();
            List<String> fileds = new ArrayList<String>();
            List<String> condition = new ArrayList<String>();
            List<String> orderby = new ArrayList<String>();
            List<String> groupby = new ArrayList<String>();
            queryWorksDO.setPage(1);
            queryWorksDO.setPagesize(10);
            request.setData(queryWorksDO);
            fileds.add("works_id");
            fileds.add("task_id");
            fileds.add("user_id");
            fileds.add("createtime");
            fileds.add("issuccess");
            fileds.add("class");
            fileds.add("isallow");
            fileds.add("iseliminate");
            fileds.add("isalternative");
            fileds.add("content");
            fileds.add("hasimgfile");
            fileds.add("nickname");

            String task_id = "task_id=" + taskId;
            condition.add(task_id);
            orderby.add("issuccess DESC");
            orderby.add("iseliminate ASC");
            orderby.add("case when issuccess = 1 then 9999  when isallow > 1 then 1  else isallow end ASC");
            orderby.add("hasimgfile DESC");
            orderby.add("createtime ASC");
            queryWorksDO.setCondition(condition);
            queryWorksDO.setFileds(fileds);
            queryWorksDO.setOrderby(orderby);
            request.setData(queryWorksDO);

            sr.setSamplerData(JSON.toJSON(request).toString());
            sr.setDataEncoding("UTF-8");
            sr.sampleStart();// jmeter 开始统计响应时间标记
            Result<List<MkWorksDTO>> result = worksQueryService.getWorksAnything(request);
            // 通过下面的操作就可以将被测方法的响应输出到Jmeter的察看结果树中的响应数据里面了。
            sr.sampleEnd();// jmeter 结束统计响应时间标记
            System.out.println(JSON.toJSON(result));
            if (result.isSuccess()) {
                sr.setResponseData("结果是：" + JSON.toJSON(result), "UTF-8");
                sr.setDataType(SampleResult.TEXT);
                System.out.println(JSON.toJSON(result));
                sr.setSuccessful(true);
            } else {
                sr.setResponseData("结果是：" + JSON.toJSON(result), "UTF-8");
                sr.setDataType(SampleResult.TEXT);
                System.out.println(JSON.toJSON(result));
                sr.setSuccessful(false);

            }
        } catch (Throwable e) {
            sr.setSuccessful(false);
            e.printStackTrace();
        } finally {

        }
        return sr;
    }

    //测试结束时调用；
    public void teardownTest(JavaSamplerContext arg0) {
        // System.out.println(end);
        // System.out.println("The cost is"+(end-start)/1000);
    }

//    public static void main(String[] args) { // TODO Auto-generated method stub
//        Arguments params = new Arguments();
//        params.addArgument("dubbourl", "172.29.214.188");//设置参数，并赋予默认值1
//        params.addArgument("port", "20880");//设置参数，并赋予默认值2
//        params.addArgument("taskId", "18");//设置参数，并赋予默认值2
//        JavaSamplerContext arg0 = new JavaSamplerContext(params);
//        Test_MkTaskService_selectByPrimaryKey test = new Test_MkTaskService_selectByPrimaryKey();
//        test.setupTest(arg0);
//        test.runTest(arg0);
//        test.teardownTest(arg0);
//    }
}
