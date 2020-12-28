package com.example.myapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.DB.local.Record;
import com.example.myapp.DB.local.Tag;
import com.example.myapp.R;
import com.example.myapp.Util.EnterViewModeFactory;
import com.example.myapp.Util.TagsViewModelFactory;
import com.example.myapp.ViewModel.EnterViewModel;
import com.example.myapp.ViewModel.TagsViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class EnterActivity extends AppCompatActivity {

    private EnterViewModel viewModel;
    private List<String> optionItems = new ArrayList<>();

    private TextInputLayout provinceLayout;
    private EditText provinceText;
    private TextInputLayout cityLayout;
    private EditText cityText;
    private TextInputLayout districtLayout;
    private EditText districtText;
    private TextInputLayout roadLayout;
    private EditText roadText;
    private TextInputLayout detailLayout;
    private EditText detailText;
    private TextInputLayout descriptionLayout;
    private EditText descriptionText;
    private TextInputLayout tagsLayout;
    private AutoCompleteTextView tags;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        viewModel = new ViewModelProvider(this, new EnterViewModeFactory(this.getApplication())).get(EnterViewModel.class);

        provinceLayout = findViewById(R.id.create_record_province_layout);
        provinceText = findViewById(R.id.create_record_province);
        cityLayout = findViewById(R.id.create_record_city_layout);
        cityText = findViewById(R.id.create_record_city);
        districtLayout = findViewById(R.id.create_record_district_layout);
        districtText = findViewById(R.id.create_record_district);
        roadLayout = findViewById(R.id.create_record_road_layout);
        roadText = findViewById(R.id.create_record_road);
        detailLayout = findViewById(R.id.create_record_detail_layout);
        detailText = findViewById(R.id.create_record_detail);
        descriptionLayout = findViewById(R.id.create_record_description_layout);
        descriptionText = findViewById(R.id.create_record_description);
        tagsLayout = findViewById(R.id.tags_layout);
        tags = findViewById(R.id.tag_list_drop_down);

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

        // clear error msg upon user start typing
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
    }

    public void onClickCreateButton(View view) {
        // create new record async
        String province = provinceText.getText().toString();
        String city = cityText.getText().toString();
        String district = districtText.getText().toString();
        String road = roadText.getText().toString();
        String detail = detailText.getText().toString();
        String description = descriptionText.getText().toString();
        String tag = tags.getText().toString();

        boolean checkPass = true;

        // set error msg for empty input
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
            tagsLayout.setError("标签不能为空!");
            checkPass = false;
        }

        if (checkPass) {
            System.out.println("checking passed");
            viewModel.addRecord(new Record(province, city, district, road, detail, description, tag));
            Context c = view.getContext();
            Intent i = new Intent(c, MainActivity.class);
            startActivity(i);
        }
        else {
            System.out.println(">>> checking fails");
//            assert false;
        }
    }

    public void onClickBackButton(View view) {
        finish();
    }
}
