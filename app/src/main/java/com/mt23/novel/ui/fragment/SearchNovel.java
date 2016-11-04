package com.mt23.novel.ui.fragment;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.mt23.novel.R;
import com.mt23.novel.novel.source.Novel;
import com.mt23.novel.novel.source.SearchCallBack;
import com.mt23.novel.novel.source.imple.NovelManagerBiQuGe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mathcoder23 on 11/4/16.
 */
public class SearchNovel extends Fragment implements SearchCallBack{
    private EditText etSearch;
    private ListView lvSearchResult;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_novel,container,false);
        etSearch = (EditText) view.findViewById(R.id.et_search);
        lvSearchResult = (ListView) view.findViewById(R.id.lv_search_result);
        etSearch.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = etSearch.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > etSearch.getWidth()
                        - etSearch.getPaddingRight()
                        - drawable.getIntrinsicWidth()){
                    Novel novel = new Novel();
                    novel.setName(etSearch.getText().toString());
                    NovelManagerBiQuGe.getInstance().SearchNovel(novel,SearchNovel.this);
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void SearchResult(List<Novel> list) {
        List<Map<String,String>> data = new ArrayList<>();
        for (Novel novel : list)
        {
            Map<String,String> n = new HashMap<>();
            n.put("name",novel.getName());
            data.add(n);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),data,R.layout.list_search_result,new String[]{"name"},new int[]{R.id.list_search_name});
        lvSearchResult.setAdapter(simpleAdapter);
    }
}
