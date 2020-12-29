package com.example.myapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.DB.local.Tag;
import com.example.myapp.R;
import com.example.myapp.Util.CreateTagViewModelFactory;
import com.example.myapp.ViewModel.CreateTagViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class CreateTagActivity extends AppCompatActivity {

    private CreateTagViewModel viewModel;

    private EditText tagName;
    private TextInputLayout nameLayout;
    private EditText tagDes;
    private TextInputLayout desLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tag);

        viewModel = new ViewModelProvider(this, new CreateTagViewModelFactory(this.getApplication())).get(CreateTagViewModel.class);

        tagName = findViewById(R.id.create_tag_name);
        nameLayout = findViewById(R.id.create_tag_name_layout);
        tagDes = findViewById(R.id.create_tag_description);
        desLayout = findViewById(R.id.create_tag_description_layout);
        tagName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                nameLayout.setError(null);
            }
        });
    }

    public void onClickConfirmButton(View view) {
        String name = tagName.getText().toString();
        if (name.length() == 0) {
            nameLayout.setError("标签名字不能为空！");
        }
        else {
            String des = tagDes.getText().toString();
            if (des.length() == 0) {
                des = null;
            }
            Tag newTag = new Tag(name, des);
            viewModel.addTag(newTag);
            Context c = view.getContext();
            int duration = Toast.LENGTH_SHORT;
            Toast t = Toast.makeText(c, "标签创建成功", duration);
            t.show();
            Intent i = new Intent(c, MainActivity.class);
            startActivity(i);
        }
    }

    public void onClickBackButton(View view) {
        finish();
    }
}
