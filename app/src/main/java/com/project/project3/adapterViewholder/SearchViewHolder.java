package com.project.project3.adapterViewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.project3.R;

public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    OnItemClickListener listener;

    private TextView tv_Name;
    private TextView tv_add;
    private TextView tv_menu; // 메뉴 이름을 표시하는 TextView로 변경
    private ImageView img_list;
    private TextView tv_hashtag; // 해시태그를 표시하는 TextView 추가

    public OnItemClickListener getListener() {
        return listener;
    }

    public TextView getTv_Name() {
        return tv_Name;
    }

    public TextView getTv_add() {
        return tv_add;
    }

    public TextView getTv_menu() {
        return tv_menu;
    }

    public ImageView getImg_list() {
        return img_list;
    }

    public TextView getTv_hashtag() {
        return tv_hashtag;
    }

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tv_Name = itemView.findViewById(R.id.tv_Name);
        this.tv_add = itemView.findViewById(R.id.tv_add);
        this.tv_menu = itemView.findViewById(R.id.tv_menu);
        this.img_list = itemView.findViewById(R.id.img_list);
        this.tv_hashtag = itemView.findViewById(R.id.tv_hashtag);

    itemView.setOnClickListener(this);
        // itemview를 클릭하는 이벤트
        }




@Override
public void onClick(View v) {
        this.listener.OnClickListener(v,getBindingAdapterPosition());
        }
}
