package com.c102c.app.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.c102c.finalJKYTJ.R;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;


public class BufferKnifeFragment extends Fragment {

    private Activity context;

    private EditText mEtSearch = null;// 输入搜索内容
    private Button mBtnClearSearchText = null;// 清空搜索信息的按钮
    private LinearLayout mLayoutClearSearchText = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discovery_pager, container, false);
        ButterKnife.bind(this, view);
        mEtSearch = (EditText) view.findViewById(R.id.et_search);
        mBtnClearSearchText = (Button) view.findViewById(R.id.btn_clear_search_text);
        mLayoutClearSearchText = (LinearLayout) view.findViewById(R.id.layout_clear_search_text);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();

    }





    @Override
    public void onResume() {
        super.onResume();
        Picasso.with(context).resumeTag(context);
    }

    @Override
    public void onPause() {
        super.onPause();
        Picasso.with(context).pauseTag(context);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Picasso.with(context).cancelTag(context);
    }
}