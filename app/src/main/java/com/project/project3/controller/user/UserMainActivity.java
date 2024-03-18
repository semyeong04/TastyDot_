package com.project.project3.controller.user;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.project.project3.R;

public class UserMainActivity extends AppCompatActivity {

    private EditText search_text;
    private ImageView search_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        // EditText와 ImageView를 레이아웃에서 가져옴
        search_text = findViewById(R.id.search_text);
        search_img = findViewById(R.id.search_img);

        // EditText에 포커스를 받았을 때 imageView19를 숨김
        search_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    search_img.setVisibility(View.INVISIBLE);
                } else {
                    search_img.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
