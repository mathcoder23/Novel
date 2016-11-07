package com.mt23.novel.ui.fragment;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.*;
import com.mt23.novel.R;
import com.mt23.novel.novel.source.Novel;
import com.mt23.novel.novel.source.SearchCallBack;
import com.mt23.novel.novel.source.imple.NovelManagerBiQuGe;
import com.mt23.novel.utils.ListMapBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mathcoder23 on 11/4/16.
 */
public class SearchNovel extends BaseFragment implements SearchCallBack{
    private EditText etSearch;
    private ListView lvSearchResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_novel,container,false);
        etSearch = (EditText) view.findViewById(R.id.et_search);
        lvSearchResult = (ListView) view.findViewById(R.id.lv_search_result);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Novel novel = new Novel();
                novel.setName(charSequence.toString());
                NovelManagerBiQuGe.getInstance().SearchNovel(novel,SearchNovel.this);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        lvSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String,String> map = (Map<String, String>) adapterView.getItemAtPosition(i);
                Toast.makeText(SearchNovel.this.getActivity(),""+map.get("url"),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void SearchResult(List<Novel> list) {
        List<Map<String,String>> data = new ArrayList<>();
        for (Novel novel : list)
        {
            Map<String,String> n = ListMapBean.BeanToMap(novel);
            data.add(n);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),data,R.layout.list_search_result,
                new String[]{"name","author","type","updateTime","lastChapter"},
                new int[]{R.id.list_search_name,
                        R.id.list_search_author,
                        R.id.list_search_type,
                        R.id.list_search_updatetime,
                        R.id.list_search_lastchapter});
        lvSearchResult.setAdapter(simpleAdapter);
    }

    @Override
    public String getFragmentTitle() {
        return "小说搜索";
    }
}
