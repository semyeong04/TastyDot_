package com.project.project3.controller.advertiser;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.project.project3.R;


import java.io.FileNotFoundException;
import java.io.IOException;


public class MenuRegiActivity extends AppCompatActivity {

    Intent intent;

    ImageView imgMenu;
    ActivityResultLauncher<Intent> startActivityResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_regi);
        imgMenu = findViewById(R.id.imgMenu);

        EditText etMenuName = findViewById(R.id.etMenuName);
        etMenuName.setOnClickListener(v -> {
            String menu = etMenuName.getText().toString();
        });
        EditText price4 = findViewById(R.id.etPrice4);

        Button btnPicAdd = findViewById(R.id.btnPicAdd);
        btnPicAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityResult.launch(intent);
            }
        });

        startActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {

            // 원하는 너비와 높이 설정
            int newWidth = 300; // 예시로 300 픽셀로 설정
            int newHeight = 300; // 예시로 300 픽셀로 설정

            @Override
            public void onActivityResult(ActivityResult result) {
                if ( result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    try {
                        // 갤러리에서 이미지를 비트맵으로 변환
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        // 이미지를 원하는 크기로 조절
                        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);


                        // 압축된 이미지를 ImageView에 표시
                        imgMenu.setImageBitmap(resizedBitmap);

                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        Button btnAddMenu = findViewById(R.id.btnAddMenu);
        btnAddMenu.setOnClickListener(v -> {
            showDialog();
        });

        ImageView imgInfo = findViewById(R.id.imginfo3);
        imgInfo.setOnClickListener(v -> {
            intent = new Intent(this, AdvInfoActivity.class);
            startActivity(intent);
        });

        ImageView imgMenu = findViewById(R.id.imghome3);
        imgMenu.setOnClickListener(v -> {
            intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        });

    }

    void showDialog() {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(MenuRegiActivity.this)
                .setTitle("메뉴 등록")
                .setMessage("메뉴를 등록하시겠습니까?")
                .setPositiveButton("네", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MenuRegiActivity.this, "메뉴가 등록됐습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MenuRegiActivity.this, UserActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                
                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MenuRegiActivity.this, "메뉴를 등록하지 않았습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
}
