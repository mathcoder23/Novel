package com.mt23.novel.utils.listview.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class CommonAdapter<T> extends BaseAdapter
{
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas = new ArrayList<T>();
    protected final int mItemLayoutId;

    public CommonAdapter(Context context,  int itemLayoutId)
    {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);

        this.mItemLayoutId = itemLayoutId;
    }
    public void updateData(List<T> mDatas)
    {
        this.mDatas = mDatas;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return mDatas.size();
    }

    @Override
    public T getItem(int position)
    {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        if (null != itemHandle)
        {
            itemHandle.onItem(viewHolder, getItem(position));
        }
        return viewHolder.getConvertView();

    }
    private ItemHandle<T> itemHandle;
    public void setItemHandle(ItemHandle<T> itemHandle)
    {
        this.itemHandle = itemHandle;
    }

    private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent)
    {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }
    public interface ItemHandle<T>{
        void onItem(ViewHolder helper, T item);
    }
}