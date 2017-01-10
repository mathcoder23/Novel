package com.mt23.novel.novel.source.novel.parser;

import com.mt23.novel.novel.source.Novel;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by mathcoder23 on 1/10/17.
 */
public interface NovelParser {
    List<Novel> parserNovelList(Document dom);
}
