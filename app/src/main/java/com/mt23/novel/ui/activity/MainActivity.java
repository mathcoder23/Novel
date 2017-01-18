package com.mt23.novel.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.mt23.novel.R;

public class MainActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onSearch(View v)
    {
        Intent intent = new Intent(this,SearchNovelActivity.class);
        startActivity(intent);
    }
}