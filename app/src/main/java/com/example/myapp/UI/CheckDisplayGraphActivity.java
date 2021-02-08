package com.example.myapp.UI;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.example.myapp.DB.local.Record;
import com.example.myapp.R;
import com.example.myapp.Util.CheckDisplayGraphViewModelFactory;
import com.example.myapp.ViewModel.CheckDisplayGraphViewModel;

import java.util.List;

public class CheckDisplayGraphActivity extends AppCompatActivity {

    private MapView view;
    private AMap map;

    // locations for the initial default position
    private final double LAT = 21.473343;
    private final double LNG = 109.119254;

    private CheckDisplayGraphViewModel viewModel;

    private Marker currMarker = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_display_graph);

        view = findViewById(R.id.map);
        view.onCreate(savedInstanceState);

        if (map == null) {
            map = view.getMap();
        }
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(LAT, LNG), 13));

        map.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (currMarker != null) {
                    // click other place to hide info window of selected marker
                    currMarker.hideInfoWindow();
                }
            }
        });
        map.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // capture the currently selected marker
                currMarker = marker;
                return false;
            }
        });

        viewModel = new ViewModelProvider(this,new CheckDisplayGraphViewModelFactory(this.getApplication())).get(CheckDisplayGraphViewModel.class);

        Toast t = Toast.makeText(this, "", Toast.LENGTH_LONG);
        t.setText("正在获取数据...");
        t.show();
        viewModel.getAllRecords().observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                t.cancel();
                for (int i = 0; i < records.size(); i++) {
                    Record r = records.get(i);
                    LatLng location = new LatLng(r.lat, r.lng);
                    MarkerOptions option = new MarkerOptions();
                    option.position(location);
                    option.title("地址: " + r.province + r.city + r.district + r.road + r.detail);
                    if (r.note.length() > 0) {
                        option.snippet("描述: " + r.description + "\n" + "状态: " + r.status + "\n" + "审核说明：" + r.note);
                    }
                    else {
                        option.snippet("描述: " + r.description + "\n" + "状态: " + r.status + "\n" + "审核说明：N/A");
                    }
                    if (r.status.equals("等待审核")) {
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.pending));
                    }
                    else if (r.status.equals("通过")) {
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.pass));
                    }
                    else {
                        option.icon(BitmapDescriptorFactory.fromResource(R.drawable.fail));
                    }
                    map.addMarker(option);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        view.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        view.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        view.onDestroy();
    }
}
