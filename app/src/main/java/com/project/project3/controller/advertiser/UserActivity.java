package com.project.project3.controller.advertiser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.project.project3.R;

public class UserActivity extends AppCompatActivity {
    Intent intent = new Intent(this, UserActivity.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        TextView tvMenuAdd = findViewById(R.id.tvMenuAdd); // 메뉴 추가
        TextView tvMenuMod = findViewById(R.id.tvMenuMod); // 메뉴 수정 페이지x
        TextView tvAddCoupon = findViewById(R.id.tvAddCoupon); // 쿠폰 발급
        TextView tvNowCoupon = findViewById(R.id.tvNowCoupon); // 쿠폰 현황
        TextView tvUsedCoupon = findViewById(R.id.tvUsedCoupon); // 이전 쿠폰 내역x

        // 메뉴 추가 페이지 이동
        tvMenuAdd.setOnClickListener(v -> {
            intent = new Intent(this, MenuRegiActivity.class);
            startActivity(intent);
        });

        // 메뉴 수정 페이지 이동


        // 쿠폰 발급 페이지 이동
        tvAddCoupon.setOnClickListener(v->{
            intent = new Intent(this, AddCouponActivity.class);
            startActivity(intent);
        });


        // 쿠폰 현황 페이지 이동
        tvNowCoupon.setOnClickListener(v->{
            intent = new Intent(this, CheckCouponActivity.class);
            startActivity(intent);
        });

        // 이전 쿠폰 내역 페이지 이동


        BottomNavigationView bnv = findViewById(R.id.bnv_adv);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // itme  : 내가 클릭한 항목에 대한 정보
                // 정보 : 속성,id,title,icon...
                // item.getItemId() : 항목의 id값을 가져오는 방법
                int itemId = item.getItemId();
                if (itemId == R.id.bnv_home) {
                    intent = new Intent(UserActivity.this, UserActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.bnv_menu) {
                    intent = new Intent(UserActivity.this, MenuRegiActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.bnv_addcoupon) {
                    intent = new Intent(UserActivity.this, AddCouponActivity.class);
                    startActivity(intent);

                } else if (itemId == R.id.bnv_checkcoupon) {
                    intent = new Intent(UserActivity.this, AddCouponActivity.class);
                    startActivity(intent);

                } else if (itemId == R.id.bnv_info) {
                    intent = new Intent(UserActivity.this, AddCouponActivity.class);
                    startActivity(intent);
                }
                // 항목에 대한 클릭 이벤트를 감지
                // false : 클릭한번하고 이벤트가 계속 된다고 생각함
                // true : 클릭 후 이벤트 종료
                return true;
            }
        });


        }}