package com.c102c.app.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.c102c.finalJKYTJ.R;
import com.c102c.app.ui.tabstrip.PagerSlidingTabStrip;

import java.util.ArrayList;

public class MainPagerFragment extends Fragment {
    private String TAG="PagerFragment";
    private static String[] TITLES;
    private Activity context;
    private static String[] URLS = new String[]{"", ""};

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private View view1;
    private View view2;
    private View view3;
    private View view4;
    private TextView tview1;
    private TextView tview2;
    private TextView tview3;
    ArrayList<View> viewContainter = new ArrayList<View>();

    private TextView mheadtext;
    private Button b_jkda_upload;
    private Button b_jkda_edit;
    private Button b_jktj_upload;
    private Button b_jktj_creat;
    private Button b_jkcl_auto;
    private Button b_jkcl_manual;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jkda_pager, container, false);
        pager = (ViewPager) view.findViewById(R.id.pager);
        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
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
        context = getActivity();
        mheadtext.setText("健康档案");
        b_jkda_upload.setVisibility(View.VISIBLE);
        b_jkda_edit.setVisibility(View.VISIBLE);
        b_jktj_upload.setVisibility(View. INVISIBLE);
        b_jktj_creat.setVisibility(View.INVISIBLE);
        b_jkcl_manual.setVisibility(View.INVISIBLE);
        b_jkcl_auto.setVisibility(View.INVISIBLE);

        TITLES = getResources().getStringArray(R.array.news_titles);
        //view是我们放进viewPager里面的东西，要为它设置好布局，再放进去
        view1= LayoutInflater.from(context).inflate(R.layout.jkda_lay1, null);
        view2 = LayoutInflater.from(context).inflate(R.layout.jkda_lay2, null);
        view3 = LayoutInflater.from(context).inflate(R.layout.jkda_lay3, null);
        view4 = LayoutInflater.from(context).inflate(R.layout.jkda_lay4, null);

        //为viewPager设置内容
        //tview1= (TextView)view1.findViewById(R.id.text_1);
        //tview2= (TextView)view2.findViewById(R.id.text_2);
        //tview3= (TextView)view3.findViewById(R.id.text_3);
        viewContainter.add(view1);
        viewContainter.add(view2);
        viewContainter.add(view3);
        viewContainter.add(view4);

      //  FragmentPagerAdapter adapter = new NewsAdapter(getChildFragmentManager());
      //  pager.setAdapter(adapter);
        pager.setAdapter(new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                //return false;
                return arg0 == arg1;
            }

            //viewpager中的组件数量
            @Override
            public int getCount() {
                return TITLES.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return TITLES[position % TITLES.length];
            }

            @Override
            public void destroyItem(View container, int position, Object object) {
                // TODO Auto-generated method stub
                //super.destroyItem(container, position, object);
                ((ViewPager)container).removeView(viewContainter.get(position));
            }

            @Override
            public int getItemPosition(Object object) {
                // TODO Auto-generated method stub
                return super.getItemPosition(object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                //return super.instantiateItem(container, position);
                ((ViewPager)container).addView(viewContainter.get(position));
                return viewContainter.get(position);
            }
        });


        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        tabs.setViewPager(pager);

        //tview1.setText("hello 1");
        //tview2.setText("hello 2");
        //tview3.setText("hello 3");
    }
/*
    class NewsAdapter extends FragmentPagerAdapter {
        public NewsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new DemoPtrFragment();
            }
            if (position == 1) {
                return new DemoPtrFragment();
            }
            if (position == 2) {
                return new DemoPtrFragment();
            }
            return new DemoPtrFragment();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position % TITLES.length];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
 */
}
