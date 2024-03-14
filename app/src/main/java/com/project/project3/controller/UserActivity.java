package com.project.project3.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.project.project3.R;

import org.w3c.dom.Text;

public class UserActivity extends AppCompatActivity {
    Intent intent;
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


    }
}