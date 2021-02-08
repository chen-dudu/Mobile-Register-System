package com.example.myapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.DB.local.Record;
import com.example.myapp.R;
import com.example.myapp.Util.RecordDetailViewModelFactory;
import com.example.myapp.Util.RequestCallBack;
import com.example.myapp.ViewModel.RecordDetailViewModel;

public class RecordDetailActivity extends AppCompatActivity {

    private RecordDetailViewModel viewModel;

    private Record r = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);

        Intent i = getIntent();
        String id = i.getStringExtra("id");

        viewModel = new ViewModelProvider(this, new RecordDetailViewModelFactory(this.getApplication())).get(RecordDetailViewModel.class);

        Button update = findViewById(R.id.record_detail_button_update_info);
        Button updateStatus = findViewById(R.id.record_detail_button_update_status);
        Button delete = findViewById(R.id.record_detail_button_delete);
        Button back = findViewById(R.id.record_detail_button_home);
        TextView address = findViewById(R.id.record_detail_address);
        TextView des = findViewById(R.id.record_detail_description);
        TextView status = findViewById(R.id.record_detail_status);
        TextView note = findViewById(R.id.record_detail_note);

        viewModel.getRecord(id).observe(this, new Observer<Record>() {
            @Override
            public void onChanged(Record record) {
                if (record != null) {
                    String addr = record.province + record.city + record.district + record.road + record.detail;
                    address.setText("地址：" + addr);
                    des.setText("描述：" + record.description);
                    status.setText("状态：" + record.status);
                    if (record.note.length() > 0) {
                        note.setText("审核说明：" + record.note);
                    }
                    else {
                        note.setText("审核说明：无审核说明");
                    }
                    r = record;
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                Intent i = new Intent(c, RecordUpdateActivity.class);
                i.putExtra("id", r.id);
                startActivity(i);
            }
        });

        updateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                Intent i = new Intent(c, RecordUpdateStatusActivity.class);
                i.putExtra("id", r.id);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.delete(r.id, new RequestCallBack() {
                    @Override
                    public void onComplete(boolean isSuccessful) {
                        Context c = v.getContext();
                        Toast t = Toast.makeText(c, "", Toast.LENGTH_SHORT);
                        if (isSuccessful) {
                            t.setText("记录删除成功");
                            t.show();
                            Intent i = new Intent(c, MainActivity.class);
                            startActivity(i);
                        }
                        else {
                            t.setText("记录删除失败");
                            t.show();
                        }
                    }
                });
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
