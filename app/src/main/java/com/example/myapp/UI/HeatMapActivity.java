package com.example.myapp.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.HeatmapTileProvider;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.WeightedLatLng;
import com.example.myapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import cn.bmob.v3.util.V;

public class HeatMapActivity extends AppCompatActivity {

    private MapView view;
    private AMap map;

    // locations for the initial default position
    private final double LAT = 21.473343;
    private final double LNG = 109.119254;

    private ProgressBar bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_view);

        view = findViewById(R.id.map);
        view.onCreate(savedInstanceState);

        bar = findViewById(R.id.progress_bar);
        bar.setVisibility(View.GONE);

        if (map == null) {
            map = view.getMap();
        }
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(LAT, LNG), 13));

        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        // this line is to fix a known bug, for more information, see:
        // https://stackoverflow.com/questions/21045091/no-activity-found-to-handle-intent-android-intent-action-open-document
        i.setType("*/*");
//        startActivity(i);
        startActivityForResult(i, 1);

        Toast t = Toast.makeText(this, "", Toast.LENGTH_LONG);
        t.setText("请选择数据文件");
        t.setGravity(Gravity.CENTER, 0, 0);
        // the following lines of code is to change the text size of a toast message
        ViewGroup group = (ViewGroup) t.getView();
        TextView text = (TextView) group.getChildAt(0);
        text.setTextSize(20);
        t.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null) {
            Toast t = Toast.makeText(this, "", Toast.LENGTH_SHORT);
            t.setText("读取数据失败");
            t.setGravity(Gravity.CENTER, 0, 0);
            ViewGroup group = (ViewGroup) t.getView();
            TextView text = (TextView) group.getChildAt(0);
            text.setTextSize(20);
            t.show();
        }
        else {
            Uri uri = data.getData();
            try {
                InputStream inp = getContentResolver().openInputStream(uri);
                BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inp)));
                String line = reader.readLine();
                List<WeightedLatLng> dataset = new ArrayList<>();

                while ((line = reader.readLine()) != null) {
                    String[] temp = line.split(",");
                    double lat = Double.parseDouble(temp[1]);
                    double lng = Double.parseDouble(temp[0]);
                    double weight;
                    if (temp.length == 2) {
                        weight = 1;
                    }
                    else {
                        weight = Double.parseDouble(temp[2]);
                    }
                    System.out.println(Arrays.toString(temp));
                    dataset.add(new WeightedLatLng(new LatLng(lat, lng), weight));
                }
                HeatmapTileProvider.Builder builder = new HeatmapTileProvider.Builder();
                builder.weightedData(dataset);
                // unit: pixel
                builder.radius(35);
                HeatmapTileProvider provider = builder.build();

                TileOverlayOptions options = new TileOverlayOptions();
                options.tileProvider(provider);
                map.addTileOverlay(options);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
