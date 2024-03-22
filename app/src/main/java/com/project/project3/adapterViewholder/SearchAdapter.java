package com.project.project3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.project.project3.R;
import com.project.project3.controller.user.UserSearchFragment;

import java.util.ArrayList;

public class SearchAdapter extends ArrayAdapter<UserSearchFragment.ListItem> {
    public SearchAdapter(Context context, ArrayList<UserSearchFragment.ListItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convertView가 재활용될 수 있도록 설정
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list, parent, false);
        }

        // 해당 position에 있는 데이터 가져오기
        UserSearchFragment.ListItem item = getItem(position);

        // 리스트 아이템에 데이터 설정
        TextView nameTextView = convertView.findViewById(R.id.tv_Name);
        TextView addressTextView = convertView.findViewById(R.id.tv_add);
        TextView scoreTextView = convertView.findViewById(R.id.tv_score);
        TextView reviewCountTextView = convertView.findViewById(R.id.tv_review);

        nameTextView.setText(item.getName());
        addressTextView.setText(item.getAddress());
        scoreTextView.setText(String.valueOf(item.getScore()));
        reviewCountTextView.setText(String.valueOf(item.getReviewCount()));

        return convertView;
    }
}
