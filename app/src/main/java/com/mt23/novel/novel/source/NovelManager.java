package com.mt23.novel.novel.source;

/**
 * Created by mathcoder23 on 16/11/3.
 * 小说管理接口提供查询小说，获取小说章节列表，获取小说章节对应的内容
 */
public interface NovelManager {
    void SearchNovel(Novel novel, SearchCallBack searchCallBack);
    void NovelChapterList(Novel novel,ChapterListCallBack chapterListCallBack);
    void NovelChapterContent(Chapter chapter,ChapterContentCallback chapterContentCallback);
}
