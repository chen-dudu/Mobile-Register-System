package com.example.myapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickEnter(View view) {
        Context c = view.getContext();
        Intent i = new Intent(c, EnterActivity.class);
        c.startActivity(i);
    }

    public void onClickCheck(View view) {
        Context c = view.getContext();
        Intent i = new Intent(c, CheckActivity.class);
        c.startActivity(i);
    }

    public void onClickCreateTag(View view) {
        Context c = view.getContext();
        Intent i = new Intent(c, CreateTagActivity.class);
        c.startActivity(i);
    }

    public void onClickTags(View view) {
        Context c = view.getContext();
        Intent i = new Intent(c, TagsActivity.class);
        c.startActivity(i);
    }
}