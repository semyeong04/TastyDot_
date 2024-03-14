package com.project.project3.controller.advertiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.project3.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnLogin = findViewById(R.id.btnLogin);
        EditText etId= findViewById(R.id.etId);
        EditText etPw= findViewById(R.id.etPw);
        TextView creatAc = findViewById(R.id.creatAc);

        // 로그인
        btnLogin.setOnClickListener( v -> {
            String id = etId.getText().toString();
            String pw = etPw.getText().toString();

            if (id.equals("test")&&pw.equals("test")){
                Toast.makeText(this,"로그인 완료",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, UserActivity.class);
                startActivity(intent);

            }else {
                Toast.makeText(this, "아이디 또는 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
            }

        });

        // 계정 생성
        creatAc.setOnClickListener(v -> {
            Intent intent = new Intent(this, JoinActivity.class);
            startActivity(intent);
        });

    }
}