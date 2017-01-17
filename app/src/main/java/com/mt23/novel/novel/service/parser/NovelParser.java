package com.mt23.novel.novel.service.parser;

import com.mt23.novel.novel.service.Chapter;
import com.mt23.novel.novel.service.Novel;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by mathcoder23 on 1/10/17.
 */
public interface NovelParser {
    List<Novel> parserNovelList(Document dom);
    List<Chapter> parserNovelChapters(Document dom);
}
