package com.project.project3.controller.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.project3.R;

public class UserLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        EditText userId = findViewById(R.id.etUserLoginId);
        EditText userPw = findViewById(R.id.etUserLoginPw);
        Button login = findViewById(R.id.btnUserLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = userId.getText().toString();
                String pw = userPw.getText().toString();
                if(id.equals("test")&&pw.equals("test")){
                    Intent intent = new Intent(UserLoginActivity.this, UserMainActivity.class);
                    startActivity(intent);
                    Toast.makeText(UserLoginActivity.this, "로그인 완료", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UserLoginActivity.this, "아이디 또는 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}