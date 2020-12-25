package com.example.myapp.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.DB.local.Tag;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class TagsActivity extends AppCompatActivity {

    protected LinearLayoutManager manager;
    protected RecyclerView rview;
    protected TagAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);

        // fake data for testing
        List<Tag> d = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            d.add(new Tag("标签 " + i, null));
        }

        rview = findViewById(R.id.tags_recyclerview);
        rview.setHasFixedSize(true);

        manager = new LinearLayoutManager(this);
        rview.setLayoutManager(manager);

        adapter = new TagAdapter(d);
        rview.setAdapter(adapter);

        DividerItemDecoration divider = new DividerItemDecoration(rview.getContext(), manager.getOrientation());
        rview.addItemDecoration(divider);
    }


    public static class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {

        private List<Tag> dataset;

        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView tagName;
            private TextView tagDescription;

            public ViewHolder(View view) {
                super(view);
                tagName = view.findViewById(R.id.tag_name);
                tagDescription = view.findViewById(R.id.tag_description);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                System.out.println(">>>");
            }

            public TextView getTagName() {
                return tagName;
            }

            public TextView getTagDescription() {
                return tagDescription;
            }
        }

        public TagAdapter(List<Tag> dataset) {
            this.dataset = dataset;
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
    }
}
