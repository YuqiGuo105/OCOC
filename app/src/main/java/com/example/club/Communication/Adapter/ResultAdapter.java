package com.example.club.Communication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.List;

import com.example.club.Objects.Activity;
import com.example.club.R;

public class ResultAdapter extends BaseAdapter {
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<Activity> list;

    final int TYPE1 = 0;
    final int TYPE2 = 1;
    int datacount = -1;
    CustomOnItemClickListent clickListent;

    public ResultAdapter(Context context, int resource, ArrayList<Activity> list){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.resource = resource;

        if(list==null){
            this.list=new ArrayList<>();
        }else if (list.size() % 2 == 0) {
            datacount = (list.size() / 2);
        } else {
            datacount = (list.size() / 2 + 1);
        }

        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 < datacount) {
            return TYPE1;
        } else {
            if (list.size() % 2 != 0) {
                return TYPE2;
            } else {
                return TYPE1;
            }
        }
    }


    @Override
    public int getCount() {
        if(list.size()%2>0) {
            return list.size()/2+1;
        } else {
            return list.size()/2;
        }
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        int type = getItemViewType(position);
        View view=null;
        //   view=convertView;
        switch (type) {
            case TYPE1:
                view=convertView;
                ViewHolder viewHolder = null;
                if (view == null || !(view.getTag() instanceof ViewHolder)) {
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.moban, null);
                    viewHolder = new ViewHolder(view);
                    view.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }
                viewHolder.textLeft.setText(list.get(2 * position).getActivityName());
                viewHolder.textRight.setText(list.get(2 * position + 1).getActivityName());
                viewHolder.imageLegt.setImageBitmap(list.get(2 * position).getImage());
                viewHolder.imageRight.setImageBitmap(list.get(2 * position + 1).getImage());

                break;
            case TYPE2:
                view=new View(parent.getContext());
                ViewHolderType2 holderType2 = null;
                if (view == null || !(view.getTag() instanceof ViewHolder)) {
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.moban2, null);
                    holderType2 = new ViewHolderType2(view);
                    view.setTag(holderType2);
                } else {
                    holderType2 = (ViewHolderType2) view.getTag();
                }
                holderType2.textLeftType2.setText(list.get(2 * position).getActivityName());
                holderType2.imageLegtType2.setImageBitmap(list.get(2 * position).getImage());
//                holderType2.layoutLeftType2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        clickListent.onItemClick(parent, convertView, (position * 2), list.get((position * 2)));
//                    }
//                });
        }

        return view;
    }

    public static class ViewHolder{
        protected ImageView imageLegt;
        protected TextView textLeft;
        protected LinearLayout layoutLeft;
        protected ImageView imageRight;
        protected TextView textRight;
        protected LinearLayout layoutRight;

        ViewHolder(View rootView) {
            initView(rootView);
        }

        private void initView(View rootView) {
            imageLegt = (ImageView) rootView.findViewById(R.id.image_legt);
            textLeft = (TextView) rootView.findViewById(R.id.text_left);
            layoutLeft = (LinearLayout) rootView.findViewById(R.id.layout_left);
            imageRight = (ImageView) rootView.findViewById(R.id.image_right);
            textRight = (TextView) rootView.findViewById(R.id.text_right);
            layoutRight = (LinearLayout) rootView.findViewById(R.id.layout_right);
        }

    }

    static class ViewHolderType2 {
        protected ImageView imageLegtType2;
        protected TextView textLeftType2;
        protected LinearLayout layoutLeftType2;

        ViewHolderType2(View rootView) {
            initView(rootView);
        }

        private void initView(View rootView) {
            imageLegtType2 = (ImageView) rootView.findViewById(R.id.image_legt_type2);
            textLeftType2 = (TextView) rootView.findViewById(R.id.text_left_type2);
            layoutLeftType2 = (LinearLayout) rootView.findViewById(R.id.layout_left_type2);
        }
    }


    //外部回调接口
    public void setOnItemClickListent(CustomOnItemClickListent customOnItemClickListent) {
        clickListent = customOnItemClickListent;
    }
    //内部接口
    public interface CustomOnItemClickListent {
        public void onItemClick(ViewGroup parent, View view, int position, Activity data);
    }


}
