package com.mt23.novel.novel.source;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
 * Created by mathcoder23 on 10/31/16.
 */
public class StoryBiquge {
    public interface StoryData{
         void StoryChapters(List<Map<String, Object>> chapters);
        void StoryChapterContent(String data);
    }
    private StoryData storyData = null;
    public void setStoryData(StoryData storyData)
    {
        this.storyData = storyData;
    }
    public static String API_BASE = "http://www.biquge.cm";
    public static String API_BUXIUFANREN = "/0/837/";
    public void getChapterContentBuxiufanrenzhuan(String url)
    {
        OkHttpUtils.get()//
                .tag(this)//
                .url(API_BASE+url)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        byte[] b = response.body().bytes(); //获取数据的bytes
                        String info = new String(b, "GB2312"); //然后将其转为gb2312
                        Document dom = Jsoup.parse(info);
                        Element element = dom.getElementById("content");

                        mHandler.obtainMessage(2,element.html()).sendToTarget();
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(Call call, Object o) {

                    }
                });
    }
    public void getChaptersBuxiufanrenzhuan(String url,final StoryData storyDataListener)
    {
        OkHttpUtils.get()//
                .tag(this)//
                .url(API_BASE+url)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        byte[] b = response.body().bytes(); //获取数据的bytes
                        String info = new String(b, "GB2312"); //然后将其转为gb2312
                        Document dom = Jsoup.parse(info);
                        Elements dd = dom.getElementsByTag("dd");
                        List<Map<String,Object>> chapter = new ArrayList<>();
                        for (Element d : dd)
                        {
                            Elements a = d.getElementsByTag("a");
                            if (null != a && a.size() == 1)
                            {
                                Map<String,Object> temp = new HashMap<String,Object>();
                                temp.put("name",a.html());
                                temp.put("href",a.attr("href"));
                                chapter.add(temp);

                            }
                            else
                            {
                                Log.i("xixi","章节列表格式不正确!");
                            }
                        }
                        Collections.reverse(chapter);
                        storyDataListener.StoryChapters(chapter);
                        mHandler.obtainMessage(1,chapter).sendToTarget();
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(Call call, Object o) {

                    }
                });
    }
    public void getChaptersBuxiufanrenzhuan(String url)
    {
        OkHttpUtils.get()//
                .tag(this)//
                .url(API_BASE+url)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        byte[] b = response.body().bytes(); //获取数据的bytes
                        String info = new String(b, "GB2312"); //然后将其转为gb2312
                        Document dom = Jsoup.parse(info);
                        Elements dd = dom.getElementsByTag("dd");
                        List<Map<String,Object>> chapter = new ArrayList<>();
                        for (Element d : dd)
                        {
                            Elements a = d.getElementsByTag("a");
                            if (null != a && a.size() == 1)
                            {
                                Map<String,Object> temp = new HashMap<String,Object>();
                                temp.put("name",a.html());
                                temp.put("href",a.attr("href"));
                                chapter.add(temp);

                            }
                            else
                            {
                                Log.i("xixi","章节列表格式不正确!");
                            }
                        }
                        Collections.reverse(chapter);
                        mHandler.obtainMessage(1,chapter).sendToTarget();
                        return null;
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
                List<Map<String,Object>> data = (List<Map<String, Object>>) msg.obj;
                if (storyData != null)
                storyData.StoryChapters(data);

            }
            else if (msg.what == 2)
            {
                if (storyData != null)
                storyData.StoryChapterContent((String) msg.obj);
            }
        }
    };
}
