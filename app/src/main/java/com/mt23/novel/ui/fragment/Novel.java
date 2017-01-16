package com.mt23.novel.ui.fragment;

import com.mt23.novel.novel.source.Chapter;

import java.util.List;

/**
 * Created by mathcoder23 on 1/16/17.
 */
public class Novel {
    /*
    Novel：
String name，小说名
String author，小说作者
String url，小说目录地址
String localUrl，小说目录本地地址
String coverImgUrl，封面图片地址
Int status，小说状态，连载，完结（保留字段）
List<Chapter> chapterList,章节列表
Boolean isDownload=false，是否下载小说至本地
Boolean isCheckUpdateChapte=falser，是否自动检测章节
Int lastChapterCount=0，最新章节个数
Chapter currentReadChapter，当前阅读章节
Long lastReadTime=0，最后一次阅读的时间
Float getReadProgress(),获取阅读进度
Int getNovelPrice()，获取小说价格
Chapter NextChapter()，下一章
Chapter PreChapter()，上一章
Chapter getCurrentChapter()，获取当前章节
     */
    private String name;
    private String author;
    private String url;
    private String localUrl;
    private String coverImgUrl;
    private int status;
    private int lastChapterCount=0;
    private boolean isDownload = false;
    private boolean isCheckUpdateChapte=false;
    private Long lastReadTime=0l;
    private List<Chapter> chapterList;
    public Float getReadProgress(){
        return 0f;
    }
    public int getNovelPrice(){
        return 0;
    }
    public Chapter NextChapter(){
        return null;
    }
    public Chapter PreChapter(){
        return null;
    }
    public Chapter getCurrentChapter(){
        return null;
    }


}
