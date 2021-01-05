package com.example.myapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.DB.local.Tag;
import com.example.myapp.R;
import com.example.myapp.Util.NewDescriptionViewModelFactory;
import com.example.myapp.Util.RequestCallBack;
import com.example.myapp.ViewModel.NewDescriptionViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class NewDescriptionActivity extends AppCompatActivity {

    private NewDescriptionViewModel viewModel;
    private EditText editText;
    private TextInputLayout layout;

//    private String tagName;
    private Tag tagToUpdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_description);

        Intent i = getIntent();
        String tagName = i.getStringExtra("tagName");
//        System.out.println(tagName);
        viewModel = new ViewModelProvider(this, new NewDescriptionViewModelFactory(this.getApplication())).get(NewDescriptionViewModel.class);
        viewModel.getTag(tagName).observe(this, new Observer<Tag>() {
            @Override
            public void onChanged(Tag tag) {
                tagToUpdate = tag;
            }
        });

        editText = findViewById(R.id.new_description);
        layout = findViewById(R.id.new_description_layout);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                layout.setError(null);
            }
        });
    }

    public void onClickNewDescriptionConfirmButton(View view) {
        String newDescription = editText.getText().toString();
        if (newDescription.length() == 0) {
            layout.setError("不能将描述更改为空");
        }
        else {
            layout.setError(null);
            viewModel.updateDescription(tagToUpdate, newDescription, new RequestCallBack() {
                @Override
                public void onComplete(boolean isSuccessful) {
                    Toast t = Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT);
                    t.setText("描述修改成功");
                    t.show();
                    finish();
                }
            });
//            finish();
        }
    }

    public void onClickNewDescriptionCancelButton(View view) {
        finish();
    }
}
