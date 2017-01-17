package com.mt23.novel.novel.service.parser;

/**
 * Created by mathcoder23 on 1/10/17.
 */
public class NovelParserFacotry {
    private static NovelParser biqugeNovelParser;
    public static NovelParser getBiQuge()
    {
        if (null == biqugeNovelParser)
            biqugeNovelParser = new BIQUGENovelParser();
        return biqugeNovelParser;
    }
}
