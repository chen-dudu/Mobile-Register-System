package com.example.myapp.UI;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.google.android.material.button.MaterialButton;

public class CreateTagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tag);

//        MaterialButton button =

    }

    public void onClickConfirmButton(View view) {
        System.out.println("");
    }

    public void onClickBackButton(View view) {
        finish();
    }
}
