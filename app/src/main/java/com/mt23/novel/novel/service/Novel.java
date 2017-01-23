package com.mt23.novel.novel.service;


import com.mt23.novel.utils.Promise.Promiser;
import com.mt23.novel.utils.Promise.PromiserRejecter;
import com.mt23.novel.utils.log.MT23Log;

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
    private String summary;
    private String type;
    private String updateTime;
    private int status;
    private int lastChapterCount=0;
    private boolean isDownload = false;
    private boolean isCheckUpdateChapte=false;
    private Long lastReadTime=0l;
    private int currentChapterIndex = 0;
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
    public Promiser<Chapter,PromiserRejecter> getCurrentChapter(){
        Promiser<Chapter,PromiserRejecter> promiser = new Promiser<>(new Promiser.PromiseInitializer<Chapter, PromiserRejecter>() {
            @Override
            public void run(Promiser.Resolver<Chapter> resolve, Promiser.Rejecter<PromiserRejecter> reject) {

                if(null != chapterList)
                {

                    if (chapterList.size()-1>currentChapterIndex)
                    {
                        resolve.run(chapterList.get(currentChapterIndex));
                        MT23Log.d("获取"+getName()+"章节，个数："+chapterList.size());
                    }
                    else
                    {
                        MT23Log.e(getName()+":章节列表的索引错误"+"，章节个数："+chapterList.size()+" 当前索引："+currentChapterIndex);
                        reject.run(new PromiserRejecter(501,"章节列表的索引错误"));
                    }
                }
                else
                {
                    MT23Log.d(getName()+"章节列表为null，正在加载");
                    NovelResourceManager.getInstance()
                            .loadNovelCatalog(Novel.this)
                            .success((List<Chapter> chapters)->{
                                MT23Log.d(getName()+",章节加载完成,章节个数："+chapters.size());
                                chapterList = chapters;

                                if (chapterList.size()-1>currentChapterIndex)
                                {
                                    resolve.run(chapterList.get(currentChapterIndex));
                                    MT23Log.d("获取"+getName()+"章节，个数："+chapterList.size());
                                }
                                else
                                {
                                    MT23Log.e(getName()+":章节列表的索引错误"+"，章节个数："+chapterList.size()+" 当前索引："+currentChapterIndex);
                                    reject.run(new PromiserRejecter(501,"章节列表的索引错误"));
                                }

                            })
                            .error((String err)->{
                                MT23Log.e(getName()+"获取章节列表失败："+err);
                            })
                            ;
                }

            }
        });
        return promiser;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLastChapterCount() {
        return lastChapterCount;
    }

    public void setLastChapterCount(int lastChapterCount) {
        this.lastChapterCount = lastChapterCount;
    }

    public boolean isDownload() {
        return isDownload;
    }

    public void setDownload(boolean download) {
        isDownload = download;
    }

    public boolean isCheckUpdateChapte() {
        return isCheckUpdateChapte;
    }

    public void setCheckUpdateChapte(boolean checkUpdateChapte) {
        isCheckUpdateChapte = checkUpdateChapte;
    }

    public Long getLastReadTime() {
        return lastReadTime;
    }

    public void setLastReadTime(Long lastReadTime) {
        this.lastReadTime = lastReadTime;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {

        this.chapterList = chapterList;
    }
    private void loadChapters()
    {

    }
}
