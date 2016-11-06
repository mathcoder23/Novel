package com.mt23.novel.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.mt23.novel.R;

/**
 * Created by mathcoder23 on 11/1/16.
 */
public class ChapterContent extends BaseFragment {
    private WebView webView;
    private String localData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_chapter,container,false);
        webView  = (WebView) view.findViewById(R.id.wv_content);
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        webView.loadData(localData,"text/html; charset=UTF-8",null);
        return view;
    }

    @Override
    public String getFragmentTitle() {
        return "小说章节";
    }

    public void setLocalData(String localData) {
        this.localData = localData;
    }
}
