package com.zhubajie.service;

import com.alibaba.fastjson.JSON;
import com.zbj.matching.dto.task.domain.MkOrderDirectionDo;
import com.zbj.matching.dto.task.result.TaskDO;
import com.zbj.matching.service.task.TaskService;
import com.zhubajie.common.dto.Request;
import com.zhubajie.common.dto.Result;
import com.zhubajie.tool.tool.DubboConnect;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * Created by Administrator on 2018/6/11.
 */
public class Test_TaskService_getTaskDirection extends AbstractJavaSamplerClient {
    private String dubbourl;
    private String port;
    private String taskId;
    private DubboConnect dubboConnect = new DubboConnect();
    private TaskService taskService;

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
        if (taskService == null) {
            taskService = dubboConnect.getService(dubbourl, port, TaskService.class);
        }
    }

    //开始测试，从arg0参数可以获得参数值；
    public SampleResult runTest(JavaSamplerContext arg0) {
        taskId = arg0.getParameter("taskId");
        SampleResult sr = new SampleResult();
        sr.setSampleLabel("Test_TaskService_getTaskDirection");
        try {
            Integer task_id = Integer.valueOf(taskId);
            sr.setSamplerData(taskId);
            sr.setDataEncoding("UTF-8");
            Request<Integer> request = Request.create();
            request.setData(task_id);
            sr.sampleStart();// jmeter 开始统计响应时间标记
            Result<MkOrderDirectionDo> result = taskService.getTaskDirection(request);
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
