package com.example.krutikovap.comeleave.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import com.example.krutikovap.comeleave.R;

public class StaticActivity extends Activity {
    private Spinner spinner;
    private GridView gridView;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        spinner = (Spinner)findViewById(R.id.spinnerMounth);
        gridView = (GridView)findViewById(R.id.gridViewStatictic);
        btnBack = (Button)findViewById(R.id.btnBackStatic);
    }
}
