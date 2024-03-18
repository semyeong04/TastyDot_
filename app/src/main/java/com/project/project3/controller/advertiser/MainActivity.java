package com.project.project3.controller.advertiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.project.project3.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    EditText etId;
    EditText etPw;
    TextView creatAc;
    static RequestQueue requestQueue;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);
        etId= findViewById(R.id.etId);
        etPw= findViewById(R.id.etPw);
        creatAc = findViewById(R.id.creatAc);

        // 로그인 버튼 클릭시
        btnLogin.setOnClickListener( v -> {
            String id = etId.getText().toString();
            String pw = etPw.getText().toString();

            loginRequest();

            if (userName != null){
                Toast.makeText(this,"로그인 완료",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, UserActivity.class);
                intent.putExtra("userName", userName);
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

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

    }

    public void loginRequest(){
        String url = "http://192.168.219.101:8081/api/login";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String userId = jsonResponse.getString("userId");
                            String userPw = jsonResponse.getString("userPw");
                             userName = jsonResponse.getString("userName");
                            String userEmail = jsonResponse.getString("userEmail");
                            // 파싱한 데이터를 활용하여 필요한 작업을 수행합니다.
                            // 예: TextView에 출력

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("에러",error.getMessage());

                    }
                }

        ) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("userId", etId.getText().toString());
                    jsonBody.put("userPw", etPw.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return jsonBody.toString().getBytes();
            }

            public Map<String, String> getHeaders() throws AuthFailureError {
                // 요청 헤더 설정
                Map<String, String> headers = super.getHeaders();
                if (headers == null || headers.isEmpty()) {
                    headers = new HashMap<>();
                }
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);

    }



}