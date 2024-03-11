package com.project.project3.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.project3.R;
import com.project.project3.model.RegiVO;

import java.util.ArrayList;

public class MenuRegiActivity extends AppCompatActivity {


    Button btnPicAdd = findViewById(R.id.btnPicAdd);
    ImageView imgMenu = findViewById(R.id.imgMenu);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_regi);

        // 메뉴 이름
        EditText etMenuName = findViewById(R.id.etMenuName);
        etMenuName.setOnClickListener(v -> {
            String menu = etMenuName.getText().toString();
        });

        // 상세 메뉴 체크박스, 이름, 가격
        CheckBox chbox1 = findViewById(R.id.chbox1);
        CheckBox chbox2 = findViewById(R.id.chBox2);
        CheckBox chbox3 = findViewById(R.id.chBox3);
        CheckBox chbox4 = findViewById(R.id.chBox4);
        EditText menu1 = findViewById(R.id.etMenu1);
        EditText menu2 = findViewById(R.id.etMenu2);
        EditText menu3 = findViewById(R.id.etMenu3);
        EditText menu4 = findViewById(R.id.etMenu4);
        EditText price1 = findViewById(R.id.etPrice1);
        EditText price2 = findViewById(R.id.etPrice2);
        EditText price3 = findViewById(R.id.etPrice3);
        EditText price4 = findViewById(R.id.etPrice4);


        btnPicAdd.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
            intent.setAction(Intent.ACTION_PICK);
            startActivity(intent);
        });




        Button btnAddMenu = findViewById(R.id.btnAddMenu);
        btnAddMenu.setOnClickListener(v -> {

            // 유저액티비티로 이동
            Toast.makeText(this, "메뉴가 등록됐습니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case 1 :
                if(requestCode==RESULT_OK){
                    Uri uri = data.getData();
                    imgMenu.setImageURI(uri);
                }
                break;
        }
    }
}