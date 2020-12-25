package com.example.myapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.DB.local.Tag;
import com.example.myapp.R;

public class TagDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_detail);

        Intent i = getIntent();
        // TODO: use tag name primary key to retrieve tag from DB
        String selectedTag = i.getStringExtra("tagName");

        // fake object, to be replaced with DB data
        // TODO: retrieve data from DB
        Tag t = new Tag("测试标签", "测试描述");

        TextView name = findViewById(R.id.tag_detail_name);
        TextView description = findViewById(R.id.tag_detail_description);

        name.setText(selectedTag);
        description.setText(t.description);
    }

    public void onClickTagDetailUpdate(View view) {
        // TODO: connected to DB
        System.out.println(">>>  update description");
    }

    public void onClickTagDetailDelete(View view) {
        // TODO: connected to DB
        System.out.println(">>>> delete tag");
    }

    public void onClickTagDetailHome(View view) {
        Context c = view.getContext();
        Intent i = new Intent(c, MainActivity.class);
        startActivity(i);
    }

}
