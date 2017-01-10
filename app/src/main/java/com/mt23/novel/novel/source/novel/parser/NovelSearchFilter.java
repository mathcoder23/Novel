package com.mt23.novel.novel.source.novel.parser;

import com.mt23.novel.novel.source.Novel;

import java.util.List;

/**
 * Created by mathcoder23 on 16/11/6.
 */
public class NovelSearchFilter {
    public static void Filter(List<Novel> list){
        for (Novel novel : list)
        {
            String name = novel.getName();
            name = name.replace("<em>","");
            name = name.replace("</em>","");
            novel.setName(name);
        }
    }
}
