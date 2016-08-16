package com.c102c.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.c102c.finalJKYTJ.R;
import com.c102c.app.activity.JKGLActivity;


import butterknife.ButterKnife;

/**
 * Created by tiansj on 15/9/4.
 */
public class DemoPtrFragment extends Fragment {
    private JKGLActivity context;

    private TextView mheadtext;
    private Button b_jkda_upload;
    private Button b_jkda_edit;
    private Button b_jktj_upload;
    private Button b_jktj_creat;
    private Button b_jkcl_auto;
    private Button b_jkcl_manual;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo_ptr, container, false);
        ButterKnife.bind(this, view);
        mheadtext =(TextView) view.findViewById(R.id.textHeadTitle);
        b_jkda_upload =(Button)view.findViewById(R.id.btn_jkda_upload);
        b_jkda_edit =(Button)view.findViewById(R.id.btn_jkda_edit);
        b_jktj_upload =(Button)view.findViewById(R.id.btn_jktj_upload);
        b_jktj_creat =(Button)view.findViewById(R.id.btn_jktj_creat);
        b_jkcl_auto =(Button)view.findViewById(R.id.btn_jkcl_auto);
        b_jkcl_manual =(Button)view.findViewById(R.id.btn_jkcl_manual);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = (JKGLActivity) getActivity();

        mheadtext.setText("健康体检");
        b_jkda_upload.setVisibility(View.INVISIBLE);
        b_jkda_edit.setVisibility(View.INVISIBLE);
        b_jktj_upload.setVisibility(View. VISIBLE);
        b_jktj_creat.setVisibility(View.VISIBLE);
        b_jkcl_manual.setVisibility(View.INVISIBLE);
        b_jkcl_auto.setVisibility(View.INVISIBLE);

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
