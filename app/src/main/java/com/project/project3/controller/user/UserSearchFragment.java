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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request.Method;

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

        // 카테고리별 스크롤뷰 초기 설정
        setupCategoryListeners(view);

        // 검색 결과를 담을 ArrayList 생성
//        ArrayList<SearchVO> items = new ArrayList<>();
        // items.add(new SearchVO("스타벅스", "아메리카노"));
        // 리스트 아이템 데이터 생성
        items = new ArrayList<>();
        getStore();
        ArrayList<SearchVO> Searchs = items;

        // 검색 결과를 표시할 어댑터 생성 및 설정
        SearchAdapter adapter = new SearchAdapter(Searchs);
//        SearchAdapter adapter = new SearchAdapter(items);
        rv_search.setAdapter(adapter);

        // 리사이클러뷰 레이아웃 매니저 설정
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        rv_search.setLayoutManager(layoutManager);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        rv_search.setLayoutManager(manager);


        // 초기에 모든 스크롤뷰를 숨김
        hideAllScrollViews(view);

        // 통신하기
        kr_01 = view.findViewById(R.id.kr_01);
        kr_01.setOnClickListener(new View.OnClickListener() { // 한식
            @Override
            public void onClick(View v) { // 클릭 이벤트 처리를 위한 메서드
                OkHttpClient client = new OkHttpClient(); // OkHttpClient 객체 생성
                String koreanFoodText = ""; // 한식 텍스트 초기화
                try {
                    koreanFoodText = URLEncoder.encode(kr_01.getText().toString(), StandardCharsets.UTF_8.name());
                    // 입력된 한식 텍스트를 UTF-8 형식으로 인코딩
                    Toast.makeText(getActivity(), koreanFoodText, Toast.LENGTH_SHORT).show();
                } catch (UnsupportedEncodingException e) {
                    // 이 경우는 발생하지 않습니다.ㅅ
                }

                String url = "http://192.168.219.106:5000/korean_food?keyword=한식&keyword2=" + koreanFoodText; // 요청할 URL 생성
                Request request = new Request.Builder() // HTTP 요청 객체 생성
                        .url(url) // 요청할 URL 설정
                        .build(); // 요청 객체 빌드

                client.newCall(request).enqueue(new Callback() { // 비동기 방식으로 요청 보내고 응답 처리
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        // 요청 실패 시 호출되는 메서드
                        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "요청 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show()); // UI 스레드에서 Toast 메시지를 통해 실패 메시지 표시
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException { // 요청 응답 받았을 때 호출되는 메서드
                        if (!response.isSuccessful()) { // 응답이 성공적이지 않을 때
                            throw new IOException("Unexpected code " + response); // 예외 던지기
                        }
                        final String responseData = response.body().string(); // 응답 데이터를 문자열로 저장

                        getActivity().runOnUiThread(() -> { // UI 스레드에서 실행
                            try {
                                JSONArray jsonArray = new JSONArray(responseData); // 응답 데이터를 JSONArray로 파싱
                                items.clear(); // 기존 목록을 지우고 새 데이터로 업데이트
                                for (int i = 0; i < jsonArray.length(); i++) { // JSONArray 반복문
                                    JSONObject storeObject = jsonArray.getJSONObject(i); // JSONArray에서 JSONObject 가져오기
                                    String tv_name = storeObject.getString("name"); // 이름 가져오기
                                    String tv_add = storeObject.getString("addr"); // 주소 가져오기
                                    items.add(new SearchVO(tv_name, tv_add, "해시태그","4.9", R.drawable.camera)); // SearchVO 객체로 변환하여 목록에 추가
                                }
                                adapter.notifyDataSetChanged(); // 어댑터에 데이터 변경 알림
                            } catch (JSONException e) { // JSON 파싱 오류 처리
                                Log.e("UserSearchFragment", "JSON 파싱 에러: " + e.getMessage()); // 로그에 에러 메시지 출력
                            }
                        });
                    }
                });
            }

        });


        cn_01 = view.findViewById(R.id.cn_01);
        cn_01.setOnClickListener(new View.OnClickListener() { // 중식
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();
                String chineseFoodText = "";
                try {
                    chineseFoodText = URLEncoder.encode(cn_01.getText().toString(), StandardCharsets.UTF_8.name());

                } catch (UnsupportedEncodingException e) {
                    // 이 경우는 발생하지 않습니다.
                }

                String url = "http://192.168.219.106:5000/chinese_food?keyword=중식&keyword2=" + chineseFoodText;
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Toast.makeText(getActivity(), chineseFoodText, Toast.LENGTH_SHORT).show();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        // 요청 실패 시 호출되는 메서드
                        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "요청 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show()); // UI 스레드에서 Toast 메시지를 통해 실패 메시지 표시
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        // 요청 응답 받았을 때 호출되는 메서드
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        }
                        final String responseData = response.body().string();
                        Log.d("Response", responseData);

                        getActivity().runOnUiThread(() -> {
                            try {
                                JSONArray jsonArray = new JSONArray(responseData);
                                items.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject storeObject = jsonArray.getJSONObject(i);
                                    String tv_name = storeObject.getString("name");
                                    String tv_add = storeObject.getString("addr");
                                    items.add(new SearchVO(tv_name, tv_add,"해시태그","4.9", R.drawable.camera));
                                }
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                Log.e("UserSearchFragment", "JSON 파싱 에러: " + e.getMessage());
                            }
                        });
                    }
                });
            }

        });


        jp_01 = view.findViewById(R.id.jp_01);
        jp_01.setOnClickListener(new View.OnClickListener() { // 일식
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();
                String japaneseFoodText = "";
                try {
                    japaneseFoodText = URLEncoder.encode(jp_01.getText().toString(), StandardCharsets.UTF_8.name());

                } catch (UnsupportedEncodingException e) {
                    // 이 경우는 발생하지 않습니다.
                }

                String url = "http://192.168.219.106:5000/japanese_food?keyword=일식&keyword2=" + japaneseFoodText;
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Toast.makeText(getActivity(), japaneseFoodText, Toast.LENGTH_SHORT).show();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        // 요청 실패 시 호출되는 메서드
                        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "요청 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show()); // UI 스레드에서 Toast 메시지를 통해 실패 메시지 표시
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        // 요청 응답 받았을 때 호출되는 메서드
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        }
                        final String responseData = response.body().string();

                        getActivity().runOnUiThread(() -> {
                            try {
                                JSONArray jsonArray = new JSONArray(responseData);
                                items.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject storeObject = jsonArray.getJSONObject(i);
                                    String tv_name = storeObject.getString("name");
                                    String tv_add = storeObject.getString("addr");
                                    items.add(new SearchVO(tv_name, tv_add,"해시태그","4.9", R.drawable.camera));
                                }
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                Log.e("UserSearchFragment", "JSON 파싱 에러: " + e.getMessage());
                            }
                        });
                    }
                });
            }

        });


        ws_01 = view.findViewById(R.id.ws_01);
        ws_01.setOnClickListener(new View.OnClickListener() { // 양식
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();
                String westernFoodText = "";
                try {
                    westernFoodText = URLEncoder.encode(ws_01.getText().toString(), StandardCharsets.UTF_8.name());

                } catch (UnsupportedEncodingException e) {
                    // 이 경우는 발생하지 않습니다.
                }

                String url = "http://192.168.219.106:5000/western_food?keyword=양식&keyword2=" + westernFoodText;
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Toast.makeText(getActivity(), westernFoodText, Toast.LENGTH_SHORT).show();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        // 요청 실패 시 호출되는 메서드
                        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "요청 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show()); // UI 스레드에서 Toast 메시지를 통해 실패 메시지 표시
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        // 요청 응답 받았을 때 호출되는 메서드
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        }
                        final String responseData = response.body().string();

                        getActivity().runOnUiThread(() -> {
                            try {
                                JSONArray jsonArray = new JSONArray(responseData);
                                items.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject storeObject = jsonArray.getJSONObject(i);
                                    String tv_name = storeObject.getString("name");
                                    String tv_add = storeObject.getString("addr");
                                    items.add(new SearchVO(tv_name, tv_add,"해시태그","4.9", R.drawable.camera));
                                }
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                Log.e("UserSearchFragment", "JSON 파싱 에러: " + e.getMessage());
                            }
                        });
                    }
                });
            }

        });


        snack_01 = view.findViewById(R.id.snack_01);
        snack_01.setOnClickListener(new View.OnClickListener() { // 분식
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();
                String snackFoodText = "";
                try {
                    snackFoodText = URLEncoder.encode(snack_01.getText().toString(), StandardCharsets.UTF_8.name());

                } catch (UnsupportedEncodingException e) {
                    // 이 경우는 발생하지 않습니다.
                }

                String url = "http://192.168.219.106:5000/snack_food?keyword=분식&keyword2=" + snackFoodText;
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Toast.makeText(getActivity(), snackFoodText, Toast.LENGTH_SHORT).show();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        // 요청 실패 시 호출되는 메서드
                        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "요청 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show()); // UI 스레드에서 Toast 메시지를 통해 실패 메시지 표시
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        // 요청 응답 받았을 때 호출되는 메서드
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        }
                        final String responseData = response.body().string();

                        getActivity().runOnUiThread(() -> {
                            try {
                                JSONArray jsonArray = new JSONArray(responseData);
                                items.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject storeObject = jsonArray.getJSONObject(i);
                                    String tv_name = storeObject.getString("name");
                                    String tv_add = storeObject.getString("addr");
                                    items.add(new SearchVO(tv_name, tv_add,"해시태그","4.9", R.drawable.camera));
                                }
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                Log.e("UserSearchFragment", "JSON 파싱 에러: " + e.getMessage());
                            }
                        });
                    }
                });
            }

        });


        cafe_01 = view.findViewById(R.id.cafe_01);
        cafe_01.setOnClickListener(new View.OnClickListener() { // 카페
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();
                String cafeFoodText = "";
                try {
                    cafeFoodText = URLEncoder.encode(cafe_01.getText().toString(), StandardCharsets.UTF_8.name());

                } catch (UnsupportedEncodingException e) {
                    // 이 경우는 발생하지 않습니다.
                }

                String url = "http://192.168.219.106:5000/cafe_food?keyword=카페&keyword2=" + cafeFoodText;
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Toast.makeText(getActivity(), cafeFoodText, Toast.LENGTH_SHORT).show();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        // 요청 실패 시 호출되는 메서드
                        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "요청 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show()); // UI 스레드에서 Toast 메시지를 통해 실패 메시지 표시
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        // 요청 응답 받았을 때 호출되는 메서드
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        }
                        final String responseData = response.body().string();

                        getActivity().runOnUiThread(() -> {
                            try {
                                JSONArray jsonArray = new JSONArray(responseData);
                                items.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject storeObject = jsonArray.getJSONObject(i);
                                    String tv_name = storeObject.getString("name");
                                    String tv_add = storeObject.getString("addr");
                                    items.add(new SearchVO(tv_name, tv_add,"해시태그","4.9", R.drawable.camera));
                                }
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                Log.e("UserSearchFragment", "JSON 파싱 에러: " + e.getMessage());
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
    private RequestQueue requestQueue;
    String storeName;
    String hashtag;
    String addr;
    String rate;
//    ArrayList<SearchVO> items;

    public void getStore() {
        String url = "http://192.168.219.101:8081/api/getStoreInfo"; // 가게 정보 조회 API 엔드포인트, 실제 엔드포인트로 수정 필요
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Method.GET, url, null,
                response -> {
                    try {
                        // 응답으로 받은 JSON 배열을 순회하면서 가게 정보 처리
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject storeInfo = response.getJSONObject(i);
                            storeName = storeInfo.getString("storeName"); // 가게 이름
                            hashtag = storeInfo.getString("hashtag");
                            addr = storeInfo.getString("addr");
                            rate = storeInfo.getString("rate");
                            Log.d("name", storeName);
                            Log.d("hash", hashtag);
                            Log.d("addr", addr);
                            SearchVO vo1 = new SearchVO(storeName, addr, hashtag,rate, R.drawable.camera);
                            items.add(vo1);
                            Log.d("rate", rate);
                            rv_search.getAdapter().notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("실패", "가게정보 불러오는데 실패");
//                        Toast.makeText(getContext(), "가게 정보를 불러오는 데 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
//                    Toast.makeText(getContext(), "가게 정보를 불러오는 데 실패했습니다: " + error.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("에러", "가게정보 불러오는데 에러" + error.toString());
                });

        requestQueue.add(jsonArrayRequest); // requestQueue는 Volley의 RequestQueue 객체의 참조, 클래스 멤버 변수로 관리되어야 함
    }



}



