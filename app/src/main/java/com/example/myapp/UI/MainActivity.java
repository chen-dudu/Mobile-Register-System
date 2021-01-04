package com.example.myapp.UI;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import android.Manifest;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.myapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_EXPORT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "82326b7d373f04180abfe46ece1c3de4");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_EXPORT) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                proceedExport(this);
            }
            else {
                int duration = Toast.LENGTH_SHORT;
                Toast t = Toast.makeText(this, "未获得权限，导出终止", duration);
                t.show();
            }
        }
        else {
            System.out.println(">>> wrong request code");
        }
    }

    public void onClickEnter(View view) {
        Context c = view.getContext();
        Intent i = new Intent(c, EnterActivity.class);
        c.startActivity(i);
    }

    public void onClickCheck(View view) {
        Context c = view.getContext();
        Intent i = new Intent(c, CheckActivity.class);
        c.startActivity(i);
    }

    public void onClickCreateTag(View view) {
        Context c = view.getContext();
        Intent i = new Intent(c, CreateTagActivity.class);
        c.startActivity(i);
    }

    public void onClickTags(View view) {
        Context c = view.getContext();
        Intent i = new Intent(c, TagsActivity.class);
        c.startActivity(i);
    }

    public void onClickExport(View view) {
        Context c = view.getContext();
        if (ContextCompat.checkSelfPermission(c, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            proceedExport(c);
        }
        else {
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_EXPORT);
        }
    }

    private void proceedExport(Context context) {
        Intent i = new Intent(context, ExportActivity.class);
        startActivity(i);
    }
}
