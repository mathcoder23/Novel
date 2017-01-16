package com.mt23.novel.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import com.mt23.novel.R;
import com.mt23.novel.novel.source.Novel;

/**
 * Created by mathcoder23 on 1/16/17.
 */
public class NovelContent extends BaseFragment {
    private Novel novel;
    private TextView tvNovelContent;
    private String mobData = "奥斯丁金佛啊手机端佛啊僵尸洞覅\n" +
            "奥斯丁金佛啊手机端佛啊僵尸洞覅\n" +
            "奥斯丁金佛啊手机端佛啊僵尸洞覅\n" +
            "奥斯丁金佛啊手机端佛啊僵尸洞覅\n" +
            "奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n" +
            "奥斯丁金佛啊手机端佛啊僵尸洞覅\n" +
            "奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n" +
            "奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n" +
            "奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n" +
            "奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n" +
            "奥斯丁金佛啊手机端佛啊僵尸洞覅\n" +
            "奥斯丁金佛啊手机端佛啊僵尸洞覅\n" +
            "奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n" +
            "奥斯丁金佛啊手机端佛啊僵尸洞覅\n" +
            "v" +
            "奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n" +
            "奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n奥斯丁金佛啊手机端佛啊僵尸洞覅\n" +
            "end" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "";
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_novel_content,container,false);
        tvNovelContent = (TextView) view.findViewById(R.id.tv_novel_chapter_text);
        tvNovelContent.setText(mobData);
        return view;
    }

    @Override
    public String getFragmentTitle() {
        return "小说内容";
    }
}
