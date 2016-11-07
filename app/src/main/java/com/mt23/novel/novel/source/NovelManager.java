package com.mt23.novel.novel.source;

/**
 * Created by mathcoder23 on 16/11/3.
 */
public interface NovelManager {
    void SearchNovel(Novel novel, SearchCallBack searchCallBack);
    void NovelChapterList(Novel novel,ChapterListCallBack chapterListCallBack);
}
