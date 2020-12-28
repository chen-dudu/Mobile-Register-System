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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.DB.local.Tag;
import com.example.myapp.R;
import com.example.myapp.Util.CheckByTagChooseViewModelFactory;
import com.example.myapp.ViewModel.CheckByTagChooseViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class CheckByTagChooseActivity extends AppCompatActivity {

    private CheckByTagChooseViewModel viewModel;
    private List<String> optionItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_by_tag_choose);

        Button confirm = findViewById(R.id.check_by_tag_choose_confirm_button);
        Button cancel = findViewById(R.id.check_by_tag_choose_cancel_button);

        AutoCompleteTextView chosenTag = findViewById(R.id.check_by_tag_menu);
        TextInputLayout layout = findViewById(R.id.check_by_tag_menu_layout);

        viewModel = new ViewModelProvider(this, new CheckByTagChooseViewModelFactory(this.getApplication())).get(CheckByTagChooseViewModel.class);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.tag_menu_item, optionItems);
        chosenTag.setAdapter(adapter);

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

        chosenTag.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                layout.setError(null);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = chosenTag.getText().toString();
                if (tag.length() == 0) {
                    layout.setError("标签不能为空！");
                }
                else {
                    Context c = v.getContext();
                    Intent i = new Intent(c, CheckDisplayActivity.class);
                    i.putExtra("tagName", tag);
                    startActivity(i);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
