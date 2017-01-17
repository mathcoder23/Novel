package com.mt23.novel.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mt23.novel.R;
import com.mt23.novel.novel.service.Chapter;
import com.mt23.novel.novel.service.Novel;
import com.mt23.novel.utils.ToastUtils;

/**
 * Created by mathcoder23 on 1/16/17.
 */
public class NovelContent extends BaseFragment {
    private Novel novel;
    private TextView tvNovelContent;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_novel_content,container,false);
        tvNovelContent = (TextView) view.findViewById(R.id.tv_novel_chapter_text);
        view.findViewById(R.id.btn_novel_content_catalog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCatalog(view);
            }
        });
        view.findViewById(R.id.btn_novel_content_pre_chapter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPreChapter(view);
            }
        });
        view.findViewById(R.id.btn_novel_content_next_chapter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextChapter(view);
            }
        });
        tvNovelContent.setText("没有数据");
        return view;
    }
    public void update(Chapter chapter)
    {
        loading();
        if(chapter.isReadable())
        {
            //更新小说内容
            chapter.getContent()
                    .success((String content)->
                    {
                        getActivity().runOnUiThread(()->{
                            tvNovelContent.setText(Html.fromHtml(content));
                            finishLoad();
                        });
                    });
            //更新标题
            getActivity().setTitle(chapter.getName());
        }
        else
        {
            cancelLoad();
            //没有购买章节无法阅读
            ToastUtils.show(getActivity(),"权限不够，请购买章节");
        }



    }

    public void onNextChapter(View v)
    {
        ToastUtils.show(getActivity(),"显示下一章节");
    }
    public void onPreChapter(View v)
    {
        ToastUtils.show(getActivity(),"显示上一章节");
    }
    public void onCatalog(View v)
    {
        ToastUtils.show(getActivity(),"显示目录");
    }

    @Override
    public String getFragmentTitle() {
        return "小说内容";
    }
}
