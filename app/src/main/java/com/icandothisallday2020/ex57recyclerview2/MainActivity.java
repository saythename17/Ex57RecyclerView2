package com.icandothisallday2020.ex57recyclerview2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //대량의 데이터
    ArrayList<Item> items=new ArrayList<>();
    MyAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //대량의 데이터 추가[실무에서는 DB 나 서버에서 데이터를 읽어옴]
        items.add(new Item("정해인","밥 잘 얻어먹는 남자","https://cphoto.asiae.co.kr/listimglink/6/2019121115190139904_1576045141.jpg",R.drawable.jhi));
        items.add(new Item("이가흔","코뿔소","http://imgnews.naver.net/image/609/2020/04/23/202004222151322510_1_20200423060044007.jpg",R.drawable.lgh));
        items.add(new Item("천인우","북극곰","https://image.ytn.co.kr/osen/2020/03/1ca9ac57-353b-4f4a-b196-00f04dad150d.jpg",R.drawable.ciw));
        items.add(new Item("박지현","노트북","https://upload3.inven.co.kr/upload/2020/04/07/bbs/i14566606194.jpg",R.drawable.pjh));
        items.add(new Item("김강열","사자","https://img.etoday.co.kr/pto_db/2020/05/600/20200506232106_1456948_1093_612.jpg",R.drawable.kky));
        items.add(new Item("서민재","토끼","https://t1.daumcdn.net/cfile/tistory/99BF85445E7DF9A518",R.drawable.smj));
        items.add(new Item("임한결","사슴","https://www.nemopan.com/files/attach/images/6294/980/028/014/486240bbacb454eb6add40c91049356c.png",R.drawable.ihk));
        items.add(new Item("정의동","범고래","https://www.ccdailynews.com/news/photo/202003/1058457_436175_5018.PNG",R.drawable.jud));

        adapter=new MyAdapter(this,items);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_linear:
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
                //3번째 파라미터 : reverse 최신글이 맨 위에 올라오게 하고 싶을 때
                recyclerView.setLayoutManager(layoutManager);
                break;
            case R.id.menu_grid:
                RecyclerView.LayoutManager layoutManager1=new GridLayoutManager(this,2);
                //spanCount:옆으로몇칸인지
                recyclerView.setLayoutManager(layoutManager1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
