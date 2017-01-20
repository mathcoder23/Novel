package com.mt23.novel.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import com.mt23.novel.MyApplication;
import com.mt23.novel.R;
import com.mt23.novel.novel.service.Novel;
import com.mt23.novel.novel.service.NovelResourceManager;
import com.mt23.novel.utils.Promise.Promiser;
import com.mt23.novel.utils.ToastUtils;
import com.mt23.novel.utils.listview.adapter.CommonAdapter;
import com.mt23.novel.utils.listview.adapter.ViewHolder;

import java.util.List;

/**
 * Created by mathcoder23 on 1/18/17.
 */
public class SearchNovelActivity extends BaseActivity implements TextWatcher {
    private EditText etSearchNovel;
    private ImageView ivSearchNovelEdClear;
    private ListView lvSearchNovelResult;
    private CommonAdapter<Novel> adapterSearchNovelResult;

    private enum UIState{
        NovelSearchResult,
        NovelSearchHint
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_novel);
        init();
    }
    ///////////////视图处理
    private void init()
    {
        etSearchNovel = (EditText) findViewById(R.id.et_search_novel);
        etSearchNovel.addTextChangedListener(this);

        ivSearchNovelEdClear = (ImageView) findViewById(R.id.iv_search_novel_et_clear);
        ivSearchNovelEdClear.setVisibility(View.INVISIBLE);

        lvSearchNovelResult = (ListView) findViewById(R.id.lv_search_hint);
        adapterSearchNovelResult = new CommonAdapter<>(this,R.layout.lv_search_novel_result);
        lvSearchNovelResult.setAdapter(adapterSearchNovelResult);
        adapterSearchNovelResult.setItemHandle(new CommonAdapter.ItemHandle<Novel>() {
            @Override
            public void onItem(ViewHolder helper, Novel item) {
                helper.setText(R.id.item_novel_name,item.getName())
                        .setText(R.id.item_novel_author,item.getAuthor())
                        .setTextFromHtml(R.id.item_novel_desc,item.getSummary())
                        .setImageByUrl(R.id.item_novel_cover,item.getCoverImgUrl());
            }
        });
        lvSearchNovelResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Novel novel = (Novel) adapterView.getItemAtPosition(i);
                NovelResourceManager.getInstance().setCurrentNovel(novel);
                ToastUtils.show(novel.getName());
                startActivity(new Intent(SearchNovelActivity.this,ReadNovelActivity.class));
            }
        });
    }
    private void changeUI(UIState uiState)
    {
        switch(uiState)
        {
            case NovelSearchHint:
                lvSearchNovelResult.setVisibility(View.GONE);
                break;
            case NovelSearchResult:
                lvSearchNovelResult.setVisibility(View.VISIBLE);
                break;
        }
    }

    ///////////////按键
    public void onSearch(View v)
    {
        ToastUtils.show(this,"search");
    }
    public void onEditClear(View v)
    {
        etSearchNovel.setText("");
        ToastUtils.show(this,"clear edit");
    }

    public void onMore(View view) {
        ToastUtils.show(this,"more");
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length()>0)
        {
            ivSearchNovelEdClear.setVisibility(View.VISIBLE);
            changeUI(UIState.NovelSearchResult);
            NovelResourceManager.getInstance()
                    .searchNovelByName(charSequence.toString())
                    .success(new Promiser.Resolver<List<Novel>>() {
                        @Override
                        public void run(List<Novel> novels) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapterSearchNovelResult.updateData(novels);
                                }
                            });

                        }
                    });
        }
        else
        {
            ivSearchNovelEdClear.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
