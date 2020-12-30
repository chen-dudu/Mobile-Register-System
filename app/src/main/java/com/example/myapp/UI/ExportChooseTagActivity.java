package com.example.myapp.UI;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.DB.local.Record;
import com.example.myapp.DB.local.Tag;
import com.example.myapp.R;
import com.example.myapp.Util.ExportChooseTagViewModelFactory;
import com.example.myapp.ViewModel.ExportChooseTagViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportChooseTagActivity extends AppCompatActivity {

    private ExportChooseTagViewModel viewModel;
    private List<String> optionItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_choose_tag);

        viewModel = new ViewModelProvider(this, new ExportChooseTagViewModelFactory(this.getApplication())).get(ExportChooseTagViewModel.class);

        Button export = findViewById(R.id.export_choose_confirm_button);
        Button back = findViewById(R.id.export_choose_cancel_button);

        TextInputLayout tagsLayout = findViewById(R.id.export_menu_layout);
        AutoCompleteTextView tags = findViewById(R.id.export_menu);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.tag_menu_item, optionItems);
        tags.setAdapter(adapter);

        viewModel.getAllTags().observe(this, new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
                optionItems.clear();
                for (int i = 0; i < tags.size(); i++) {
                    optionItems.add(tags.get(i).name);
                }
                adapter.notifyDataSetChanged();
            }
        });

        tags.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                tagsLayout.setError(null);
            }
        });

        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = tags.getText().toString();
                if (tag.length() == 0) {
                    tagsLayout.setError("标签不能为空!");
                }
                else {
                    viewModel.getRecordsByTag(tag).observe(ExportChooseTagActivity.this, new Observer<List<Record>>() {
                        @Override
                        public void onChanged(List<Record> records) {
                            exportRecords(records);
                        }
                    });
                }
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
        String fileName = "records_by_tag";
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
}
