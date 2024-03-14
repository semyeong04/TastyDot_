package com.project.project3.controller.advertiser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
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
            intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        });

        BottomNavigationView bnv = findViewById(R.id.bnv_adv);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // itme  : 내가 클릭한 항목에 대한 정보
                // 정보 : 속성,id,title,icon...
                // item.getItemId() : 항목의 id값을 가져오는 방법
                int itemId = item.getItemId();
                if (itemId == R.id.bnv_home) {
                    intent = new Intent(AddCouponActivity.this, UserActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.bnv_menu) {
                    intent = new Intent(AddCouponActivity.this, MenuRegiActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.bnv_addcoupon) {
                    intent = new Intent(AddCouponActivity.this, AddCouponActivity.class);
                    startActivity(intent);

                } else if (itemId == R.id.bnv_checkcoupon) {
                    intent = new Intent(AddCouponActivity.this, CheckCouponActivity.class);
                    startActivity(intent);

                }else if (itemId == R.id.bnv_info) {
                    intent = new Intent(AddCouponActivity.this, CheckCouponActivity.class);
                    startActivity(intent);

                }
                // 항목에 대한 클릭 이벤트를 감지
                // false : 클릭한번하고 이벤트가 계속 된다고 생각함
                // true : 클릭 후 이벤트 종료
                return true;
            }
        });

    }
}