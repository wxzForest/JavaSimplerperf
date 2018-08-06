/**
 * Copyright (C) 2015-2016 ZhuBaJie NetWork Co.,Ltd.
 * ZhuBaJie Basic Core System Source Code. Version 1.0
 * Created by ZhangYu (zhangyuf@zbj.com)) on 2018年07月13日.
 */
package com.zhubajie.tool.tool;

import java.util.List;

/**
 * @title 标签对象
 * @author ZhangYu (zhangyuf@zbj.com)
 */
public class Tag {
    /**
     * 标签ID
     */
    private Integer id;

    /**
     * 标签名称
     */
    private String  tagName;

    /**
     * 子标签列表
     */
    private List<Tag> eboxSonTags;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Tag> getEboxSonTags() {
        return eboxSonTags;
    }

    public void setEboxSonTags(List<Tag> eboxSonTags) {
        this.eboxSonTags = eboxSonTags;
    }
}
