package com.example.club.Communication.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.example.club.R;

import java.util.List;
import java.util.Map;

public class ListViewAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public ListViewAdapter(Context context, List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class Clubs{
        public ImageView image;
        public Button title;
    }

    @Override
    public int getCount() {
        return data.size();
    }
    /**
     * 获得某一位置的数据
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    /**
     * 获得唯一标识
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Clubs clubs = null;
        if(convertView==null){
            clubs = new Clubs();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.input_hp_activity_item, null);
            clubs.image= (ImageView) convertView.findViewById(R.id.activity_club_image);
            clubs.title= (Button) convertView.findViewById(R.id.activity_club_name);
            convertView.setTag(clubs);
        }else{
            clubs=(Clubs)convertView.getTag();
        }

        //绑定数据
        clubs.image.setImageBitmap((Bitmap) data.get(position).get("image"));
        clubs.title.setText((String)data.get(position).get("title"));
        return convertView;
    }

}