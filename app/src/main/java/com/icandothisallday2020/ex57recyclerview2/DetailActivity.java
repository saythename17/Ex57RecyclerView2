package com.icandothisallday2020.ex57recyclerview2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //내게 온 택배기사 참조
        Intent intent=getIntent();
        String name=intent.getStringExtra("Name");
        int imgID=intent.getIntExtra("img",R.drawable.ic_launcher_foreground);
        iv=findViewById(R.id.iv);
        Glide.with(this).load(imgID).into(iv);
        getSupportActionBar().setTitle(name);
        //제목줄에 업버튼 아이콘[←]보이기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //화면전환효과 반영
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            iv.setTransitionName("IMG");
        }
    }
}
