package com.mt23.novel;

import com.mt23.novel.novel.source.Novel;
import com.mt23.novel.utils.Promise.Promiser;

import java.util.List;

/**
 * Created by mathcoder23 on 1/10/17.
 */
public class SearchNovelManager {
    private static SearchNovelManager single;

    private SearchNovelManager(){}
    public static SearchNovelManager getInstance(){
        if (null == single)
        {
            single = new SearchNovelManager();
        }
        return single;
    }
    public Promiser<List<Novel>,String> hotNovels(){
        return new Promiser<>(new Promiser.PromiseInitializer<List<Novel>, String>() {
            @Override
            public void run(Promiser.Resolver<List<Novel>> resolve, Promiser.Rejecter<String> reject) {

            }
        });
    }
    public List<Novel> historyNovels(){
        return null;
    }
    public void removeHistoryNovel(Novel novel)
    {

    }
    public void clearHistoryNovel()
    {

    }
    public void addHistoryNovel(Novel novel)
    {

    }
}
