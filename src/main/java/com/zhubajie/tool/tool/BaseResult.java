/**
 * Copyright (C) 2015-2016 ZhuBaJie NetWork Co.,Ltd.
 * ZhuBaJie Basic Core System Source Code. Version 1.0
 * Created by ZhangYu (zhangyuf@zbj.com)) on 2018年03月16日.
 */
package com.zhubajie.tool.tool;

import java.io.Serializable;

/**
 * 企业盒子接口返回对象基类
 *
 * @title  企业盒子接口返回对象基类
 * @author ZhangYu (zhangyuf@zbj.com)
 */
public class BaseResult<T> implements Serializable {
    private static final long serialVersionUID = -4711757863930676989L;
    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 错误信息
     */
    private String  description;

    /**
     * 返回数据
     */
    private T data;


    public  static <T> BaseResult<T> create() {
        BaseResult result = new BaseResult();
        return  result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
