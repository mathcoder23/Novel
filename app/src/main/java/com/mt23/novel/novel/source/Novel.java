package com.mt23.novel.novel.source;

/**
 * Created by mathcoder23 on 16/11/3.
 */
public class Novel {
    private String name;
    private String author;
    private String summary;
    private String url;
    private String imgUrl;
    private String type;
    private String updateTime;
    private String lastChapter;
    private String lastChapterUrl;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public String getLastChapterUrl() {
        return lastChapterUrl;
    }

    @Override
    public String toString() {
        return "Novel{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", summary='" + summary + '\'' +
//                ", url='" + url + '\'' +
//                ", imgUrl='" + imgUrl + '\'' +
//                ", type='" + type + '\'' +
//                ", updateTime='" + updateTime + '\'' +
//                ", lastChapter='" + lastChapter + '\'' +
//                ", lastChapterUrl='" + lastChapterUrl + '\'' +
                '}';
    }

    public void setLastChapterUrl(String lastChapterUrl) {
        this.lastChapterUrl = lastChapterUrl;
    }

}
