package com.project.project3.controller.user;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.project.project3.R;
import com.project.project3.adapterViewholder.CouponAdapter;
import com.project.project3.adapterViewholder.SearchAdapter;
import com.project.project3.model.SearchVO;
import com.project.project3.model.UserCouponVO;

import java.util.ArrayList;

public class UserSearchFragment extends Fragment {

    // 모델 클래스 정의
//    public static class ListItem {
//        private String name;
//        private String address;
//        private double score;
//        private int reviewCount;
//
//        public ListItem(String name, String address, double score, int reviewCount) {
//            this.name = name;
//            this.address = address;
//            this.score = score;
//            this.reviewCount = reviewCount;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public String getAddress() {
//            return address;
//        }
//
//        public double getScore() {
//            return score;
//        }
//
//        public int getReviewCount() {
//            return reviewCount;
//        }
//    }

    private RecyclerView rv_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_search, container, false);
        RecyclerView rv_search = view.findViewById(R.id.rv_search);

        // 리스트 아이템 데이터 생성
        ArrayList<SearchVO> items = new ArrayList<>();

        SearchVO vo1 = new SearchVO("은선 식당", "목포시 전체", "4.9", "500", R.drawable.camera);
        SearchVO vo2 = new SearchVO("세명 식당", "목포시 우미오션빌", "4.9", "500", R.drawable.camera);
        SearchVO vo3 = new SearchVO("맹진 식당", "목포시 부영2차", "4.9", "500", R.drawable.camera);
        SearchVO vo4 = new SearchVO("경근 식당", "목포시 남악어딘가", "4.9", "500", R.drawable.camera);
        items.add(vo1);
        items.add(vo2);
        items.add(vo3);
        items.add(vo4);

        ArrayList<SearchVO> Searchs = items; //  경근씨 DB쿠폰 데이터 입력 ex20240122참고해서 만들면됩니다!
        SearchAdapter adapter = new SearchAdapter(Searchs);

        rv_search.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        rv_search.setLayoutManager(manager);
        // 리사이클러뷰 새로고침 : adpater.notifyDatasetChanged();


        return view;
    }
}
