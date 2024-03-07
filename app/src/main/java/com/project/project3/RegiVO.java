package com.project.project3;


import android.widget.Button;
import android.widget.EditText;

public class RegiVO {
    private Button button;
    private EditText etSize;
    private EditText etPrice;

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public EditText getEtSize() {
        return etSize;
    }

    public void setEtSize(EditText etSize) {
        this.etSize = etSize;
    }

    public EditText getEtPrice() {
        return etPrice;
    }

    public void setEtPrice(EditText etPrice) {
        this.etPrice = etPrice;
    }

    public RegiVO(Button button, EditText etSize, EditText etPrice) {
        this.button = button;
        this.etSize = etSize;
        this.etPrice = etPrice;
    }
}
