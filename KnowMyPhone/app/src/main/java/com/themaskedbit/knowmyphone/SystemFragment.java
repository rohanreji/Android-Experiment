package com.themaskedbit.knowmyphone;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

@SuppressLint("ValidFragment")
class SystemFragment extends Fragment {
    private static final String TAG_LOG = "System Fragment";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String[] descriptorDataSet;
    String[] valueDataSet;
    Set<String> deviceIdList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_system, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        descriptorDataSet = new String[]{"Manufacturer", "Board", "Hardware"};
        String manufacturer = android.os.Build.MANUFACTURER;
        String board = Build.BOARD;
        String hardware = Build.HARDWARE;
        valueDataSet = new String[]{manufacturer, board, hardware};
        mAdapter = new MyAdapter(descriptorDataSet, valueDataSet);
        mRecyclerView.setAdapter(mAdapter);

        if (isTablet()) {
            ((ImageView) rootView.findViewById(R.id.phone_img)).setImageResource(R.drawable.ic_tablet_android_green_24dp);
        } else {
            ((ImageView) rootView.findViewById(R.id.phone_img)).setImageResource(R.drawable.ic_stay_primary_portrait_black_24dp);
        }
        ((TextView) rootView.findViewById(R.id.phone_name)).setText(Build.BRAND);
        ((TextView) rootView.findViewById(R.id.phone_model)).setText(Build.MODEL);


        return rootView;
    }



    public boolean isTablet() {
        try {
            DisplayMetrics dm = getActivity().getResources().getDisplayMetrics();
            float screenWidth  = dm.widthPixels / dm.xdpi;
            float screenHeight = dm.heightPixels / dm.ydpi;
            double size = Math.sqrt(Math.pow(screenWidth, 2) +
                    Math.pow(screenHeight, 2));
            return size >= 6;
        } catch(Throwable t) {
            Log.d(TAG_LOG, "Failed to compute screen size", t);
            return false;
        }

    }
}