package com.project.project3.controller.advertiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.project3.R;

public class AddCouponActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coupon);

        EditText et1000 = findViewById(R.id.et1000);
        Button btn1000 = findViewById(R.id.btn1000);

        EditText et2000 = findViewById(R.id.et2000);
        Button btn2000 = findViewById(R.id.btn2000);

        EditText et3000 = findViewById(R.id.et3000);
        Button btn3000 = findViewById(R.id.btn3000);

        EditText et5000 = findViewById(R.id.et5000);
        Button btn5000 = findViewById(R.id.btn5000);

        Button btnAddCoupon = findViewById(R.id.btnCheckCoupon);

        btnAddCoupon.setOnClickListener(v -> {
            Toast.makeText(this, "쿠폰이 발급 완료됐습니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        });


    }
}