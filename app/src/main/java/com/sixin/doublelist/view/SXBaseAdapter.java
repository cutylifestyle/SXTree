package com.sixin.doublelist.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Collection;
import java.util.List;


public abstract class SXBaseAdapter<T> extends BaseAdapter {

    private Context mContext;
    private List<T> mData;

    public SXBaseAdapter(Context context,List<T> data){
        if(context == null || data == null){
            throw new NullPointerException("context & data must not null");
        }
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SXViewHolder holder = null;
        if(convertView == null){
            convertView = getItemView(position,parent);
            holder = getViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (SXViewHolder) convertView.getTag();
        }
        setView(holder, position, convertView, parent);
        return convertView;
    }

    protected abstract SXViewHolder getViewHolder(View convertView);

    protected abstract void setView(SXViewHolder holder, int position, View convertView, ViewGroup parent);

    protected abstract View getItemView(int position, ViewGroup parent);

    abstract class SXViewHolder {
        public SXViewHolder(View itemView){
            initView(itemView);
        }
        protected abstract void initView(View itemView);
    }

    public void removeAll(){
        if(mData != null){
            mData.clear();
        }
    }

    public void addAll(Collection collection){
        if(mData != null){
            mData.addAll(collection);
        }
    }

    public void add(T t){
        if(mData != null){
            mData.add(t);
        }
    }

    public List<T> subList(int fromIndex,int toIndex){
        List<T> data = null;
        if(mData != null){
            data =  mData.subList(fromIndex, toIndex);
        }
        return data;
    }

    public List<T> getData(){
        return mData;
    }

    public void setData(List<T> data){
        mData = data;
    }


}
