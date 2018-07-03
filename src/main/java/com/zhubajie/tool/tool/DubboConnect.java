package com.zhubajie.tool.tool;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;

/**
 * Created by Administrator on 2017/7/6.
 */
public class DubboConnect {
    public <T extends Object> T getService(String dubbourl,String port,Class<T> clazzs, String... version) {
        String dubbourlhost = "";
        String url = "dubbo://"+dubbourl+":"+port+"/"+clazzs.getName();
        ApplicationConfig application = new ApplicationConfig();
        application.setName("yudong_config");
//        Class<?> clazz = TaskOptService.class;
        ReferenceConfig<T> reference = new ReferenceConfig<T>();
        reference.setUrl(url);
        reference.setInterface(clazzs);
        reference.setTimeout(10000);
        reference.setVersion("");
        reference.setApplication(application);
        if(version != null && version.length !=0 ) {
            reference.setVersion(version[0]);
        }
        T t = reference.get();
        return t;
    }


}
