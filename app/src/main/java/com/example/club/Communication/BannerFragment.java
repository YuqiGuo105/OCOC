package com.example.club.Communication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.club.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BannnerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BannerFragment extends Fragment {
    private int imgRes;

    public BannerFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate=inflater.inflate(R.layout.fragment_banner, container, false);
        ImageView ivBanner=inflate.findViewById(R.id.iv_Banner);
        ivBanner.setImageResource(imgRes);
        return inflate;
    }

    public void setImage(int imgRes)
    {
        this.imgRes=imgRes;
    }

}