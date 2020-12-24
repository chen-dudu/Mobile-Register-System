package com.example.myapp.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;

import java.util.ArrayList;

public class EnterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);


        // asyn to retrieve all tags from DB when startup
        ArrayList<String> items = new ArrayList<>();
        items.add("标签 1");
        items.add("标签 2");
        items.add("标签 3");
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.tag_menu_item, items);
        AutoCompleteTextView menu = (AutoCompleteTextView) findViewById(R.id.tag_list_drop_down);
        menu.setAdapter(adapter);
    }

    public void onClickCreateButton(View view) {
        // create new record async
        System.out.println("async create record");
    }

    public void onClickBackButton(View view) {
        finish();
    }
}
