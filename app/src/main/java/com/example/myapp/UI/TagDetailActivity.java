package com.example.myapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.DB.local.Tag;
import com.example.myapp.R;
import com.example.myapp.Util.TagDetailViewModelFactory;
import com.example.myapp.ViewModel.TagDetailViewModel;

public class TagDetailActivity extends AppCompatActivity {

    private TagDetailViewModel viewModel;
    private Tag t = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_detail);

        Intent i = getIntent();
        String selectedTag = i.getStringExtra("tagName");

        TextView name = findViewById(R.id.tag_detail_name);
        TextView description = findViewById(R.id.tag_detail_description);

        viewModel = new ViewModelProvider(this, new TagDetailViewModelFactory(this.getApplication())).get(TagDetailViewModel.class);
        viewModel.getTag(selectedTag).observe(this, new Observer<Tag>() {
            @Override
            public void onChanged(Tag tag) {
                if (tag != null) {
                    t = tag;
                    name.setText(tag.name);
                    description.setText(tag.description);
                }
            }
        });
    }

    public void onClickTagDetailUpdate(View view) {
        Context c = view.getContext();
        Intent i = new Intent(c, NewDescriptionActivity.class);
        i.putExtra("tagName", t.name);
        startActivity(i);
    }

    public void onClickTagDetailDelete(View view) {
        viewModel.deleteTag(t);
        Context c = view.getContext();
        Intent i = new Intent(c, MainActivity.class);
        startActivity(i);
    }

    public void onClickTagDetailHome(View view) {
        Context c = view.getContext();
        Intent i = new Intent(c, MainActivity.class);
        startActivity(i);
    }
}
