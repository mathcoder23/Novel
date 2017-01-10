package com.mt23.novel.novel.source;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.mt23.novel.novel.source.imple.NovelManagerBiQuGe;
import com.mt23.novel.novel.source.novel.parser.NovelParserFacotry;
import com.mt23.novel.utils.Promise.Promiser;
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
 * Created by mathcoder23 on 17/1/9.
 */
public class NovelResourceManager {
    private static NovelResourceManager single;
    private NovelResourceManager()
    {

    }
    public static NovelResourceManager getInstance()
    {
        if (single == null)
            single = new NovelResourceManager();
        return single;
    }
    private String searchUrl = "http://zhannei.baidu.com/cse/search";

    public Promiser<List<Novel>,String> searchNovelByName(String name){

        Promiser<List<Novel>,String> promise = new Promiser<List<Novel>,String>((Promiser.Resolver<List<Novel>> resolve, Promiser.Rejecter<String> reject) ->{
            Map<String,String> params = new HashMap<>();
            params.put("s","287293036948159515");
            params.put("q",name);

            OkHttpUtils.get()//
                    .tag(this)//
                    .url(searchUrl)
                    .params(params)
                    .build()
                    .execute(new HtmlDomCallBack() {
                        @Override
                        public void onResponseString(Document dom) throws Exception{
                            List<Novel> novelList = NovelParserFacotry.getBiQuge().parserNovelList(dom);
                            resolve.run(novelList);
                        }

                        @Override
                        public void onError(Call call, Exception e) {
                            Log.i("xixi","onerror"+e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Object o) {

                        }
                    });

        });
        return promise;
    }
    public abstract class HtmlDomCallBack extends Callback
    {
        @Override
        public Object parseNetworkResponse(Response response) throws Exception {
            byte[] b = response.body().bytes();
            String data = new String(b, "UTF-8");
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
