package com.mt23.novel.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.mt23.novel.ui.activity.MainActivity;
import com.mt23.novel.R;
import com.mt23.novel.novel.service.Novel;
import com.mt23.novel.novel.service.NovelResourceManager;
import com.mt23.novel.utils.ListMapBean;
import com.mt23.novel.utils.PrefersHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mt23.novel.local.PrefersManger.SEARCH_RECODE;

/**
 * Created by mathcoder23 on 11/4/16.
 */
public class SearchNovel extends BaseFragment {
    private EditText etSearch;
    private ListView lvSearchResult;
    private MainActivity mainActivity;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_novel,container,false);
        mainActivity = (MainActivity) getActivity();
        etSearch = (EditText) view.findViewById(R.id.et_search);
        lvSearchResult = (ListView) view.findViewById(R.id.lv_search_result);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name = charSequence.toString();

                if(name!= null && name.length()>0)
                {
                    NovelResourceManager
                            .getInstance()
                            .searchNovelByName(name)
                            .success((List<Novel> novelList)->{
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        updateList(novelList);
                                    }
                                });

                            });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        lvSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String,String> map = (Map<String, String>) adapterView.getItemAtPosition(i);
                Novel novel = (Novel) ListMapBean.MapToBean(map,Novel.class);
                //PrefersHelper.setValue(SEARCH_RECODE,novel.getName(),JSON.toJSONString(novel));
            }
        });
//        showSearchRecode();
        return view;

    }
    private void showSearchRecode()
    {
        Map<String,String> datas = (Map<String, String>) PrefersHelper.getAll(SEARCH_RECODE);
        List<Map<String,String>> list = new ArrayList<>();
        for (String key : datas.keySet())
        {
            Novel novel = JSON.parseObject(datas.get(key),Novel.class);
            list.add(ListMapBean.BeanToMap(novel));
        }
        //updateList(list);
    }
    private void updateList(List<Novel> list)
    {
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
