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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.project3.R;

import java.io.FileNotFoundException;
import java.io.IOException;


public class MenuModActivity extends AppCompatActivity {

    String[] items = {"카리나", "윈터", "닝닝", "지젤"};
    Intent intent;
    ImageView imgMenu;
    ActivityResultLauncher<Intent> startActivityResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_mod);

        Spinner spinner = findViewById(R.id.spNowMenu);
        // ArrayAdapter를 사용하여 스피너와 아이템을 연결합니다.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
        // 스피너에서 아이템을 선택했을 때 동작하는 리스너를 추가합니다.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Spinner는 스피너 아이템을 자동으로 선택한 항목으로 표시합니다.
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // 아무 것도 선택되지 않았을 때의 동작을 정의할 수 있습니다.
            }
        });


        EditText menuName = findViewById(R.id.etModMenu);
        EditText menuPrice = findViewById(R.id.etModPrice);
        EditText menuExplain = findViewById(R.id.etModExplain);
        Button btnModPic = findViewById(R.id.btnPicMod);
        Button btnMod = findViewById(R.id.btnModMenu);
        imgMenu = findViewById(R.id.imgModMenu);

        ImageView imgInfo = findViewById(R.id.imgInfo4);
        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MenuModActivity.this, AdvInfoActivity.class);
                startActivity(intent);
            }
        });
        ImageView imgHome = findViewById(R.id.imgHome4);
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MenuModActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });

        btnModPic.setOnClickListener(new View.OnClickListener() {
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

        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });




    }
    void showDialog() {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(MenuModActivity.this)
                .setTitle("메뉴 수정")
                .setMessage("메뉴를 수정하시겠습니까?")
                .setPositiveButton("네", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MenuModActivity.this, "메뉴가 수정됐습니다.", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(MenuModActivity.this, UserActivity.class);
//                        startActivity(intent);
                        finish();
                    }
                })

                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MenuModActivity.this, "메뉴를 수정하지 않았습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
}