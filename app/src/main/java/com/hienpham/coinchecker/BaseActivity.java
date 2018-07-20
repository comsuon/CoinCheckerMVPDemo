package com.hienpham.coinchecker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setUpToolbar(Toolbar toolbar){
        mToolbar = toolbar;
        this.setSupportActionBar(mToolbar);
    }

    protected void setTitle(String title) {
        mToolbar.setTitle(title);
    }
}
