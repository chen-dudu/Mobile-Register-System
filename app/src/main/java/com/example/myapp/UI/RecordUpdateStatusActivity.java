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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.DB.local.Record;
import com.example.myapp.R;
import com.example.myapp.Util.RecordUpdateStatusViewModelFactory;
import com.example.myapp.Util.RequestCallBack;
import com.example.myapp.ViewModel.RecordUpdateStatusViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecordUpdateStatusActivity extends AppCompatActivity {

    private RecordUpdateStatusViewModel viewModel;

    private List<String> optionItems = new ArrayList<>();

    private TextInputLayout newStatusLayout;
    private AutoCompleteTextView newStatus;
    private TextInputLayout reasonLayout;
    private TextInputEditText reason;
    private Button back;
    private Button confirm;
    private LinearLayout content;
    private ProgressBar bar;

    private Record  r = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_update_status);

        Intent i = getIntent();
        String id = i.getStringExtra("id");

        viewModel = new ViewModelProvider(this, new RecordUpdateStatusViewModelFactory(this.getApplication())).get(RecordUpdateStatusViewModel.class);
        viewModel.getRecord(id).observe(this, new Observer<Record>() {
            @Override
            public void onChanged(Record record) {
                r = record;
                content.setVisibility(View.VISIBLE);
                bar.setVisibility(View.GONE);
            }
        });

        newStatusLayout = findViewById(R.id.record_update_status_new_layout);
        newStatus = findViewById(R.id.record_update_status_new);
        reasonLayout = findViewById(R.id.record_update_status_reason_layout);
        reason = findViewById(R.id.record_update_status_reason);
        back = findViewById(R.id.record_update_status_back);
        confirm = findViewById(R.id.record_update_status_confirm);
        content = findViewById(R.id.record_update_status_content);
        bar = findViewById(R.id.progress_bar);

        content.setVisibility(View.GONE);
        bar.setVisibility(View.VISIBLE);

        List<String> optionItems = Arrays.asList("等待审核", "通过", "失败");
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.tag_menu_item, optionItems);
        newStatus.setAdapter(adapter);

        // clear error msg upon user start typing
        newStatus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                newStatusLayout.setError(null);
            }
        });
        reason.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                reasonLayout.setError(null);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = newStatus.getText().toString();
                String note = reason.getText().toString();

                boolean checkPass = true;

                if (status.length() == 0) {
                    newStatusLayout.setError("状态不能为空！");
                    checkPass = false;
                }
                if (note.length() == 0) {
                    reasonLayout.setError("理由不能为空！");
                    checkPass = false;
                }

                if (checkPass) {
                    // update data in DB
                    r.status = status;
                    r.note = note;
                    viewModel.update(r, new RequestCallBack() {
                        @Override
                        public void onComplete(boolean isSuccessful) {
                            Context c = v.getContext();
                            Toast t = Toast.makeText(c, "", Toast.LENGTH_SHORT);
                            if (isSuccessful) {
                                t.setText("状态更改成功");
                                finish();
                            }
                            else {
                                t.setText("状态更改失败");
                            }
                            t.show();
                        }
                    });
                }
                else {
                    System.out.println("check failed");
                }
            }
        });

    }
}
