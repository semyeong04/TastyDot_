package com.project.project3.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.project.project3.R;
import com.project.project3.model.RegiVO;

import java.util.ArrayList;

public class MenuRegiActivity extends AppCompatActivity {

    private Button[] buttons;
    private EditText[] etSizes;
    private  EditText[] etPrices;
    private ArrayList<RegiVO> dataset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_regi);
    }
}