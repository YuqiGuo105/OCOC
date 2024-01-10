package com.example.club.Communication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.club.Objects.Activity;
import com.example.club.R;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Activity> {
    private int resourceId;

    public MyAdapter(Context context, int textViewResourceId, List<Activity> objects) {
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = getItem(position);

        // 加个判断，以免ListView每次滚动时都要重新加载布局，以提高运行效率
        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            // 避免ListView每次滚动时都要重新加载布局，以提高运行效率
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

            // 避免每次调用getView()时都要重新获取控件实例
            viewHolder=new ViewHolder();
            viewHolder.activityImage = view.findViewById(R.id.item_image);
            viewHolder.activityName = view.findViewById(R.id.activityName);
            viewHolder.date = view.findViewById(R.id.Date);
            viewHolder.host = view.findViewById(R.id.host);

            // 将ViewHolder存储在View中（即将控件的实例存储在其中）
            view.setTag(viewHolder);

        } else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }

        viewHolder.activityImage.setImageBitmap(activity.getImage());
        viewHolder.activityName.setText(activity.getActivityName());
        viewHolder.date.setText(activity.getDate());
        viewHolder.host.setText(activity.getPostUserID());
        
        return view;
    }


    class ViewHolder {
        ImageView activityImage;
        TextView activityName;
        TextView date;
        TextView host;
    }
}


