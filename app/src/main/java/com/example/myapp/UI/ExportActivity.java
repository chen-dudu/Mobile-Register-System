package com.example.myapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.DB.local.Record;
import com.example.myapp.DB.local.Tag;
import com.example.myapp.R;
import com.example.myapp.Util.ExportViewModelFactory;
import com.example.myapp.ViewModel.ExportViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportActivity extends AppCompatActivity {

    private ExportViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);

        viewModel = new ViewModelProvider(this, new ExportViewModelFactory(this.getApplication())).get(ExportViewModel.class);

        Button allRecords = findViewById(R.id.export_all_records_button);
        Button allTags = findViewById(R.id.export_all_tags_button);
        Button recordByTag = findViewById(R.id.export_record_by_tag_button);
        Button back = findViewById(R.id.export_back_button);

        allRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getAllRecords().observe(ExportActivity.this, new Observer<List<Record>>() {
                    @Override
                    public void onChanged(List<Record> records) {
                        exportRecords(records);
                    }
                });
            }
        });

        allTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getAllTags().observe(ExportActivity.this, new Observer<List<Tag>>() {
                    @Override
                    public void onChanged(List<Tag> tags) {
                        exportTags(tags);
                    }
                });
            }
        });

        recordByTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                Intent i = new Intent(c, ExportChooseTagActivity.class);
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

    private void exportRecords(List<Record> records) {
        String fileName = "all_records";
        String fileExtension = ".csv";
        File file = new File(getExternalFilesDir(null), fileName + fileExtension);
        int count = 1;
        while (file.exists()) {
            String newName = fileName +  " (" + count + ")";
            file = new File(getExternalFilesDir(null), newName + fileExtension);
            count++;
        }

        try {
            FileOutputStream out = new FileOutputStream(file);
            String temp = "地址,描述,标签\n";
            out.write(temp.getBytes());
            for (int i = 0; i < records.size(); i++) {
                Record r = records.get(i);
                temp = r.province + r.city + r.district + r.road + r.detail + "," + r.description + "," + r.tag + "\n";
                out.write(temp.getBytes());
            }
            out.close();
            int duration = Toast.LENGTH_SHORT;
            Toast t = Toast.makeText(this, "成功导出数据", duration);
            t.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportTags(List<Tag> tags) {
        String fileName = "all_tags";
        String fileExtension = ".csv";
        File file = new File(getExternalFilesDir(null), fileName + fileExtension);
        int count = 1;
        while (file.exists()) {
            String newName = fileName +  " (" + count + ")";
            file = new File(getExternalFilesDir(null), newName + fileExtension);
            count++;
        }

        try {
            FileOutputStream out = new FileOutputStream(file);
            String temp = "标签名称,标签描述\n";
            out.write(temp.getBytes());
            for (int i = 0; i < tags.size(); i++) {
                Tag t = tags.get(i);
                temp = t.name + "," + t.description + "\n";
                out.write(temp.getBytes());
            }
            out.close();
            int duration = Toast.LENGTH_SHORT;
            Toast t = Toast.makeText(this, "成功导出数据", duration);
            t.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
