package com.sixin.doublelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sixin.doublelist.view.HAdapter;
import com.sixin.doublelist.view.SXTree;
import com.sixin.doublelist.view.VAdapter;

import db.DBHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private SXTree mSXTree;

    private HAdapter mHAdapter;
    private VAdapter mVAdapter;

    private DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




}
