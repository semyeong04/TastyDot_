package com.project.project3.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import com.project.project3.R;

public class RegiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Button button;
    private EditText etSize;
    private  EditText etPrice;

    ItemClickListener listener;

   public RegiViewHolder(View price){
       super(price);
       // 변수 초기화
       this.button = price.findViewById(R.id.btnDel);
       this.etSize = price.findViewById(R.id.etSize);
       this.etPrice = price.findViewById(R.id.etPrice);

       // 버튼에 클릭이벤트 연결
       this.button.setOnClickListener(this);

   }

    public Button getButton() {
        return button;
    }

    public EditText getEtSize() {
        return etSize;
    }

    public EditText getEtPrice() {
        return etPrice;
    }


    @Override
    public void onClick(View v) {
        this.listener.onItemClick(v,getAdapterPosition());
    }
}
