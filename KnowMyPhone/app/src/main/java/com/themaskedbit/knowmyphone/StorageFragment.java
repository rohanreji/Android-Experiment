package com.themaskedbit.knowmyphone;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

@SuppressLint("ValidFragment")
class StorageFragment extends Fragment {

    View rootView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    int[] imageArray;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_storage, container, false);
//        TextView storageText = (TextView)rootView.findViewById(R.id.storage_text);
//        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.traslate_anim);
//        storageText.setAnimation(anim);
        imageArray = new int[]{R.drawable.ic_tablet_android_green_24dp,R.drawable.ic_stay_primary_portrait_black_24dp,R.drawable.ic_launcher_foreground};
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.storage_recycler_view);
        mLayoutManager = new GridLayoutManager(inflater.getContext(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new StorageAdpater(imageArray);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("Storage",isVisibleToUser+ " value");
        if(isVisibleToUser ){
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getActivity(),R.anim.gridlayout_animation);
            mRecyclerView.setLayoutAnimation(animation);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Storage", "on resumed");
    }
}

