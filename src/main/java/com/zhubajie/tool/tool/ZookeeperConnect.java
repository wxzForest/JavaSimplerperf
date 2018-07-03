package com.zhubajie.tool.tool;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * Created by Administrator on 2016/12/23.
 */
public class ZookeeperConnect {
    public <T extends Object> T getService(String zookeeper,Class<T> clazzs, String... version) {
//        String host = "192.168.143.34:2181";
//        String host = "172.26.20.162:2181";
        String host = zookeeper+":2181";
        Class<?> clazz = clazzs;
        ApplicationConfig application = new ApplicationConfig();
        application.setName("yudong_config");
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://" + host);
        registry.setUsername("test-cat");
        registry.setCheck(false);
        ReferenceConfig<T> reference = new ReferenceConfig<T>();
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setTimeout(10000);
        reference.setInterface(clazz);
        if(version != null && version.length !=0 ) {
            reference.setVersion(version[0]);
        }
        reference.setTimeout(10000);
        reference.setInterface(clazz);

        T t = reference.get();
        return t;
    }
}
