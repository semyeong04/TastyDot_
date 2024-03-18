package com.project.project3.controller.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.project.project3.R;
import com.project.project3.controller.advertiser.MenuRegiActivity;
import com.project.project3.controller.advertiser.UserActivity;

import java.util.ArrayList;
import java.util.List;

public class JoinUserActivity extends AppCompatActivity {

    private List<String> selectedChecks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_user);

        EditText name = findViewById(R.id.etUserName);
        EditText id = findViewById(R.id.etUserId);
        EditText pw = findViewById(R.id.etUserPw);
        EditText email = findViewById(R.id.etUserEmail);
        CheckBox ckBox1 = findViewById(R.id.cbKfood);
        CheckBox ckBox2 = findViewById(R.id.cbCfood);
        CheckBox ckBox3 = findViewById(R.id.cbJfood);
        CheckBox ckBox4 = findViewById(R.id.cbWfood);
        CheckBox ckBox5 = findViewById(R.id.cbSnack);
        CheckBox ckBox6 = findViewById(R.id.cbCafe);

        // 체크박스 선택시 selectedChecks리스트에 저장
        ckBox1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedChecks.add("한식");
            } else {
                selectedChecks.remove("한식");
            }
        });
        ckBox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedChecks.add("중식");
            } else {
                selectedChecks.remove("중식");
            }
        });
        ckBox3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedChecks.add("일식");
            } else {
                selectedChecks.remove("일식");
            }
        });
        ckBox4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedChecks.add("양식");
            } else {
                selectedChecks.remove("양식");
            }
        });
        ckBox5.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedChecks.add("분식");
            } else {
                selectedChecks.remove("분식");
            }
        });
        ckBox6.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedChecks.add("카페");
            } else {
                selectedChecks.remove("카페");
            }
        });

        Button join = findViewById(R.id.btnUserJoin);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });




    }

    void showDialog() {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(JoinUserActivity.this)
                .setTitle("회원 가입")
                .setMessage("회원가입 하시겠습니까?")
                .setPositiveButton("네", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(JoinUserActivity.this, "가입 완료.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(JoinUserActivity.this, UserLoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })

                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(JoinUserActivity.this, "가입 취소.", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
}