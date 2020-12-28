package com.example.myapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_display);

        Intent i = getIntent();
        String tag = i.getStringExtra("tagName");

        viewModel = new ViewModelProvider(this, new CheckDisplayViewModelFactory(this.getApplication())).get(CheckDisplayViewModel.class);

        if (tag == null) {
            viewModel.getAllRecords().observe(this, new Observer<List<Record>>() {
                @Override
                public void onChanged(List<Record> records) {
                    if (records.size() == 0) {
                        Context c = getApplication();
                        int duration = Toast.LENGTH_SHORT;
                        Toast t = Toast.makeText(c, "未找到结果。", duration);
                        t.show();
                    }
                    else {
                        adapter.setData(records);
                    }
                }
            });
        }
        else {
            viewModel.getRecords(tag).observe(this, new Observer<List<Record>>() {
                @Override
                public void onChanged(List<Record> records) {
                    if (records.size() == 0) {
                        Context c = getApplication();
                        int duration = Toast.LENGTH_SHORT;
                        Toast t = Toast.makeText(c, "未找到结果。", duration);
                        t.show();
                    }
                    else {
                        adapter.setData(records);
                    }
                }
            });
        }

        rview = findViewById(R.id.record_display_recyclerview);
        rview.setHasFixedSize(true);

        manager = new LinearLayoutManager(this);
        rview.setLayoutManager(manager);

        adapter = new DisplayAdapter();
        rview.setAdapter(adapter);

        DividerItemDecoration divider = new DividerItemDecoration(rview.getContext(), manager.getOrientation());
        rview.addItemDecoration(divider);
    }

    public static class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder> {

        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView address;
            private TextView description;
            private TextView tag;

            private int id;

            public ViewHolder(View view) {
                super(view);
                address = view.findViewById(R.id.record_address);
                description = view.findViewById(R.id.record_description);
                tag = view.findViewById(R.id.record_tag);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
//                Context c = v.getContext();
//                Intent i = new Intent(i, );
                System.out.println(">>>>> click");
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
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
            holder.getAddress().setText(address);
            holder.getDescription().setText(r.description);
            holder.getTag().setText(r.tag);
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
