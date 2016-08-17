package com.c102c.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.c102c.app.activity.JKGLActivity;
import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch_by_li;
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.model.LocalMeasurementBaiXiBao;
import com.c102c.app.model.LocalMeasurementEWen;
import com.c102c.app.model.LocalMeasurementNiaoYe;
import com.c102c.app.model.LocalMeasurementXinDian;
import com.c102c.app.model.LocalMeasurementXueTang;
import com.c102c.app.model.LocalMeasurementXueYa;
import com.c102c.app.model.LocalMeasurementXueYang;
import com.c102c.finalJKYTJ.R;
import com.health.measurement.ETempActivity;
import com.health.measurement.MeasureOnPC300Activity;
import com.health.measurement.MeasureUrineActivity;
import com.health.measurement.MeasureWbc;
import com.health.measurement.MeasureWeilionECG;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;

/**
 * Created by Guoquan on 2016/8/16.
 */
public class JKCLFragment  extends Fragment {
    private JKGLActivity context;
    private TextView mheadtext;
    private Button b_jkda_upload;
    private Button b_jkda_edit;
    private Button b_jktj_upload;
    private Button b_jktj_creat;
    private Button b_jkcl_auto;
    private Button b_jkcl_manual;
    private HealthRecord_down mHealthRecord_down;

    private LocalMeasurementEWen mLocalMeasurementEWen;
    private LocalMeasurementNiaoYe mLocalMeasurementNiaoYe;
    private LocalMeasurementXinDian mLocalMeasurementXinDian;
    private LocalMeasurementXueTang mLocalMeasurementXueTang;
    private LocalMeasurementXueYang mLocalMeasurementXueYang;
    private LocalMeasurementXueYa mLocalMeasurementXueYa;
    private LocalMeasurementBaiXiBao mLocalMeasurementBaiXiBao;

    private static final String TAG = "JKCLActivity";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jkcl_pager, container, false);
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

        mheadtext.setText("健康测量");
        b_jkda_upload.setVisibility(View.INVISIBLE);
        b_jkda_edit.setVisibility(View.INVISIBLE);
        b_jktj_upload.setVisibility(View. INVISIBLE);
        b_jktj_creat.setVisibility(View.INVISIBLE);
        b_jkcl_manual.setVisibility(View.VISIBLE);
        b_jkcl_auto.setVisibility(View.VISIBLE);

        mHealthRecord_down = Basesence.getTempHealthRecord_down();
        AppDB.getInstance(context);

    }


    public void onViewClicked(View v) {
        Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.jkcl_test_bp_btn:
                    // 血压测量
                intent.setClass(context, MeasureOnPC300Activity.class);
                intent.putExtra("token", MeasureOnPC300Activity.BLOOD_PRESSURE_LEFT);
                startActivityForResult(intent, Basesence.MEASURE);
                    break;
                case R.id.jkcl_test_xueyang_btn:
                    // 血氧测量
                intent.setClass(context, MeasureOnPC300Activity.class);
                intent.putExtra("token", MeasureOnPC300Activity.BLOOD_OXYGEN);
                startActivityForResult(intent, Basesence.MEASURE);
                    break;
                case R.id.jkcl_test_ewen_btn:
                    // 额温测量
                intent.setClass(context, ETempActivity.class);
                startActivityForResult(intent, Basesence.MEASURE);
                    break;
                case R.id.jkcl_test_xuetang_btn:
                    // 血糖测量
                intent.setClass(context, MeasureOnPC300Activity.class);
                intent.putExtra("token", MeasureOnPC300Activity.BLOOD_SUGAR);
                startActivityForResult(intent, Basesence.MEASURE);
                    break;
                case R.id.jkcl_test_niaoyifenxi_btn:
                    // 尿液分析
                intent.setClass(context, MeasureUrineActivity.class);
                startActivityForResult(intent, Basesence.MEASURE);
                    break;
                case R.id.jkcl_test_ecg12_btn:
                    // 十二导心电
                intent.setClass(context, MeasureWeilionECG.class);
                startActivityForResult(intent, Basesence.MEASURE);
                    break;
                case R.id.test_baixibao:
                    // 白细胞
                intent.setClass(context, MeasureWbc.class);
                startActivityForResult(intent, Basesence.MEASURE);
                    break;
                default:
                    break;
            }
        }




    private Handler handler = Basesence.getupdatehandler(context);


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
       switch (resultCode) {
            case Basesence.MEASURE_RESULT_LEFT_BLOOD_PRESURE:
                if (!data.getStringExtra("ssy").equals("")) {
                    mLocalMeasurementXueYa = new LocalMeasurementXueYa();
                    mLocalMeasurementXueYa.setPersonId(mHealthRecord_down
                            .getPersonId());
                    mLocalMeasurementXueYa.setName(mHealthRecord_down.getName());
                    mLocalMeasurementXueYa
                            .setIdCard(mHealthRecord_down.getIdCard());
                    mLocalMeasurementXueYa.setSsy(data.getStringExtra("ssy"));
                    mLocalMeasurementXueYa.setSzy(data.getStringExtra("szy"));
                    mLocalMeasurementXueYa.setMl(data.getStringExtra("ml"));
                    mLocalMeasurementXueYa.setTime(sdformat.format(new Date()).toString());
                    AppDB.add(mLocalMeasurementXueYa);
                    Fetch_by_li.communicate("M0100080201", context,
                            handler, mLocalMeasurementXueYa);
                    Toast.makeText(context, "添加测量血压记录", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "没有测量血压", Toast.LENGTH_LONG).show();
                }
                break;
            case Basesence.MEASURE_RESULT_BLOOD_OXYGEN:
                if (!data.getStringExtra("xy").equals("")) {
                    mLocalMeasurementXueYang = new LocalMeasurementXueYang();
                    mLocalMeasurementXueYang.setPersonId(mHealthRecord_down
                            .getPersonId());
                    mLocalMeasurementXueYang.setName(mHealthRecord_down.getName());
                    mLocalMeasurementXueYang.setIdCard(mHealthRecord_down
                            .getIdCard());
                    mLocalMeasurementXueYang.setXy(data.getStringExtra("xy"));
                    mLocalMeasurementXueYang.setTime(sdformat.format(new Date()).toString());
                    AppDB.add(mLocalMeasurementXueYang);
                    Fetch_by_li.communicate("M0100080201", context,
                            handler, mLocalMeasurementXueYang);
                    Toast.makeText(context, "添加测量血氧记录", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "没有测量血氧", Toast.LENGTH_LONG).show();
                }
                break;
            case Basesence.MEASURE_RESULT_BLOOD_SUGAR:
                // mLocalMeasurement.setXt(data.getStringExtra("xt"));
                if (!data.getStringExtra("xt").equals("")) {
                    mLocalMeasurementXueTang = new LocalMeasurementXueTang();
                    mLocalMeasurementXueTang.setPersonId(mHealthRecord_down
                            .getPersonId());
                    mLocalMeasurementXueTang.setName(mHealthRecord_down.getName());
                    mLocalMeasurementXueTang.setIdCard(mHealthRecord_down
                            .getIdCard());
                    mLocalMeasurementXueTang.setXt(data.getStringExtra("xt"));
                    mLocalMeasurementXueTang.setTime(sdformat.format(new Date()).toString());
                    AppDB.add(mLocalMeasurementXueTang);
                    Fetch_by_li.communicate("M0100080201", context,
                            handler, mLocalMeasurementXueTang);
                    Toast.makeText(context, "添加测量血糖记录", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "没有测量血糖", Toast.LENGTH_LONG).show();
                }
                break;
            case Basesence.MEASURE_RESULT_ETMP:
                if (!data.getStringExtra(ETempActivity.TAG).equals("")) {
                    mLocalMeasurementEWen = new LocalMeasurementEWen();
                    mLocalMeasurementEWen.setPersonId(mHealthRecord_down
                            .getPersonId());
                    mLocalMeasurementEWen.setName(mHealthRecord_down.getName());
                    mLocalMeasurementEWen.setIdCard(mHealthRecord_down.getIdCard());
                    mLocalMeasurementEWen.setEw(data
                            .getStringExtra(ETempActivity.TAG));
                    mLocalMeasurementEWen.setTime(sdformat.format(new Date()).toString());
                    AppDB.add(mLocalMeasurementEWen);

                    Fetch_by_li.communicate("M0100080201", context,
                            handler, mLocalMeasurementEWen);
                    Toast.makeText(context, "添加测量额温记录", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "没有测量额温", Toast.LENGTH_LONG).show();
                }
                break;
            case Basesence.MEASURE_RESULT_URINE:
                if (!data.getStringExtra("ndb").equals("")) {
                    mLocalMeasurementNiaoYe = new LocalMeasurementNiaoYe();
                    mLocalMeasurementNiaoYe.setPersonId(mHealthRecord_down
                            .getPersonId());
                    mLocalMeasurementNiaoYe.setName(mHealthRecord_down.getName());
                    mLocalMeasurementNiaoYe.setIdCard(mHealthRecord_down
                            .getIdCard());
                    mLocalMeasurementNiaoYe.setPro(data.getStringExtra("ndb"));
                    mLocalMeasurementNiaoYe.setBld(data.getStringExtra("nqx"));
                    mLocalMeasurementNiaoYe.setUglu(data.getStringExtra("nt"));
                    mLocalMeasurementNiaoYe.setKet(data.getStringExtra("ntt"));
                    mLocalMeasurementNiaoYe.setNqt(data.getStringExtra("nqt"));
                    mLocalMeasurementNiaoYe.setTime(sdformat.format(new Date()).toString());
                    AppDB.add(mLocalMeasurementNiaoYe);
                    Fetch_by_li.communicate("M0100080201", context,
                            handler, mLocalMeasurementNiaoYe);
                    Toast.makeText(context, "添加尿液分析记录", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "没有进行尿液分析", Toast.LENGTH_LONG).show();
                }
                break;
            case Basesence.MEASURE_RESULT_XIN_DIAN:
                if (!data.getStringExtra("xdjg").equals("")) {
                    mLocalMeasurementXinDian = new LocalMeasurementXinDian();
                    mLocalMeasurementXinDian.setPersonId(mHealthRecord_down
                            .getPersonId());
                    mLocalMeasurementXinDian.setName(mHealthRecord_down.getName());
                    mLocalMeasurementXinDian.setIdCard(mHealthRecord_down
                            .getIdCard());
                    mLocalMeasurementXinDian.setXdjg(data.getStringExtra("xdjg"));
                    mLocalMeasurementXinDian.setXdsj(data.getStringExtra("xdsj"));
                    mLocalMeasurementXinDian.setTime(sdformat.format(new Date()).toString());
                    AppDB.add(mLocalMeasurementXinDian);
                    Fetch_by_li.communicate("M0100080201", context,
                            handler, mLocalMeasurementXinDian);
                    Toast.makeText(context, "添加心电测量记录", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "没有进行心电测量", Toast.LENGTH_LONG).show();
                }
                break;
            case Basesence.MEASURE_RESULT_WBC:
                if (!data.getStringExtra("wbc").equals("")) {
                    mLocalMeasurementBaiXiBao = new LocalMeasurementBaiXiBao();
                    mLocalMeasurementBaiXiBao.setPersonId(mHealthRecord_down
                            .getPersonId());
                    mLocalMeasurementBaiXiBao.setName(mHealthRecord_down.getName());
                    mLocalMeasurementBaiXiBao.setIdCard(mHealthRecord_down
                            .getIdCard());
                    mLocalMeasurementBaiXiBao.setWbc(data.getStringExtra("wbc"));

                    mLocalMeasurementBaiXiBao.setTime(sdformat.format(new Date()).toString());
                    AppDB.add(mLocalMeasurementBaiXiBao);
                    Fetch_by_li.communicate("M0100080201", context,
                            handler, mLocalMeasurementBaiXiBao);
                    Toast.makeText(context, "添加白细胞测量记录", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "没有进行白细胞测量", Toast.LENGTH_LONG).show();
                }
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
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

