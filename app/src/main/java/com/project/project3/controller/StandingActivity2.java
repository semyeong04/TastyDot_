package com.project.project3.controller; // 패키지 선언 추가

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.snackbar.Snackbar;
import com.project.project3.R;

public class StandingActivity2 extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standing2);


        // 웹뷰 초기화
        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true); // JavaScript 활성화
        webView.setWebViewClient(new WebViewClient()); // 내부 웹페이지를 로드하기 위해 WebViewClient 설정
        webView.loadUrl("https://www.blogger.com/blog/post/edit/432173056677444632/3935452453798781395?hl=ko"); // 웹뷰에 표시할 URL 설정
        webView.setVisibility(View.GONE); // 웹뷰를 일단 숨김

        // 주소 찾기 버튼 클릭 이벤트 처리
        findViewById(R.id.btn_webview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 웹뷰가 보이도록 설정
                webView.setVisibility(View.VISIBLE);
            }
        });

        // 다음 버튼 및 나가기 버튼에 대한 클릭 이벤트 처리 등 기타 코드는 여기에 추가합니다.

        // 다음 버튼 클릭 이벤트 처리
        findViewById(R.id.btn_next2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StandingActivity2.this, StandingActivity3.class);
                startActivity(intent);
            }
        });

        // 나가기 버튼 클릭 이벤트 처리
        findViewById(R.id.btn_quit1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StandingActivity2.this, StandingActivity1.class);
                startActivity(intent);
            }
        });

        // Spinner를 레이아웃과 연결
        Spinner spinner = findViewById(R.id.tv_spinner);

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
