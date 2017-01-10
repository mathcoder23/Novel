package com.mt23.novel.novel.source.novel.parser;

import com.mt23.novel.novel.source.Novel;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathcoder23 on 1/10/17.
 */
public class BIQUGENovelParser implements NovelParser {
    @Override
    public List<Novel> parserNovelList(Document dom) {
        List<Novel> novelList = new ArrayList<>();
        Elements lists = dom.getElementsByClass("result-list");
        Elements list = lists.get(0).getElementsByClass("result-item result-game-item");
        for (Element ch : list)
        {
            Novel novel = new Novel();
            novel.setUrl(ch.getElementsByClass("result-game-item-pic-link").get(0)
                    .attr("href"));
            novel.setImgUrl(ch.getElementsByClass("result-game-item-pic-link-img").get(0)
                    .attr("src"));
            novel.setName(ch.getElementsByClass("result-game-item-title-link").get(0)
                    .html());
            novel.setSummary(ch.getElementsByClass("result-game-item-desc").get(0)
                    .html());
            novel.setAuthor(ch.getElementsByClass("result-game-item-info-tag").get(0)
                    .getElementsByTag("span").get(1)
                    .html());
            novel.setType(ch.getElementsByClass("result-game-item-info-tag").get(1)
                    .getElementsByTag("span").get(1)
                    .html());
            novel.setUpdateTime(ch.getElementsByClass("result-game-item-info-tag").get(2)
                    .getElementsByTag("span").get(1)
                    .html());
            novel.setLastChapter(ch.getElementsByClass("result-game-item-info-tag").get(3)
                    .getElementsByTag("a").get(0)
                    .html());
            novel.setLastChapterUrl(ch.getElementsByClass("result-game-item-info-tag").get(3)
                    .getElementsByTag("a").get(0)
                    .attr("href"));
            novelList.add(novel);

        }
        NovelSearchFilter.Filter(novelList);
        return novelList;
    }
}
