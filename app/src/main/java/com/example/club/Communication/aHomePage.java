package com.example.club.Communication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.club.Communication.Adapter.ResultAdapter;
import com.example.club.Database.ManageActivityImple;
import com.example.club.Objects.Activity;
import com.example.club.R;
import com.example.club.Service.activites;
import com.example.club.view.Indicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class aHomePage extends AppCompatActivity {

    private Button mBtnCancel4;
    private ImageButton mBtnPop;
    private ImageButton mBtnPop1;
    private PopupWindow mPop;
    private PopupWindow mPop1;
    private Button mBtnDialog2;
    private Button mBtnDialog1;


    //end
    private RadioGroup mRadioGroup;
    private Fragment[]mFragments;
    private RadioButton mRadioButtonHome;
    //scroll
    private ScrollView mScro;
    private LinearLayout mLinear;
    private TextView mTv;

    private HorizontalScrollView hscroll;
    private LinearLayout hlinear;
    private ImageButton htv;

    //list
    private String[] names = new String[]{"food club", "dance club", "music club"};
    private String[] says = new String[]{"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"};
    private int[] imgIds = new int[]{R.drawable.c1, R.drawable.r1, R.drawable.c1};

    //banner
    private int[] imgResID =new int []{R.drawable.c1, R.drawable.r1, R.drawable.c1};
    private ViewPager mviewPager;
    private Handler mHandler=new Handler();
    //小圆点
    private Indicator mIndicator;
    //search
    private EditText mSearch;
    private Button m5;
    private ImageButton m4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_wl);
//search
        m4=(ImageButton)findViewById(R.id.buttonsc1);
        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(aHomePage.this, Search.class);
                startActivity(intent);

            }
        });



        //banner
        mviewPager=findViewById(R.id.viewPager);
        //设置适配器
        mviewPager.setAdapter(new MyFragmentAdpter((getSupportFragmentManager())));

        //设置页面监听
        mviewPager.setOnPageChangeListener(new MyPagerListner());
        mIndicator =findViewById(R.id.indicator);
        autoScroll();
        //below
        mBtnCancel4=(Button)findViewById(R.id.radio_button_profile);
        mBtnCancel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(aHomePage.this, UserAccount.class);
                startActivity(intent);

            }
        });
        mBtnCancel4=(Button)findViewById(R.id.radio_button_discovery);
        mBtnCancel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(aHomePage.this, activity.class);
                startActivity(intent);

            }
        });


        //list
        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("touxiang", imgIds[i]);
            showitem.put("name", names[i]);
            showitem.put("says", says[i]);
            listitem.add(showitem);
        }


        //search
        ListView lv = (ListView)findViewById(R.id.lv);

        ArrayList<Activity> result = new ArrayList<Activity>();
        try {
            result = search("","");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView listView= (ListView) findViewById(R.id.hh);
        ResultAdapter adapter = new ResultAdapter(this,R.layout.moban,result);
        listView.setAdapter(adapter);

        //pop1
        mBtnPop1=(ImageButton)findViewById(R.id.btn_pop1);
        mBtnPop1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                View view=getLayoutInflater().inflate(R.layout.layout_pop1,null);
                mBtnDialog1=(Button)view.findViewById(R.id.tv_time);
                mBtnDialog1.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        Toast.makeText(aHomePage.this,"time",Toast.LENGTH_SHORT).show();
                    }
                });
                mBtnDialog2=(Button)view.findViewById(R.id.tv_hot);
                mBtnDialog2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        Toast.makeText(aHomePage.this,"hot",Toast.LENGTH_SHORT).show();
                    }
                });
                mPop1=new PopupWindow(view,250, ViewGroup.LayoutParams.WRAP_CONTENT);
                mPop1.setOutsideTouchable(true);
                mPop1.setFocusable(true);
                mPop1.showAsDropDown(mBtnPop1);
            }
        });


    }
    //banner
    //自动滚动
    private void autoScroll()
    {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取当前的页面下标
                int currentItem=mviewPager.getCurrentItem();
                mviewPager.setCurrentItem(currentItem+1);
                //重复调用该滚动方法,每三秒钟滚动一次
                mHandler.postDelayed(this,3000);

            }
        },3000);
    }
    class MyPagerListner implements ViewPager.OnPageChangeListener{
        //ViewPager的滚动的
        /***
         *
         * @param position
         * @param positionOffset    偏移的百分比
         * @param positionOffsetPixels    偏移量
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.e("onPageScrolled", "positionOffset<< "+positionOffset +"  positionOffsetPixels<<<" + positionOffsetPixels);
            mIndicator.setOffset(position,positionOffset);
        }
        //ViewPager选中
        @Override
        public void onPageSelected(int position) {

        }
        //ViewPager滑动状态改变
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyFragmentAdpter extends FragmentPagerAdapter {
        //构造方法
        public  MyFragmentAdpter(FragmentManager fm)
        {
            super(fm);
        }

        //返回fragment的视图
        @Override
        public Fragment getItem(int i) {
            i %=imgResID.length;
            BannerFragment fragment=new BannerFragment();
            fragment.setImage(imgResID[i]);
            return fragment;
        }
        @Override
        public int getCount() {
            //设置滚动的一个最大值
            return Integer.MAX_VALUE;
        }
        //

    }


    //search
    @RequiresApi(api = Build.VERSION_CODES.P)
    private ArrayList<Activity> search (String category, String key) throws JSONException {
        ArrayList<Activity> activities = new ArrayList<Activity>();

        ManageActivityImple database = new ManageActivityImple(aHomePage.this);
        activites operation = new activites();

        JSONArray result = database.getResults("Activity");
//        result = operation.search(result, key, category);

        for (int i=0; i<result.length(); i++) {
            JSONObject temp = result.getJSONObject(i);

            Bitmap photo = database.search(temp.getString("activityid")).getImage();

            Activity activity = new Activity(temp.getString("activityid"), temp.getInt("postByType"),
                    temp.getString("postUserID"), temp.getString("activityName"), temp.getString("location"),
                    temp.getString("date"), temp.getString("time"), temp.getString("type"),
                    temp.getString("description"), temp.getInt("capacity"), photo,
                    temp.getInt("likes"), temp.getString("detailsurl")
            );

            activities.add(activity);
        }

        return activities;
    }
}

