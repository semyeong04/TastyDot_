package com.project.project3.adapterViewholder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.project3.R;
import com.project.project3.controller.user.MapFragment;
import com.project.project3.controller.user.UserSearchFragment;
import com.project.project3.model.SearchVO;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private TextView tv_Name, tv_add, tv_menu, tv_hashtag;

    private ArrayList<SearchVO> dataset;


    public SearchAdapter(ArrayList<SearchVO> dataset) { this.dataset = dataset; }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);

        SearchViewHolder holder = new SearchViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        SearchVO item = dataset.get(position);
        holder.getTv_Name().setText(item.getTv_name()); // getter 메서드 사용
        holder.getTv_add().setText(item.getTv_add()); // getter 메서드 사용
        holder.getTv_menu().setText(item.getMenu()); // 메뉴 이름 설정
        holder.getTv_hashtag().setText(item.getHashtag()); // 해시태그 설정
        // holder.getTvHashtag().setText(item.getHashtag());
        // holder.getTv_score().setText(item.getTv_score());
        holder.listener = new OnItemClickListener() {
            @Override
            public void OnClickListener(View v, int position) {

                // 여기서 검색프래그먼트 -> 지도프래그먼트로 이동하는데 데이터 하나 가져가야함
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(holder.itemView.getContext(), MapFragment.class);
                holder.itemView.getContext().startActivity(intent);
                // 인텐트에 데이터 추가 필요한 경우 여기에 추가
                // 예: intent.putExtra("key", "value");
                // context.startActivity(intent);

            }
        };

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    // 데이터 세트 업데이트 메소드
    public void updateData(ArrayList<SearchVO> newData) {
        dataset = newData;
        notifyDataSetChanged();
    }
}
