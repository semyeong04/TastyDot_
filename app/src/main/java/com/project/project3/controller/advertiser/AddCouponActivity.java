package com.project.project3.controller.advertiser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.project.project3.R;

public class AddCouponActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coupon);

        EditText et1000 = findViewById(R.id.et1000);
        EditText et2000 = findViewById(R.id.et2000);
        EditText et3000 = findViewById(R.id.et3000);
        EditText et5000 = findViewById(R.id.et5000);

        Button btnAddCoupon = findViewById(R.id.btnJoin);
        btnAddCoupon.setOnClickListener(v -> {
            Toast.makeText(this, "쿠폰이 발급 완료됐습니다.", Toast.LENGTH_SHORT).show();
            intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        });

        ImageView imgInfo = findViewById(R.id.imginfo1);
        imgInfo.setOnClickListener(v -> {
            intent = new Intent(this, AdvInfoActivity.class);
            startActivity(intent);
        });

        ImageView imgMenu = findViewById(R.id.imghome1);
        imgMenu.setOnClickListener(v -> {
            intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        });


    }
}