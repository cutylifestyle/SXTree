package com.sixin.doublelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sixin.doublelist.view.HorizontalListView;
import com.sixin.doublelist.view.HAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private HorizontalListView mListView;
    private HAdapter adapter;
    private String[] leftStr = new String[]{"面食类", "盖饭", "寿司", "烧烤", "酒水", "凉菜", "小吃", "粥", "休闲"};
    private boolean[] flagArray = {true, false, false, false, false, false, false, false, false};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        initView();
        //programmer1
        setListViewAdapter();
    }

    private void setListViewAdapter() {

    }

    private void initView() {

    }


}
