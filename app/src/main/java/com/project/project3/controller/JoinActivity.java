package com.project.project3.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.project.project3.R;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        EditText etJoinId = findViewById(R.id.etJoinId);
        EditText etJoinPw = findViewById(R.id.etJoinPw);
        EditText etJoinName = findViewById(R.id.etJoinName);
        EditText etJoinEmail = findViewById(R.id.etJoinEmail);
        Button btnJoin = findViewById(R.id.btnCheckCoupon);

        btnJoin.setOnClickListener(v -> {
            String id = etJoinName.getText().toString();
            String pw = etJoinPw.getText().toString();
            String name = etJoinName.getText().toString();
            String email = etJoinEmail.getText().toString();


            // 회원가입시 로그인페이지로 이동
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });


    }
}