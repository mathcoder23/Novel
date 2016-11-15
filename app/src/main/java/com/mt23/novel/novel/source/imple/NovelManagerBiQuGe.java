package com.mt23.novel.novel.source.imple;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.mt23.novel.novel.source.*;
import com.mt23.novel.ui.fragment.SearchNovel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import okhttp3.Call;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

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
                            HandlerMessage<Novel> handlerMessage = new HandlerMessage();
                            NovelSearchFilter.Filter(novelList);
                            handlerMessage.list = novelList;
                            handlerMessage.callback = searchCallBack;
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

    @Override
    public void NovelChapterList(Novel novel, final ChapterListCallBack chapterListCallBack) {
        OkHttpUtils.get()//
                .tag(this)//
                .url(searchUrl)
                .build()
                .execute(new HtmlDomCallBack() {
                    @Override
                    public void onResponseString(Document dom) throws Exception {
                        Elements dd = dom.getElementsByTag("dd");
                        List<Chapter> chapters = new ArrayList<Chapter>();
                        for (Element d : dd)
                        {
                            Elements a = d.getElementsByTag("a");
                            if (null != a && a.size() == 1)
                            {
                                Chapter chapter = new Chapter();
                                chapter.setTitle(a.html());
                                chapter.setUrl(a.attr("href"));
                                chapters.add(chapter);
                            }
                            else
                            {
                                Log.i("xixi","章节列表格式不正确!");
                            }
                        }
                        Collections.reverse(chapters);
                        HandlerMessage<Chapter> handlerMessage = new HandlerMessage();
                        handlerMessage.callback = chapterListCallBack;
                        handlerMessage.list = chapters;
                        mHandler.obtainMessage(2,handlerMessage).sendToTarget();
                    }

                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(Call call, Object o) {

                    }
                });
    }

    @Override
    public void NovelChapterContent(final Chapter chapter, final ChapterContentCallback chapterContentCallback) {
            OkHttpUtils.get()
                    .tag(this)
                    .url(chapter.getUrl())
                    .build()
                    .execute(new HtmlDomCallBack() {
                        @Override
                        public void onResponseString(Document dom) throws Exception {
                            Element element = dom.getElementById("content");
                            chapter.setContent(element.html());
                            HandlerMessage handlerMessage = new HandlerMessage();
                            handlerMessage.callback = chapterContentCallback;
                            handlerMessage.obj = chapter;
                            mHandler.obtainMessage(3,handlerMessage).sendToTarget();
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
                HandlerMessage<Novel> handlerMessage = (HandlerMessage) msg.obj;
                SearchCallBack searchNovel = (SearchCallBack) handlerMessage.callback;
                searchNovel.SearchResult(handlerMessage.list);
            }
            else if (msg.what == 2)
            {
                HandlerMessage<Chapter> handlerMessage = (HandlerMessage) msg.obj;
                ChapterListCallBack callback = (ChapterListCallBack) handlerMessage.callback;
                callback.chapterList(handlerMessage.list);
            }
            else if (msg.what == 3)
            {
                HandlerMessage handlerMessage = (HandlerMessage) msg.obj;
                ChapterContentCallback callback = (ChapterContentCallback) handlerMessage.callback;
                callback.result((Chapter) handlerMessage.obj);

            }
        }
    };
    class HandlerMessage<T> {
        public Object callback;
        public List<T> list;
        public Object obj;

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
