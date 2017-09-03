package com.sixin.doublelist.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class VAdapter extends SXBaseAdapter<String> {


    public VAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    protected SXViewHolder getViewHolder(View convertView) {
        return null;
    }

    @Override
    protected void setView(SXViewHolder holder, int position, View convertView, ViewGroup parent) {

    }

    @Override
    protected View getItemView(int position, ViewGroup parent) {
        return null;
    }

    class VViewHolder extends SXViewHolder{


        public VViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView(View itemView) {

        }
    }
}
