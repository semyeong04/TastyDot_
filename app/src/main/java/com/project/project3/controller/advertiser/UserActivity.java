package com.project.project3.controller.advertiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.project3.R;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        TextView tvMenuAdd = findViewById(R.id.tvMenuAdd); // 메뉴 추가
        TextView tvAddCoupon = findViewById(R.id.tvAddCoupon); // 쿠폰 발급
        TextView tvNowCoupon = findViewById(R.id.tvNowCoupon); // 쿠폰 현황

        // 메뉴 추가 페이지 이동
        tvMenuAdd.setOnClickListener(v -> {
            Intent intent = new Intent(UserActivity.this, MenuRegiActivity.class);
            startActivity(intent);
        });

        // 쿠폰 발급 페이지 이동
        tvAddCoupon.setOnClickListener(v->{
            Intent intent = new Intent(UserActivity.this, AddCouponActivity.class);
            startActivity(intent);
        });

        // 쿠폰 현황 페이지 이동
        tvNowCoupon.setOnClickListener(v->{
            Intent intent = new Intent(UserActivity.this, CheckCouponActivity.class);
            startActivity(intent);
        });

        // 기타 이미지뷰 이벤트 처리
        ImageView imgInfo = findViewById(R.id.imginfo4);
        imgInfo.setOnClickListener(v -> {
            Intent intent = new Intent(UserActivity.this, AdvInfoActivity.class);
            startActivity(intent);
        });

        ImageView imgMenu = findViewById(R.id.imghome4);
        imgMenu.setOnClickListener(v -> {
            // 홈 이미지뷰 클릭 시 현재 액티비티를 다시 시작하는 것은 의미가 없습니다.
            // 필요한 경우 다른 활동으로 이동하도록 수정해야 합니다.
            // 예를 들어, MainActivity로 이동하도록 수정할 수 있습니다.
            Intent intent = new Intent(UserActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
