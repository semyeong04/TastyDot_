package com.project.project3.controller.user;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.project3.R;
import com.project.project3.adapterViewholder.SearchAdapter;
import com.project.project3.model.SearchVO;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserSearchFragment extends Fragment {
    private RecyclerView rv_search;
    private Button kr_01,kr_02, kr_03, kr_04, kr_05, cn_01, cn_02, cn_03, cn_04, cn_05, jp_01, jp_02, jp_03, jp_04, jp_05, ws_01, ws_02, ws_03, ws_04,snack_01, snack_02,snack_03,snack_04,snack_05, cafe_01, cafe_02, cafe_03, cafe_04, cafe_05 ;
    private ArrayList<SearchVO> items = new ArrayList<>();
    private SearchAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_search, container, false);
        rv_search = view.findViewById(R.id.rv_search);
        // 검색 결과를 담을 ArrayList 생성
        ArrayList<SearchVO> items = new ArrayList<>();
        // items.add(new SearchVO("스타벅스", "아메리카노"));

        // 검색 결과를 표시할 어댑터 생성 및 설정
        SearchAdapter adapter = new SearchAdapter(items);
        rv_search.setAdapter(adapter);

        // 리사이클러뷰 레이아웃 매니저 설정
        rv_search.setLayoutManager(new LinearLayoutManager(getContext()));

        // 카테고리별 스크롤뷰 초기 설정
        setupCategoryListeners(view);

        // 초기에 모든 스크롤뷰를 숨김
        hideAllScrollViews(view);

        // 통신하기
        kr_01 = view.findViewById(R.id.kr_01);
        kr_02 = view.findViewById(R.id.kr_02);
        kr_03 = view.findViewById(R.id.kr_03);
        kr_04 = view.findViewById(R.id.kr_04);
        kr_05 = view.findViewById(R.id.kr_05);

        kr_01.setOnClickListener(new View.OnClickListener() { // 한식
            @Override
            public void onClick(View v) {
                Log.d("디버그로그", "onClick: 클릭 이벤트 처리 시작");
                // OkHttpClient client = new OkHttpClient();
                // OkHttpClient에 타임아웃 설정 추가
                OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(300, TimeUnit.SECONDS) // 연결 타임아웃
                    .writeTimeout(300, TimeUnit.SECONDS) // 쓰기 타임아웃
                    .readTimeout(300, TimeUnit.SECONDS) // 읽기 타임아웃
                    .build();
                String koreanFoodText = "";
                try {
                    Log.d("디버그로그", "onClick: URL 인코딩 시도");
                    koreanFoodText = URLEncoder.encode(kr_01.getText().toString(), StandardCharsets.UTF_8.name());
                    Log.d("디버그로그", "onClick: URL 인코딩 성공 - " + koreanFoodText);
                    Toast.makeText(getActivity(), koreanFoodText, Toast.LENGTH_SHORT).show();


                } catch (UnsupportedEncodingException e) {
                    // 이 경우는 발생하지 않습니다.
                    Log.e("디버그로그", "onClick: UnsupportedEncodingException 발생", e);
                }

                String url = "http://192.168.219.106:5000/korean_food?keyword=한식&keyword2=" + koreanFoodText;
                Log.d("디버그로그", "onClick: 요청 URL - " + url);
                Request request = new Request.Builder()
                    .url(url)
                    .build();
                Log.d("디버그로그", "onClick: 사용자에게 Toast 메시지 표시 - " + koreanFoodText);


                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        // 요청 실패 시 호출되는 메서드
                        Log.e("디버그로그", "onFailure: 요청 실패", e);
                        getActivity().runOnUiThread(() -> {
                            if (getActivity() != null) {
                                Log.d("디버그로그", "getActivity() 호출 성공.");
                            } else {
                                Log.e("디버그로그", "getActivity() 호출 실패. Activity가 null입니다.");
                            }

                            // 토스트 메시지 표시 테스트
                            if (getContext() != null) {
                                Toast.makeText(getContext(), "테스트 토스트 메시지", Toast.LENGTH_SHORT).show();
                            }
                            // UI 스레드에서 실행 전 로그
                            Log.d("디버그로그", "onFailure: UI 스레드에서 Toast 메시지 표시 - 요청 실패: " + e.getMessage());
                            if (getContext() != null) {
                                Log.d("디버그로그", "getContext() 호출 성공.");
                            } else {
                                Log.e("디버그로그", "getContext() 호출 실패. Context가 null입니다.");
                            }
                            Toast.makeText(getContext(), "요청 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Log.d("디버그로그", "onResponse: 서버로부터 응답 받음.");
                        // 요청 응답 받았을 때 호출되는 메서드
                        if (!response.isSuccessful()) {
                            Log.d("디버그로그", "onResponse: 응답 실패. 코드: " + response);
                            throw new IOException("Unexpected code " + response);
                        }
                        final String responseData = response.body().string();
                        Log.d("디버그로그", "onResponse: 응답 데이터: " + responseData);

                        getActivity().runOnUiThread(() -> {
                            try {
                                JSONArray jsonArray = new JSONArray(responseData);
                                Log.d("디버그로그", "UI Thread: 항목 목록 초기화");
                                items.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject storeObject = jsonArray.getJSONObject(i);
                                    Log.d("디버그로그", "UI Thread: 항목 추가 - " + storeObject.getString("storeName"));
                                    items.add(new SearchVO(storeObject.getString("storeName"),
                                        storeObject.getString("address"),
                                        storeObject.getString("menu"),
                                        storeObject.getString("hashtag")));
                                }
                                Log.d("디버그로그", "UI Thread: 어댑터에 데이터 변경을 알림");
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                Log.e("SearchFragment", "JSON 파싱 오류", e);
                            }
                        });
                    }
                });
            }

        });

        // 중식
        cn_01 = view.findViewById(R.id.cn_01);
        cn_01.setOnClickListener(new View.OnClickListener() { // 한식
            @Override
            public void onClick(View v) {
                Log.d("디버그로그", "onClick: 클릭 이벤트 처리 시작");
                // OkHttpClient client = new OkHttpClient();
                // OkHttpClient에 타임아웃 설정 추가
                OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(300, TimeUnit.SECONDS) // 연결 타임아웃
                    .writeTimeout(300, TimeUnit.SECONDS) // 쓰기 타임아웃
                    .readTimeout(300, TimeUnit.SECONDS) // 읽기 타임아웃
                    .build();
                String cnFoodText = "";
                try {
                    Log.d("디버그로그", "onClick: URL 인코딩 시도");
                    cnFoodText = URLEncoder.encode(cn_01.getText().toString(), StandardCharsets.UTF_8.name());
                    Log.d("디버그로그", "onClick: URL 인코딩 성공 - " + cnFoodText);
                    Toast.makeText(getActivity(), cnFoodText, Toast.LENGTH_SHORT).show();


                } catch (UnsupportedEncodingException e) {
                    // 이 경우는 발생하지 않습니다.
                    Log.e("디버그로그", "onClick: UnsupportedEncodingException 발생", e);
                }

                String url = "http://192.168.219.106:5000/cn_food?keyword=중식&keyword2=" + cnFoodText;
                Log.d("디버그로그", "onClick: 요청 URL - " + url);
                Request request = new Request.Builder()
                    .url(url)
                    .build();
                Log.d("디버그로그", "onClick: 사용자에게 Toast 메시지 표시 - " + cnFoodText);


                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        // 요청 실패 시 호출되는 메서드
                        Log.e("디버그로그", "onFailure: 요청 실패", e);
                        getActivity().runOnUiThread(() -> {
                            if (getActivity() != null) {
                                Log.d("디버그로그", "getActivity() 호출 성공.");
                            } else {
                                Log.e("디버그로그", "getActivity() 호출 실패. Activity가 null입니다.");
                            }

                            // 토스트 메시지 표시 테스트
                            if (getContext() != null) {
                                Toast.makeText(getContext(), "테스트 토스트 메시지", Toast.LENGTH_SHORT).show();
                            }
                            // UI 스레드에서 실행 전 로그
                            Log.d("디버그로그", "onFailure: UI 스레드에서 Toast 메시지 표시 - 요청 실패: " + e.getMessage());
                            if (getContext() != null) {
                                Log.d("디버그로그", "getContext() 호출 성공.");
                            } else {
                                Log.e("디버그로그", "getContext() 호출 실패. Context가 null입니다.");
                            }
                            Toast.makeText(getContext(), "요청 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Log.d("디버그로그", "onResponse: 서버로부터 응답 받음.");
                        // 요청 응답 받았을 때 호출되는 메서드
                        if (!response.isSuccessful()) {
                            Log.d("디버그로그", "onResponse: 응답 실패. 코드: " + response);
                            throw new IOException("Unexpected code " + response);
                        }
                        final String responseData = response.body().string();
                        Log.d("디버그로그", "onResponse: 응답 데이터: " + responseData);

                        getActivity().runOnUiThread(() -> {
                            try {
                                JSONArray jsonArray = new JSONArray(responseData);
                                Log.d("디버그로그", "UI Thread: 항목 목록 초기화");
                                items.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject storeObject = jsonArray.getJSONObject(i);
                                    Log.d("디버그로그", "UI Thread: 항목 추가 - " + storeObject.getString("storeName"));
                                    items.add(new SearchVO(storeObject.getString("storeName"),
                                        storeObject.getString("address"),
                                        storeObject.getString("menu"),
                                        storeObject.getString("hashtag")));
                                }
                                Log.d("디버그로그", "UI Thread: 어댑터에 데이터 변경을 알림");
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                Log.e("SearchFragment", "JSON 파싱 오류", e);
                            }
                        });
                    }
                });
            }

        });


        // 일식
        jp_01 = view.findViewById(R.id.jp_01);
        jp_01.setOnClickListener(new View.OnClickListener() { // 한식
            @Override
            public void onClick(View v) {
                Log.d("디버그로그", "onClick: 클릭 이벤트 처리 시작");
                // OkHttpClient client = new OkHttpClient();
                // OkHttpClient에 타임아웃 설정 추가
                OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(300, TimeUnit.SECONDS) // 연결 타임아웃
                    .writeTimeout(300, TimeUnit.SECONDS) // 쓰기 타임아웃
                    .readTimeout(300, TimeUnit.SECONDS) // 읽기 타임아웃
                    .build();
                String jpFoodText = "";
                try {
                    Log.d("디버그로그", "onClick: URL 인코딩 시도");
                    jpFoodText = URLEncoder.encode(jp_01.getText().toString(), StandardCharsets.UTF_8.name());
                    Log.d("디버그로그", "onClick: URL 인코딩 성공 - " + jpFoodText);
                    Toast.makeText(getActivity(), jpFoodText, Toast.LENGTH_SHORT).show();


                } catch (UnsupportedEncodingException e) {
                    // 이 경우는 발생하지 않습니다.
                    Log.e("디버그로그", "onClick: UnsupportedEncodingException 발생", e);
                }

                String url = "http://192.168.219.106:5000/jp_food?keyword=일식&keyword2=" + jpFoodText;
                Log.d("디버그로그", "onClick: 요청 URL - " + url);
                Request request = new Request.Builder()
                    .url(url)
                    .build();
                Log.d("디버그로그", "onClick: 사용자에게 Toast 메시지 표시 - " + jpFoodText);


                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        // 요청 실패 시 호출되는 메서드
                        Log.e("디버그로그", "onFailure: 요청 실패", e);
                        getActivity().runOnUiThread(() -> {
                            if (getActivity() != null) {
                                Log.d("디버그로그", "getActivity() 호출 성공.");
                            } else {
                                Log.e("디버그로그", "getActivity() 호출 실패. Activity가 null입니다.");
                            }

                            // 토스트 메시지 표시 테스트
                            if (getContext() != null) {
                                Toast.makeText(getContext(), "테스트 토스트 메시지", Toast.LENGTH_SHORT).show();
                            }
                            // UI 스레드에서 실행 전 로그
                            Log.d("디버그로그", "onFailure: UI 스레드에서 Toast 메시지 표시 - 요청 실패: " + e.getMessage());
                            if (getContext() != null) {
                                Log.d("디버그로그", "getContext() 호출 성공.");
                            } else {
                                Log.e("디버그로그", "getContext() 호출 실패. Context가 null입니다.");
                            }
                            Toast.makeText(getContext(), "요청 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Log.d("디버그로그", "onResponse: 서버로부터 응답 받음.");
                        // 요청 응답 받았을 때 호출되는 메서드
                        if (!response.isSuccessful()) {
                            Log.d("디버그로그", "onResponse: 응답 실패. 코드: " + response);
                            throw new IOException("Unexpected code " + response);
                        }
                        final String responseData = response.body().string();
                        Log.d("디버그로그", "onResponse: 응답 데이터: " + responseData);

                        getActivity().runOnUiThread(() -> {
                            try {
                                JSONArray jsonArray = new JSONArray(responseData);
                                Log.d("디버그로그", "UI Thread: 항목 목록 초기화");
                                items.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject storeObject = jsonArray.getJSONObject(i);
                                    Log.d("디버그로그", "UI Thread: 항목 추가 - " + storeObject.getString("storeName"));
                                    items.add(new SearchVO(storeObject.getString("storeName"),
                                        storeObject.getString("address"),
                                        storeObject.getString("menu"),
                                        storeObject.getString("hashtag")));
                                }
                                Log.d("디버그로그", "UI Thread: 어댑터에 데이터 변경을 알림");
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                Log.e("SearchFragment", "JSON 파싱 오류", e);
                            }
                        });
                    }
                });
            }

        });

        // 양식
        ws_01 = view.findViewById(R.id.jp_01);
        ws_01.setOnClickListener(new View.OnClickListener() { // 한식
            @Override
            public void onClick(View v) {
                Log.d("디버그로그", "onClick: 클릭 이벤트 처리 시작");
                // OkHttpClient client = new OkHttpClient();
                // OkHttpClient에 타임아웃 설정 추가
                OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(300, TimeUnit.SECONDS) // 연결 타임아웃
                    .writeTimeout(300, TimeUnit.SECONDS) // 쓰기 타임아웃
                    .readTimeout(300, TimeUnit.SECONDS) // 읽기 타임아웃
                    .build();
                String wsFoodText = "";
                try {
                    Log.d("디버그로그", "onClick: URL 인코딩 시도");
                    wsFoodText = URLEncoder.encode(jp_01.getText().toString(), StandardCharsets.UTF_8.name());
                    Log.d("디버그로그", "onClick: URL 인코딩 성공 - " + wsFoodText);
                    Toast.makeText(getActivity(), wsFoodText, Toast.LENGTH_SHORT).show();


                } catch (UnsupportedEncodingException e) {
                    // 이 경우는 발생하지 않습니다.
                    Log.e("디버그로그", "onClick: UnsupportedEncodingException 발생", e);
                }

                String url = "http://192.168.219.106:5000/ws_food?keyword=양식&keyword2=" + wsFoodText;
                Log.d("디버그로그", "onClick: 요청 URL - " + url);
                Request request = new Request.Builder()
                    .url(url)
                    .build();
                Log.d("디버그로그", "onClick: 사용자에게 Toast 메시지 표시 - " + wsFoodText);


                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        // 요청 실패 시 호출되는 메서드
                        Log.e("디버그로그", "onFailure: 요청 실패", e);
                        getActivity().runOnUiThread(() -> {
                            if (getActivity() != null) {
                                Log.d("디버그로그", "getActivity() 호출 성공.");
                            } else {
                                Log.e("디버그로그", "getActivity() 호출 실패. Activity가 null입니다.");
                            }

                            // 토스트 메시지 표시 테스트
                            if (getContext() != null) {
                                Toast.makeText(getContext(), "테스트 토스트 메시지", Toast.LENGTH_SHORT).show();
                            }
                            // UI 스레드에서 실행 전 로그
                            Log.d("디버그로그", "onFailure: UI 스레드에서 Toast 메시지 표시 - 요청 실패: " + e.getMessage());
                            if (getContext() != null) {
                                Log.d("디버그로그", "getContext() 호출 성공.");
                            } else {
                                Log.e("디버그로그", "getContext() 호출 실패. Context가 null입니다.");
                            }
                            Toast.makeText(getContext(), "요청 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Log.d("디버그로그", "onResponse: 서버로부터 응답 받음.");
                        // 요청 응답 받았을 때 호출되는 메서드
                        if (!response.isSuccessful()) {
                            Log.d("디버그로그", "onResponse: 응답 실패. 코드: " + response);
                            throw new IOException("Unexpected code " + response);
                        }
                        final String responseData = response.body().string();
                        Log.d("디버그로그", "onResponse: 응답 데이터: " + responseData);

                        getActivity().runOnUiThread(() -> {
                            try {
                                JSONArray jsonArray = new JSONArray(responseData);
                                Log.d("디버그로그", "UI Thread: 항목 목록 초기화");
                                items.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject storeObject = jsonArray.getJSONObject(i);
                                    Log.d("디버그로그", "UI Thread: 항목 추가 - " + storeObject.getString("storeName"));
                                    items.add(new SearchVO(storeObject.getString("storeName"),
                                        storeObject.getString("address"),
                                        storeObject.getString("menu"),
                                        storeObject.getString("hashtag")));
                                }
                                Log.d("디버그로그", "UI Thread: 어댑터에 데이터 변경을 알림");
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                Log.e("SearchFragment", "JSON 파싱 오류", e);
                            }
                        });
                    }
                });
            }

        });

        // 분식

        // 카페
        cafe_01 = view.findViewById(R.id.cafe_01);
        cafe_01.setOnClickListener(new View.OnClickListener() { // 한식
            @Override
            public void onClick(View v) {
                Log.d("디버그로그", "onClick: 클릭 이벤트 처리 시작");
                // OkHttpClient client = new OkHttpClient();
                // OkHttpClient에 타임아웃 설정 추가
                OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(500, TimeUnit.SECONDS) // 연결 타임아웃
                    .writeTimeout(500, TimeUnit.SECONDS) // 쓰기 타임아웃
                    .readTimeout(500, TimeUnit.SECONDS) // 읽기 타임아웃
                    .build();
                String cafeFoodText = "";
                try {
                    Log.d("디버그로그", "onClick: URL 인코딩 시도");
                    cafeFoodText = URLEncoder.encode(cafe_01.getText().toString(), StandardCharsets.UTF_8.name());
                    Log.d("디버그로그", "onClick: URL 인코딩 성공 - " + cafeFoodText);
                    Toast.makeText(getActivity(), cafeFoodText, Toast.LENGTH_SHORT).show();


                } catch (UnsupportedEncodingException e) {
                    // 이 경우는 발생하지 않습니다.
                    Log.e("디버그로그", "onClick: UnsupportedEncodingException 발생", e);
                }

                String url = "http://192.168.219.106:5000/cafe_food?keyword=카페&keyword2=" + cafeFoodText;
                Log.d("디버그로그", "onClick: 요청 URL - " + url);
                Request request = new Request.Builder()
                    .url(url)
                    .build();
                Log.d("디버그로그", "onClick: 사용자에게 Toast 메시지 표시 - " + cafeFoodText);


                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        // 요청 실패 시 호출되는 메서드
                        Log.e("디버그로그", "onFailure: 요청 실패", e);
                        getActivity().runOnUiThread(() -> {
                            if (getActivity() != null) {
                                Log.d("디버그로그", "getActivity() 호출 성공.");
                            } else {
                                Log.e("디버그로그", "getActivity() 호출 실패. Activity가 null입니다.");
                            }

                            // 토스트 메시지 표시 테스트
                            if (getContext() != null) {
                                Toast.makeText(getContext(), "테스트 토스트 메시지", Toast.LENGTH_SHORT).show();
                            }
                            // UI 스레드에서 실행 전 로그
                            Log.d("디버그로그", "onFailure: UI 스레드에서 Toast 메시지 표시 - 요청 실패: " + e.getMessage());
                            if (getContext() != null) {
                                Log.d("디버그로그", "getContext() 호출 성공.");
                            } else {
                                Log.e("디버그로그", "getContext() 호출 실패. Context가 null입니다.");
                            }
                            Toast.makeText(getContext(), "요청 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Log.d("디버그로그", "onResponse: 서버로부터 응답 받음.");
                        // 요청 응답 받았을 때 호출되는 메서드
                        if (!response.isSuccessful()) {
                            Log.d("디버그로그", "onResponse: 응답 실패. 코드: " + response);
                            throw new IOException("Unexpected code " + response);
                        }
                        final String responseData = response.body().string();
                        Log.d("디버그로그", "onResponse: 응답 데이터: " + responseData);

                        getActivity().runOnUiThread(() -> {
                            try {
                                JSONArray jsonArray = new JSONArray(responseData);
                                Log.d("디버그로그", "UI Thread: 항목 목록 초기화");
                                items.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject storeObject = jsonArray.getJSONObject(i);
                                    Log.d("디버그로그", "UI Thread: 항목 추가 - " + storeObject.getString("storeName"));
                                    items.add(new SearchVO(storeObject.getString("storeName"),
                                        storeObject.getString("address"),
                                        storeObject.getString("menu"),
                                        storeObject.getString("hashtag")));
                                }
                                Log.d("디버그로그", "UI Thread: 어댑터에 데이터 변경을 알림");
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                Log.e("SearchFragment", "JSON 파싱 오류", e);
                            }
                        });
                    }
                });
            }

        });



// onclick
        return view;
    }


    // 모든 스크롤뷰를 숨기는 메소드
    private void hideAllScrollViews(View view) {
        final HorizontalScrollView scrollView = view.findViewById(R.id.horizontalScrollView);
        final HorizontalScrollView scrollView2 = view.findViewById(R.id.horizontalScrollView2);
        final HorizontalScrollView scrollView3 = view.findViewById(R.id.horizontalScrollView3);
        final HorizontalScrollView scrollView4 = view.findViewById(R.id.horizontalScrollView4);
        final HorizontalScrollView scrollView5 = view.findViewById(R.id.horizontalScrollView5);
        final HorizontalScrollView scrollView6 = view.findViewById(R.id.horizontalScrollView6);

        scrollView.setVisibility(View.GONE);
        scrollView2.setVisibility(View.GONE);
        scrollView3.setVisibility(View.GONE);
        scrollView4.setVisibility(View.GONE);
        scrollView5.setVisibility(View.GONE);
        scrollView6.setVisibility(View.GONE);
    }

    // 카테고리별 스크롤뷰 리스너 설정 메소드
    private void setupCategoryListeners(View view) {
        TextView korean = view.findViewById(R.id.korean);
        final HorizontalScrollView scrollView = view.findViewById(R.id.horizontalScrollView);
        korean.setOnClickListener(v -> toggleScrollViewVisibility(view, scrollView));

        TextView chinese = view.findViewById(R.id.chinese);
        final HorizontalScrollView scrollView2 = view.findViewById(R.id.horizontalScrollView2);
        chinese.setOnClickListener(v -> toggleScrollViewVisibility(view, scrollView2));

        TextView japanese = view.findViewById(R.id.japanese);
        final HorizontalScrollView scrollView3 = view.findViewById(R.id.horizontalScrollView3);
        japanese.setOnClickListener(v -> toggleScrollViewVisibility(view, scrollView3));

        TextView western = view.findViewById(R.id.western);
        final HorizontalScrollView scrollView4 = view.findViewById(R.id.horizontalScrollView4);
        western.setOnClickListener(v -> toggleScrollViewVisibility(view, scrollView4));

        TextView snack = view.findViewById(R.id.snack);
        final HorizontalScrollView scrollView5 = view.findViewById(R.id.horizontalScrollView5);
        snack.setOnClickListener(v -> toggleScrollViewVisibility(view, scrollView5));

        TextView cafe = view.findViewById(R.id.cafe);
        final HorizontalScrollView scrollView6 = view.findViewById(R.id.horizontalScrollView6);
        cafe.setOnClickListener(v -> toggleScrollViewVisibility(view, scrollView6));
    }

    // 스크롤뷰 가시성을 토글하는 메소드
    private void toggleScrollViewVisibility(View parentView, HorizontalScrollView scrollView) {
        hideAllScrollViews(parentView); // 먼저 모든 스크롤뷰를 숨김
        if (scrollView.getVisibility() == View.VISIBLE) {
            scrollView.setVisibility(View.GONE);
        } else {
            scrollView.setVisibility(View.VISIBLE);
        }
    }



    private String decodeUnicode(String unicodeStr) { // 유니코드 문자열을 해독하는 메서드 정의
        Pattern pattern = Pattern.compile("\\\\u([0-9a-f]{4})", Pattern.CASE_INSENSITIVE);
        // 유니코드 패턴을 찾기 위한 정규표현식 패턴 생성

        Matcher matcher = pattern.matcher(unicodeStr);
        // 주어진 문자열에서 패턴 매칭을 수행할 Matcher 객체 생성

        StringBuffer buffer = new StringBuffer(unicodeStr.length());
        // 문자열을 담을 StringBuffer 생성
        while (matcher.find()) { // 패턴 매칭이 있을 때까지 반복
            matcher.appendReplacement(buffer, String.valueOf((char) Integer.parseInt(matcher.group(1), 16)));
            // 매칭된 유니코드를 해당하는 문자로 변환하여 StringBuffer에 추가
        }
        matcher.appendTail(buffer);
        // 나머지 문자열을 StringBuffer에 추가
        return buffer.toString();
        // StringBuffer를 문자열로 변환하여 반환
    }

}



