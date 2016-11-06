package com.mt23.novel.novel.source.imple;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.mt23.novel.novel.source.Novel;
import com.mt23.novel.novel.source.NovelManager;
import com.mt23.novel.novel.source.NovelSearchFilter;
import com.mt23.novel.novel.source.SearchCallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import okhttp3.Call;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mathcoder23 on 16/11/3.
 */
public class NovelManagerBiQuGe implements NovelManager{
    public static String API_BASE = "http://www.biquge.cm";
    public static String API_BUXIUFANREN = "/0/837/";
    private String searchUrl = "http://zhannei.baidu.com/cse/search";
//    private String searchUrl = "http://zhannei.baidu.com/cse/searchUrl?s=287293036948159515&q=%E7%9C%9F%E6%AD%A6%E4%B8%96%E7%95%8C";
    private static NovelManagerBiQuGe single;
    private NovelManagerBiQuGe(){

    }
    public static NovelManagerBiQuGe getInstance()
    {
        if (single == null)
            single = new NovelManagerBiQuGe();
        return single;
    }
    @Override
    public void SearchNovel(Novel novel, final SearchCallBack searchCallBack) {
        Map<String,String> params = new HashMap<>();
        params.put("s","287293036948159515");
        params.put("q",novel.getName());

        OkHttpUtils.get()//
                .tag(this)//
                .url(searchUrl)
                .params(params)
                .build()
                .execute(new HtmlDomCallBack() {
                    @Override
                    public void onResponseString(Document dom) throws Exception{
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
                        if(searchCallBack != null)
                        {
                            HandlerMessage handlerMessage = new HandlerMessage();
                            NovelSearchFilter.Filter(novelList);
                            handlerMessage.novels = novelList;
                            handlerMessage.searchCallBack = searchCallBack;
                            mHandler.obtainMessage(1,handlerMessage).sendToTarget();
                        }

                    }

                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(Call call, Object o) {

                    }
                });
    }
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1)
            {
                HandlerMessage handlerMessage = (HandlerMessage) msg.obj;
                handlerMessage.callback();
            }
        }
    };
    class HandlerMessage {
        public SearchCallBack searchCallBack;
        public List<Novel> novels;
        public void callback(){
            searchCallBack.SearchResult(novels);
        }
    }
    abstract class HtmlDomCallBack extends Callback
    {
        @Override
        public Object parseNetworkResponse(Response response) throws Exception {
            byte[] b = response.body().bytes(); //获取数据的bytes
            String data = new String(b, "UTF-8"); //然后将其转为gb2312
//            Log.i("xixi",data);
            Document dom = Jsoup.parse(data);
            try{
                onResponseString(dom);

            }catch(Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }


        public abstract void onResponseString(Document dom) throws Exception;

    }
}
