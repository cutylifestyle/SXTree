package com.sixin.doublelist.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sixin.doublelist.R;

import java.util.List;

/**
 * @author 周文涛
 */

public class SXTree extends LinearLayout {
    // TODO: 2017/9/2 提供一个接口，对水平ListView以及垂直listView进行属性设置
    private static final String TAG = SXTree.class.getName();

    private static final int S_DEFLAUT_H_HEIGHT = 100;

    /**
     * SXTree包含一个水平listView和一个垂直listView
     * */
    private HorizontalListView mHorizontalListView;
    private ListView mVerticalListView;

    /**
     * 水平listView适配器和垂直listView的适配器
     * */
    private SXBaseAdapter mHAdapter;
    private SXBaseAdapter mVAdapter;

    /**
     * mRefreshListener:刷新数据的接口；
     * mEndListener: 无新数据，定义后续行为的接口
     * */
    private RefreshListener mRefreshListener;
    private EndListener mEndListener;

    /**
     * 水平listView的高度,单位：像素px
     * */
    private int hHeight;

    public SXTree(Context context) {
        super(context);
    }

    public SXTree(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SXTree);
        hHeight = a.getDimensionPixelSize(R.styleable.SXTree_hHeight, S_DEFLAUT_H_HEIGHT);
        a.recycle();

        //设置布局方向
        setOrientation(VERTICAL);

        initLayout(context);
    }

    public SXTree(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initLayout(Context context) {
        //初始化水平listView
        mHorizontalListView = new HorizontalListView(context);
        ViewGroup.LayoutParams hParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, hHeight);
        addView(mHorizontalListView,hParams);

        //初始化垂直listView
        mVerticalListView = new ListView(context);
        ViewGroup.LayoutParams vParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mVerticalListView,vParams);
    }

    /**
     * 设置水平ListView的适配器
     * @param hAdapter 水平适配器
     * */
    public void setHAdapter(SXBaseAdapter hAdapter){
        mHAdapter = hAdapter;
        mHorizontalListView.setAdapter(hAdapter);
    }

    /**
     * 设置垂直listView的适配器
     * @param vAdapter 垂直适配器
     * */
    public void setVAadapter(SXBaseAdapter vAdapter){
        mVAdapter = vAdapter;
        mVerticalListView.setAdapter(vAdapter);
    }

    /**
     * 实现水平listView与垂直listView的交互
     * @param isInteractive 是否需要实现水平listView与垂直listView之间的交互
     * @param refreshListener 刷新数据的接口
     * @param endListener 无新数据，定义后续行为的接口
     * */
    public void completeInteractive(boolean isInteractive,RefreshListener refreshListener,EndListener endListener){
        if(!isInteractive){
            return;
        }

        if(refreshListener == null || endListener == null){
            throw new NullPointerException("refreshListener & endListener must not null");
        }

        mRefreshListener = refreshListener;
        mEndListener = endListener;

        setHListViewListener();
        setVListViewListener();
    }

    private void setHListViewListener() {
        mHorizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int totalCount = mHAdapter.getCount();
                boolean isLastPosition = position == totalCount -1;
                if(!isLastPosition && position != 0){
                    //刷新水平listView
                    List subData = mHAdapter.subList(0,position);
                    if(subData != null && subData.size() > 0){
                        mHAdapter.setData(subData);
                        mHAdapter.notifyDataSetChanged();
                    }

                    //通知垂直列表刷新数据
                    List data = mRefreshListener.refresh(parent, view, position, id,mHAdapter);
                    if(data != null && data.size() > 0) {
                        mVAdapter.removeAll();
                        mVAdapter.addAll(data);
                        mVAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void setVListViewListener(){
        mVerticalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List data = mRefreshListener.refresh(parent,view,position,id,mVAdapter);

                //刷新垂直列表数据以及水平列表数据
                if(data != null && data.size() > 0){
                    Object value = mVAdapter.getItem(position);
                    mVAdapter.removeAll();
                    mVAdapter.addAll(data);
                    mVAdapter.notifyDataSetChanged();


                    mHAdapter.add(value);
                    mHAdapter.notifyDataSetChanged();
                }else{
                    //没有新数据，用户定义后续需要执行的操作
                    mEndListener.follow(parent,view,position,id);
                }
            }
        });
    }



    /**
     * 刷新数据的接口
     * */
    public interface RefreshListener{
        /**
         * 刷新数据的方法
         * */
        List refresh(AdapterView<?> parent, View view, int position, long id,SXBaseAdapter adapter);
    }

    /**
     * 已经没有新数据需要刷新了，由用户确定后续行为的接口
     * */
    public interface EndListener{
        /**
         * 定义后续程序走向的方法
         * */
        void follow(AdapterView<?> parent, View view, int position, long id);
    }
}
