package com.example.myapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.example.myapp.R;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_EXPORT = 1;
    public static final String[] PERMISSIONS_NEEDED = {
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions(PERMISSIONS_NEEDED, REQUEST_CODE_EXPORT);
        // init Bmob DB
        Bmob.initialize(this, "82326b7d373f04180abfe46ece1c3de4");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_EXPORT) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                for (int grantResult : grantResults) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        finish();
                        break;
                    }
                }
            }
            else {
                finish();
            }
        }
        else {
            finish();
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
        Intent i = new Intent(c, ExportActivity.class);
        c.startActivity(i);
    }
}
