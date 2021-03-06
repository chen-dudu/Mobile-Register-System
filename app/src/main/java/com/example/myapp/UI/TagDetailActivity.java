package com.example.myapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.DB.local.Tag;
import com.example.myapp.R;
import com.example.myapp.Util.RequestCallBack;
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
        LinearLayout content = findViewById(R.id.tag_detail_content);
        ProgressBar bar = findViewById(R.id.progress_bar);

        content.setVisibility(View.GONE);
        bar.setVisibility(View.VISIBLE);

        viewModel = new ViewModelProvider(this, new TagDetailViewModelFactory(this.getApplication())).get(TagDetailViewModel.class);
        viewModel.getTag(selectedTag).observe(this, new Observer<Tag>() {
            @Override
            public void onChanged(Tag tag) {
                if (tag != null) {
                    t = tag;
                    name.setText(tag.name);
                    if (tag.description == null) {
                        description.setText("无描述");
                    }
                    else {
                        description.setText(tag.description);
                    }
                    content.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
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
        viewModel.deleteTag(t.id, new RequestCallBack() {
            @Override
            public void onComplete(boolean isSuccessful) {
                Context c = view.getContext();
                Toast t = Toast.makeText(c, "", Toast.LENGTH_SHORT);
                if (isSuccessful) {
                    t.setText("标签删除成功");
                    t.show();
                    Intent i = new Intent(c, MainActivity.class);
                    c.startActivity(i);
                }
                else {
                    t.setText("标签删除失败");
                    t.show();
                }
            }
        });
    }

    public void onClickTagDetailHome(View view) {
//        Context c = view.getContext();
//        Intent i = new Intent(c, MainActivity.class);
//        startActivity(i);
        finish();
    }
}
