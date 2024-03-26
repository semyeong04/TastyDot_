package com.project.project3.controller.advertiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    String id;
    String userName;
    String userId;
    String userPw;
    String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnUserLogin);
        etId= findViewById(R.id.etId);
        etPw= findViewById(R.id.etPw);
        creatAc = findViewById(R.id.creatAc);
        // 로그인 버튼 클릭시
        btnLogin.setOnClickListener( v -> {
            loginRequest();
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
                             id = jsonResponse.getString("id");
                             userId = jsonResponse.getString("userId");
                             userName = jsonResponse.getString("userName");
                             userEmail = jsonResponse.getString("userEmail");

                            if (userName != null){
                                Toast.makeText(MainActivity.this,"로그인 완료",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, StandingActivity1.class);
                                // 로그인 성공시
                                intent.putExtra("id", id);
                                intent.putExtra("userId", userId);
                                intent.putExtra("userName", userName);
                                intent.putExtra("userEmail", userEmail);

                                Log.d("MainActivity", "userName: " + userName);

                                startActivity(intent);
                            }else {
                                Toast.makeText(MainActivity.this, "아이디 또는 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Log.d("에러",error.getMessage());
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