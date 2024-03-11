package com.project.project3.controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.project3.R;

import java.util.ArrayList;

public class StandingActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standing2);

        // Spinner를 레이아웃과 연결
        Spinner spinner = findViewById(R.id.spinner2);

        // Spinner에 표시할 옵션을 정의
        String[] spinnerOptions = getResources().getStringArray(R.array.spinner_options);

        // ArrayAdapter 생성 및 설정
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Spinner에 adapter 설정
        spinner.setAdapter(adapter);

        // Spinner 아이템 선택 이벤트 처리
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 선택된 항목의 텍스트 가져오기
                String selectedItem = parent.getItemAtPosition(position).toString();
                // 토스트 메시지로 선택된 항목 출력
                Toast.makeText(StandingActivity2.this, selectedItem + " 선택됨", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 아무것도 선택되지 않았을 때 처리할 작업
            }
        });
    }
}