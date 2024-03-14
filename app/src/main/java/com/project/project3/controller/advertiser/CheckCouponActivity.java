package com.project.project3.controller.advertiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.project.project3.R;

public class CheckCouponActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_coupon);

        Button btnCheckCoupon = findViewById(R.id.btnCheckCoupon);
        btnCheckCoupon.setOnClickListener(v -> {

            // 클릭시 유저액티비티로 이동
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        });

    }
}