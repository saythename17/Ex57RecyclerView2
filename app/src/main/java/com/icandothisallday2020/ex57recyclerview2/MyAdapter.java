package com.icandothisallday2020.ex57recyclerview2;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<Item> items;

    public MyAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.recycler_item,parent,false);
        //뷰홀더 객체 생성 및 리턴
        VH holder=new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;
        //현재 position 의 테이터를 가진 item 객체 얻기
        Item item=items.get(position);
        vh.civ.setImageResource(item.profile);
        vh.name.setText(item.name);
        vh.msg.setText(item.msg);
        //네트워크 이미지를 쉽게 불러오는 외부 라이브러리:glide 사용
        Glide.with(context).load(item.imageURL).into(vh.iv);
        Glide.with(context).load(item.profile).into(vh.civ);
    }

    @Override
    public int getItemCount() {        return items.size();    }

    //아이템뷰 안 뷰들의 참조변수를 멤버로 가진 이너 클래스
    class VH extends RecyclerView.ViewHolder{
        CircleImageView civ;
        TextView name,msg;
        ImageView iv;
        public VH(@NonNull View itemView) {
            super(itemView);
            civ=itemView.findViewById(R.id.profile);
            name=itemView.findViewById(R.id.tv_name);
            msg=itemView.findViewById(R.id.tv_msg);
            iv=itemView.findViewById(R.id.iv);

            //ItemClickListener 생성 및 설정
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Item item=items.get(getLayoutPosition());
                    //└현재 클릭한 포지션의 아이템 데이터 참조
                    Intent intent=new Intent(context,DetailActivity.class);
                    //┌전달할 데이터 추가
                    intent.putExtra("Name",item.name);
                    intent.putExtra("img",item.profile);

                    //화면전환 애니메이션 효과(api ver.21~이상만 가능)
                    if(Build.VERSION.SDK_INT<21) context.startActivity(intent);
                    //==(Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP)
                    else {
                        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                                new Pair<View, String>(civ,"IMG"));
                        context.startActivity(intent,options.toBundle());
                    }

                }
            });
        }
    }
}
