package com.sixin.doublelist.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class HAdapter extends SXBaseAdapter<String> {


    public HAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    protected SXViewHolder getViewHolder(View convertView) {
        return new HViewHolder(convertView);
    }

    @Override
    protected void setView(SXViewHolder holder, int position, View convertView, ViewGroup parent) {

    }

    @Override
    protected View getItemView(int position, ViewGroup parent) {
        return null;
    }

    class HViewHolder extends SXViewHolder{

        public HViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView(View itemView) {

        }
    }
}
