package com.example.myapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.DB.local.Record;
import com.example.myapp.DB.local.Tag;
import com.example.myapp.R;
import com.example.myapp.Util.RecordUpdateViewModelFactory;
import com.example.myapp.ViewModel.RecordUpdateViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class RecordUpdateActivity extends AppCompatActivity {

    private RecordUpdateViewModel viewModel;
    private List<String> optionItem = new ArrayList<>();

    private Record r = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_update);

        Intent i = getIntent();
        int id = i.getIntExtra("id", -1);

        viewModel = new ViewModelProvider(this, new RecordUpdateViewModelFactory(this.getApplication())).get(RecordUpdateViewModel.class);

        TextInputLayout provinceLayout = findViewById(R.id.update_record_province_layout);
        EditText provinceText = findViewById(R.id.update_record_province);
        TextInputLayout cityLayout = findViewById(R.id.update_record_city_layout);
        EditText cityText = findViewById(R.id.update_record_city);
        TextInputLayout districtLayout = findViewById(R.id.update_record_district_layout);
        EditText districtText = findViewById(R.id.update_record_district);
        TextInputLayout roadLayout = findViewById(R.id.update_record_road_layout);
        EditText roadText = findViewById(R.id.update_record_road);
        TextInputLayout detailLayout = findViewById(R.id.update_record_detail_layout);
        EditText detailText = findViewById(R.id.update_record_detail);
        TextInputLayout descriptionLayout = findViewById(R.id.update_record_description_layout);
        EditText descriptionText = findViewById(R.id.update_record_description);
        TextInputLayout tagLayout = findViewById(R.id.update_record_tags_layout);
        AutoCompleteTextView tagText = findViewById(R.id.update_record_tag_list_drop_down);
        Button confirm = findViewById(R.id.update_record_confirm_button);
        Button back = findViewById(R.id.update_record_back_button);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.tag_menu_item, optionItem);
        tagText.setAdapter(adapter);

        viewModel.getRecord(id).observe(this, new Observer<Record>() {
            @Override
            public void onChanged(Record record) {
                provinceText.setText(record.province);
                cityText.setText(record.city);
                districtText.setText(record.district);
                roadText.setText(record.road);
                detailText.setText(record.detail);
                descriptionText.setText(record.description);
                tagText.setText(record.tag, false);
                r = record;
            }
        });

        viewModel.getAllTags().observe(this, new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
                optionItem.clear();
                for (int i = 0; i < tags.size(); i++) {
                    System.out.println(">>>> " + tags.get(i).name);
                    optionItem.add(tags.get(i).name);
                }
                adapter.notifyDataSetChanged();
            }
        });

        provinceText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                provinceLayout.setError(null);
            }
        });
        cityText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                cityLayout.setError(null);
            }
        });
        districtText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                districtLayout.setError(null);
            }
        });
        roadText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                roadLayout.setError(null);
            }
        });
        detailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                detailLayout.setError(null);
            }
        });
        descriptionText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                descriptionLayout.setError(null);
            }
        });
        tagText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                tagLayout.setError(null);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String province = provinceText.getText().toString();
                String city = cityText.getText().toString();
                String district = districtText.getText().toString();
                String road = roadText.getText().toString();
                String detail = detailText.getText().toString();
                String description = descriptionText.getText().toString();
                String tag = tagText.getText().toString();

                boolean checkPass = true;

                if (province.length() == 0) {
                    provinceLayout.setError("省份不能为空！");
                    checkPass = false;
                }
                if (city.length() == 0) {
                    cityLayout.setError("城市不能为空！");
                    checkPass = false;
                }
                if (district.length() == 0) {
                    districtLayout.setError("城区不能为空！");
                    checkPass = false;
                }
                if (road.length() == 0) {
                    roadLayout.setError("道路不能为空！");
                    checkPass = false;
                }
                if (detail.length() == 0) {
                    detailLayout.setError("具体位置不能为空！");
                    checkPass = false;
                }
                if (description.length() == 0) {
                    descriptionLayout.setError("描述不能为空！");
                    checkPass = false;
                }
                if (tag.length() == 0) {
                    tagLayout.setError("标签不能为空!");
                    checkPass = false;
                }

                if (checkPass) {
                    r.province = province;
                    r.city = city;
                    r.district = district;
                    r.road = road;
                    r.detail = detail;
                    r.description = description;
                    r.tag = tag;

                    viewModel.update(r);

                    Context c = v.getContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast t = Toast.makeText(c, "记录修改成功", duration);
                    t.show();
                    finish();
                }
                else {
                    System.out.println(">>> check failed");
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
}
