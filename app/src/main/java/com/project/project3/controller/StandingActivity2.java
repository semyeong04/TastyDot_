package com.project.project3.controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.project.project3.R;

import java.util.ArrayList;

public class StandingActivity2 extends AppCompatActivity {

    private Spinner spinner2;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standing2);

        arrayList = new ArrayList<>();
        arrayList.add("철수");
        arrayList.add("영희");
        arrayList.add("람휘");
        arrayList.add("녹지");
        arrayList.add("치치");
        arrayList.add("양가");
        arrayList.add("용병");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);

        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner2.setAdapter(arrayAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),arrayList.get(i)+"가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    출처: https://bottlecok.tistory.com/entry/안드로이드-스피너Spinner이용하기-ArrayList-ArrayAdapter사용 [잡캐의 IT 꿀팁:티스토리]
}