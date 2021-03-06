package com.example.myapp.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.DB.local.Record;
import com.example.myapp.R;
import com.example.myapp.Util.CheckDisplayViewModelFactory;
import com.example.myapp.ViewModel.CheckDisplayViewModel;

import java.util.ArrayList;
import java.util.List;

public class CheckDisplayActivity extends AppCompatActivity {

    private CheckDisplayViewModel viewModel;

    protected LinearLayoutManager manager;
    protected RecyclerView rview;
    protected DisplayAdapter adapter;

    private ProgressBar bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        Intent i = getIntent();
        String tag = i.getStringExtra("tagName");

        viewModel = new ViewModelProvider(this, new CheckDisplayViewModelFactory(this.getApplication())).get(CheckDisplayViewModel.class);

        Toast t = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        if (tag == null) {
            viewModel.getAllRecords().observe(this, new Observer<List<Record>>() {
                @Override
                public void onChanged(List<Record> records) {
                    if (records.size() == 0) {
                        t.setText("未找到结果。");
                        t.show();
//                        finish();
                    }
                    else {
                        adapter.setData(records);
                    }
                    bar.setVisibility(View.GONE);
                }
            });
        }
        else {
            viewModel.getRecords(tag).observe(this, new Observer<List<Record>>() {
                @Override
                public void onChanged(List<Record> records) {
                    if (records.size() == 0) {
                        t.setText("未找到结果。");
                        t.show();
//                        finish();
                    }
                    else {
                        adapter.setData(records);
                    }
                    bar.setVisibility(View.GONE);
                }
            });
        }

        rview = findViewById(R.id.recyclerview);
        rview.setHasFixedSize(true);

        manager = new LinearLayoutManager(this);
        rview.setLayoutManager(manager);

        adapter = new DisplayAdapter();
        rview.setAdapter(adapter);

        DividerItemDecoration divider = new DividerItemDecoration(rview.getContext(), manager.getOrientation());
        rview.addItemDecoration(divider);

        bar = findViewById(R.id.progress_bar);
        bar.setVisibility(View.VISIBLE);
    }

    public static class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder> {

        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView address;
            private TextView description;
            private TextView tag;

            private String id;

            public ViewHolder(View view) {
                super(view);
                address = view.findViewById(R.id.record_address);
                description = view.findViewById(R.id.record_description);
                tag = view.findViewById(R.id.record_tag);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                Intent i = new Intent(c, RecordDetailActivity.class);
                i.putExtra("id", this.id);
                c.startActivity(i);
            }

            public TextView getAddress() {
                return address;
            }

            public TextView getDescription() {
                return description;
            }

            public TextView getTag() {
                return tag;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        private List<Record> dataset;

        public DisplayAdapter() {
            dataset = new ArrayList<>();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Record r = dataset.get(position);
            String address = r.province + r.city + r.district + r.road + r.detail;
            TextView addrView = holder.getAddress();
            TextView desView = holder.getDescription();
            TextView tagView = holder.getTag();
            if (r.status.equals("通过")) {
                addrView.setTextColor(Color.rgb(0, 200, 0));
                desView.setTextColor(Color.rgb(0, 200, 0));
                tagView.setTextColor(Color.rgb(0, 200, 0));
            }
            else if (r.status.equals("失败")) {
                addrView.setTextColor(Color.RED);
                desView.setTextColor(Color.RED);
                tagView.setTextColor(Color.RED);
            }
            addrView.setText(address);
            desView.setText(r.description);
            tagView.setText(r.tag);
            holder.setId(r.id);
        }

        @Override
        public int getItemCount() {
            return dataset.size();
        }

        public void setData(List<Record> newData) {
            dataset = newData;
            notifyDataSetChanged();
        }
    }
}
