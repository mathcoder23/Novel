package com.mt23.novel.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.mt23.novel.R;
import com.mt23.novel.novel.service.Chapter;
import com.mt23.novel.novel.service.Novel;
import com.mt23.novel.novel.service.NovelResourceManager;
import com.mt23.novel.utils.Promise.Promiser;
import com.mt23.novel.utils.ToastUtils;
import com.mt23.novel.utils.listview.adapter.CommonAdapter;
import com.mt23.novel.utils.listview.adapter.ViewHolder;

/**
 * Created by mathcoder23 on 1/20/17.
 */
public class ReadNovelActivity extends BaseActivity {
    private Novel novel;
    private TextView tvNovelContent;
    private ListView lvNovelChapers;
    private CommonAdapter<Chapter> adapterNovelChapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_novel);
        init();
    }
    private void init()
    {
        tvNovelContent = (TextView) findViewById(R.id.tv_novel_chapter_text);
        lvNovelChapers = (ListView) findViewById(R.id.lv_novel_chapters);
        adapterNovelChapter = new CommonAdapter<>(this,R.layout.item_novel_chapter);
        adapterNovelChapter.setItemHandle(new CommonAdapter.ItemHandle<Chapter>() {
            @Override
            public void onItem(ViewHolder helper, Chapter item) {
                helper.setText(R.id.chapter_name,item.getName());
            }
        });
        lvNovelChapers.setAdapter(adapterNovelChapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        novel = NovelResourceManager.getInstance().getCurrentNovel();
        novel.getCurrentChapter().success(new Promiser.Resolver<Chapter>() {
            @Override
            public void run(Chapter chapter) {
                update(chapter);
            }
        });
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
                        runOnUiThread(()->{
                            tvNovelContent.setText(Html.fromHtml(content));
                            finishLoad();
                        });
                    });
            //更新标题
        }
        else
        {
            cancelLoad();
            //没有购买章节无法阅读
            ToastUtils.show("权限不够，请购买章节");
        }



    }
    public void onNextChapter(View v)
    {
        update(novel.PreChapter());
        ToastUtils.show("显示下一章节");
    }
    public void onPreChapter(View v)
    {
        update(novel.NextChapter());
        ToastUtils.show("显示上一章节");
    }
    public void onCatalog(View v)
    {
        ToastUtils.show("显示目录");
    }

}
