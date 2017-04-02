package com.example.dawidmichalowicz.kalkulatorbmi;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dawid Michałowicz on 01.04.2017.
 */




public class AuthorInfo extends AppCompatActivity{

    @BindView(R.id.authorBackButton)
    Button backButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.author_info);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.authorBackButton)
    public void backToMainActivity(){
        finish();
    }
}
