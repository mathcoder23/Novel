package com.mt23.novel.ui.fragment;

import com.mt23.novel.utils.Promise.Promiser;

/**
 * Created by mathcoder23 on 1/17/17.
 */
public class Chapter {
    /*
    Chapter
Int id,章节id
String name，章节名称
String text，章节内容
String url，章节地址
String localUrl，章节本地地址
Boolean isEnableRead=false,是否能阅读
Boolean isRead=false，是否阅读
Boolean isDownload=false，是否下载
     */
    private int id;
    private String name;
    private String content;
    private String url;
    private String localUrl;
    private boolean isReadable=false;
    private boolean isRead = false;
    private boolean isDownload=false;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Promiser<String,String> getContent() {
        return null;
    }

    public String getUrl() {
        return url;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public boolean isReadable() {
        return isReadable;
    }

    public boolean isRead() {
        return isRead;
    }

    public boolean isDownload() {
        return isDownload;
    }
}
