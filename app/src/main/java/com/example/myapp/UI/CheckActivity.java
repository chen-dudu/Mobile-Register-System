package com.example.myapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;

public class CheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        Button all = findViewById(R.id.check_record_all_button);
        Button allGraph = findViewById(R.id.check_record_all_graph_button);
        Button tag = findViewById(R.id.check_record_by_tag_button);
        Button tagGraph = findViewById(R.id.check_record_by_tag_graph_button);
        Button back = findViewById(R.id.check_record_back_button);

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                Intent i = new Intent(c, CheckDisplayActivity.class);
                startActivity(i);
            }
        });

        allGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                Intent i = new Intent(c, CheckDisplayGraphActivity.class);
                startActivity(i);
            }
        });

        tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                Intent i = new Intent(c, CheckByTagChooseActivity.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
