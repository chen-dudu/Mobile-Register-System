package com.example.myapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.DB.local.Tag;
import com.example.myapp.R;
import com.example.myapp.Util.TagsViewModelFactory;
import com.example.myapp.ViewModel.TagsViewModel;

import java.util.ArrayList;
import java.util.List;

public class TagsActivity extends AppCompatActivity {

    protected LinearLayoutManager manager;
    protected RecyclerView rview;
    protected TagAdapter adapter;

    private TagsViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);

        viewModel = new ViewModelProvider(this, new TagsViewModelFactory(this.getApplication())).get(TagsViewModel.class);
        viewModel.getAllTags().observe(this, new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
                adapter.setData(tags);
            }
        });

        rview = findViewById(R.id.tags_recyclerview);
        rview.setHasFixedSize(true);

        manager = new LinearLayoutManager(this);
        rview.setLayoutManager(manager);

        adapter = new TagAdapter();
        rview.setAdapter(adapter);

        DividerItemDecoration divider = new DividerItemDecoration(rview.getContext(), manager.getOrientation());
        rview.addItemDecoration(divider);
    }


    public static class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {

        private List<Tag> dataset;

        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView tagName;
            private TextView tagDescription;

            private String name;

            public ViewHolder(View view) {
                super(view);
                tagName = view.findViewById(R.id.tag_name);
                tagDescription = view.findViewById(R.id.tag_description);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Context c = view.getContext();
                Intent i = new Intent(c, TagDetailActivity.class);
                i.putExtra("tagName", this.name);
                c.startActivity(i);
            }

            public TextView getTagName() {
                return tagName;
            }

            public TextView getTagDescription() {
                return tagDescription;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public TagAdapter() {
            this.dataset = new ArrayList<>();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Tag t = dataset.get(position);
            holder.getTagName().setText(t.name);
            holder.setName(t.name);
            String description = t.description;
            if (description == null) {
                description = "无描述";
                System.out.println(">>>>>");
            }
            holder.getTagDescription().setText(description);
        }

        @Override
        public int getItemCount() {
            return dataset.size();
        }

        public void setData(List<Tag> newData) {
            dataset = newData;
            notifyDataSetChanged();
        }
    }
}
