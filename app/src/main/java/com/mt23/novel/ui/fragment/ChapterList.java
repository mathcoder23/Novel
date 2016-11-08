package com.mt23.novel.ui.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.mt23.novel.MainActivity;
import com.mt23.novel.R;
import com.mt23.novel.novel.source.StoryBiquge;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by mathcoder23 on 11/1/16.
 */
public class ChapterList extends BaseFragment implements StoryBiquge.StoryData{
    private ListView lvChapter;
    private StoryBiquge storyBiquge;
    private Context mContext;
    private OnItemSelectListener onItemSelectListener;
    private String title= "";
    private MainActivity mainActivity;
    public interface OnItemSelectListener{
        void SelectItem(String data,String title);
    }
    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener)
    {
        this.onItemSelectListener = onItemSelectListener;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragmetn_chapters,container,false);
        mContext = getActivity();
        mainActivity = (MainActivity) getActivity();
        lvChapter = (ListView) view.findViewById(R.id.lv_chapters);

        lvChapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String,Objects> map = (Map<String, Objects>) adapterView.getItemAtPosition(i);
                storyBiquge.getChapterContentBuxiufanrenzhuan(map.get("href")+"");
                title = map.get("name")+"";
                Toast.makeText(getActivity(),map.get("href")+"",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void StoryChapters(List<Map<String, Object>> chapters) {
        SimpleAdapter simpleAdapter = new SimpleAdapter(mContext,chapters,android.R.layout.simple_list_item_1,new String[]{"name"},new int[]{android.R.id.text1});
        lvChapter.setAdapter(simpleAdapter);
    }

    @Override
    public void StoryChapterContent(String data) {
        if (onItemSelectListener != null)
        {
            onItemSelectListener.SelectItem(data,title);
        }
        mainActivity.changeFragment(3,data);
    }
    public void updateList(String url)
    {
        if (null == storyBiquge)
        {
            storyBiquge = new StoryBiquge();
            storyBiquge.setStoryData(this);
        }
        storyBiquge.getChaptersBuxiufanrenzhuan(url);
    }
    @Override
    public String getFragmentTitle() {
        return "小说列表";
    }
}
