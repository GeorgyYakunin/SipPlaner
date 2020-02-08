package com.example.planner.MyPlan;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.planner.R;
import com.example.planner.SipBean;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ActivityEditingPlan extends AppCompatActivity {
    public static ArrayList<SipBean> arrayListAddPlan;
    public static SipBean mySipBean;
    public String TAG = ActivityMyPlans.class.getSimpleName();
    String TAG_PLAN_LIST = "PlanList";
    AdView adView;
    TextView add;
    Button btn_date;
    TextView calcSip;
    int calculateClicked = 0;
    Date curr_date;
    TextView detail;
    TextView et_dt;
    private int flag = 0;
    TextView investAmt;
    TextView maturityAmt;
    EditText name;
    List<SipBean> planList;
    EditText rateReturn;
    String selectedDate;
    EditText sipAmount;
    EditText tunurePer;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.acе_editштп_planы);
        setTitle(getResources().getString(R.string.add_investment));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        loadBannerAd();
        this.planList = loadSharedPreferencesLogList(getApplicationContext());
        this.sipAmount = (EditText) findViewById(R.id.mnthly_investment);
        this.rateReturn = (EditText) findViewById(R.id.rateReturn);
        this.tunurePer = (EditText) findViewById(R.id.tunurePer);
        this.investAmt = (TextView) findViewById(R.id.investAmt);
        this.maturityAmt = (TextView) findViewById(R.id.maturityAmt);
        this.calcSip = (TextView) findViewById(R.id.calcSip);
        this.detail = (TextView) findViewById(R.id.detail);
        this.name = (EditText) findViewById(R.id.name);
        this.et_dt = (TextView) findViewById(R.id.et_dt);
        this.btn_date = (Button) findViewById(R.id.btn_date);
        this.sipAmount.setText(mySipBean.getMnthly_investment());
        this.rateReturn.setText(mySipBean.getRate_of_return());
        this.tunurePer.setText(mySipBean.getYear());
        TextView textView = this.investAmt;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("₹ ");
        stringBuilder.append(mySipBean.getInvestment());
        textView.setText(stringBuilder.toString());
        textView = this.maturityAmt;
        stringBuilder = new StringBuilder();
        stringBuilder.append("₹ ");
        stringBuilder.append(mySipBean.getBalanceE());
        textView.setText(stringBuilder.toString());
        this.name.setText(mySipBean.getName());
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.selectedDate = mySipBean.getStart_date();
        try {
            this.curr_date = simpleDateFormat.parse(this.selectedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        instance.setTime(this.curr_date);
        final int i = instance.get(1);
        final int i2 = instance.get(2);
        final int i3 = instance.get(5);
        this.et_dt.setText(this.selectedDate);
        this.btn_date.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                new DatePickerDialog(ActivityEditingPlan.this, new OnDateSetListener() {
                    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                        Calendar instance = Calendar.getInstance();
                        instance.set(i, i2, i3);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        ActivityEditingPlan.this.curr_date = instance.getTime();
                        ActivityEditingPlan.this.selectedDate = simpleDateFormat.format(ActivityEditingPlan.this.curr_date);
                        ActivityEditingPlan.this.et_dt.setText(ActivityEditingPlan.this.selectedDate);
                    }
                }, i, i2, i3).show();
            }
        });
        this.add = (TextView) findViewById(R.id.add);
        arrayListAddPlan = new ArrayList();
        this.add.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String obj = ActivityEditingPlan.this.name.getText().toString();
                int i = 0;
                if (TextUtils.isEmpty(obj)) {
                    Toast.makeText(ActivityEditingPlan.this.getApplicationContext(), ActivityEditingPlan.this.getResources().getString(R.string.plz_fill_info), 0).show();
                    return;
                }
                double d;
                int i2;
                String obj2 = ActivityEditingPlan.this.sipAmount.getText().toString();
                String obj3 = ActivityEditingPlan.this.rateReturn.getText().toString();
                String obj4 = ActivityEditingPlan.this.tunurePer.getText().toString();
                double parseFloat = (double) Float.parseFloat(obj2);
                float parseFloat2 = Float.parseFloat(obj3);
                float parseFloat3 = Float.parseFloat(obj4);
                if (ActivityEditingPlan.this.flag == 0) {
                    d = (double) parseFloat3;
                    Double.isNaN(parseFloat);
                    Double.isNaN(d);
                    i2 = (int) ((d * parseFloat) * 12.0d);
                } else {
                    d = (double) parseFloat3;
                    Double.isNaN(parseFloat);
                    Double.isNaN(d);
                    i2 = (int) (d * parseFloat);
                }
                d = (double) ((int) parseFloat);
                int i3 = ActivityEditingPlan.this.flag == 0 ? ((int) parseFloat3) * 12 : (int) parseFloat3;
                double d2 = 0.0d;
                double d3 = 0.0d;
                double d4 = d3;
                while (i < i3) {
                    if (d != d2) {
                        Double.isNaN(d);
                        d3 += d;
                        d2 = (double) parseFloat2;
                        Double.isNaN(d2);
                        d3 = ((d3 * d2) / 100.0d) / 12.0d;
                        Double.isNaN(d);
                        d4 += d;
                        d2 = (double) ((int) Math.round(d3));
                        Double.isNaN(d2);
                        d4 += d2;
                        Math.round(d3);
                        d3 = d4;
                    }
                    i++;
                    d2 = 0.0d;
                }
                Calendar instance = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = null;
                try {
                    date = simpleDateFormat.parse(ActivityEditingPlan.this.selectedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                instance.setTime(date);
                Date time = instance.getTime();
                instance.add(1, Integer.parseInt(obj4));
                Date time2 = instance.getTime();
                obj2 = simpleDateFormat.format(time);
                String format = simpleDateFormat.format(time2);
                PrintStream printStream = System.out;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("today::");
                stringBuilder.append(obj2);
                printStream.println(stringBuilder.toString());
                printStream = System.out;
                stringBuilder = new StringBuilder();
                stringBuilder.append("last::");
                stringBuilder.append(format);
                printStream.println(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                stringBuilder.append(ActivityEditingPlan.this.planList.size());
                stringBuilder.append("");
                Log.e("planList size::", stringBuilder.toString());
                for (i3 = 0; i3 < ActivityEditingPlan.this.planList.size(); i3++) {
                    SipBean sipBean = (SipBean) ActivityEditingPlan.this.planList.get(i3);
                    if (sipBean.getId() == ActivityEditingPlan.mySipBean.getId()) {
                        sipBean.setName(obj);
                        sipBean.setMnthly_investment(String.valueOf(Math.round(parseFloat)));
                        sipBean.setInvestment(String.valueOf(Math.round((float) i2)));
                        sipBean.setBalanceE(String.valueOf(Math.round(d3)));
                        sipBean.setYear(obj4);
                        sipBean.setRate_of_return(obj3);
                        sipBean.setStart_date(obj2);
                        sipBean.setEnd_date(format);
                        ActivityEditingPlan.this.planList.set(i3, sipBean);
                    }
                }
                ActivityEditingPlan activityEditingPlan = ActivityEditingPlan.this;
                activityEditingPlan.saveSharedPreferencesLogList(activityEditingPlan.getApplicationContext(), ActivityEditingPlan.this.planList);
                ActivityEditingPlan.this.onBackPressed();
            }
        });
        this.detail.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (ActivityEditingPlan.this.calculateClicked == 0) {
                    ActivityEditingPlan.arrayListAddPlan.clear();
                    String obj = ActivityEditingPlan.this.sipAmount.getText().toString();
                    String obj2 = ActivityEditingPlan.this.rateReturn.getText().toString();
                    String obj3 = ActivityEditingPlan.this.tunurePer.getText().toString();
                    int i = 0;
                    if (TextUtils.isEmpty(obj)) {
                        Toast.makeText(ActivityEditingPlan.this.getApplicationContext(), ActivityEditingPlan.this.getResources().getString(R.string.plz_fill_info), 0).show();
                        return;
                    } else if (TextUtils.isEmpty(obj2)) {
                        Toast.makeText(ActivityEditingPlan.this.getApplicationContext(), ActivityEditingPlan.this.getResources().getString(R.string.plz_fill_info), 0).show();
                        return;
                    } else if (TextUtils.isEmpty(obj3)) {
                        Toast.makeText(ActivityEditingPlan.this.getApplicationContext(), ActivityEditingPlan.this.getResources().getString(R.string.plz_fill_info), 0).show();
                        return;
                    } else {
                        double parseFloat = (double) Float.parseFloat(obj);
                        float parseFloat2 = Float.parseFloat(obj2);
                        float parseFloat3 = Float.parseFloat(obj3);
                        int access$000 = ActivityEditingPlan.this.flag;
                        double d = (double) ((int) parseFloat);
                        int i2 = ActivityEditingPlan.this.flag == 0 ? ((int) parseFloat3) * 12 : (int) parseFloat3;
                        double d2 = 0.0d;
                        double d3 = 0.0d;
                        double d4 = d3;
                        while (i <= i2) {
                            float f;
                            int i3;
                            SipBean sipBean;
                            if (i == 0) {
                                sipBean = new SipBean();
                                sipBean.setBalanceB("Start Balance");
                                sipBean.setInvestment("Investment");
                                sipBean.setInterst("Interest");
                                sipBean.setBalanceE("End Balance");
                                sipBean.setMonth("Month");
                                ActivityEditingPlan.arrayListAddPlan.add(sipBean);
                                f = parseFloat2;
                                i3 = i2;
                            } else if (d != d2) {
                                sipBean = new SipBean();
                                Double.isNaN(d);
                                d3 += d;
                                d2 = (double) parseFloat2;
                                Double.isNaN(d2);
                                d3 = (d3 * d2) / 100.0d;
                                d2 = d3 / 12.0d;
                                Double.isNaN(d);
                                d4 += d;
                                f = parseFloat2;
                                i3 = i2;
                                double round = (double) ((int) Math.round(d2));
                                Double.isNaN(round);
                                d4 += round;
                                Math.round(d2);
                                sipBean.setBalanceB(String.valueOf(Math.round(d4)));
                                sipBean.setInvestment(String.valueOf(Math.round(parseFloat)));
                                sipBean.setInterst(String.valueOf(Math.round(d3)));
                                sipBean.setBalanceE(String.valueOf(Math.round(d4)));
                                sipBean.setMonth(String.valueOf(Math.round((float) i)));
                                ActivityEditingPlan.arrayListAddPlan.add(sipBean);
                                d3 = d4;
                            } else {
                                f = parseFloat2;
                                i3 = i2;
                            }
                            i++;
                            parseFloat2 = f;
                            i2 = i3;
                            d2 = 0.0d;
                        }
                    }
                }
                PlanDetailFragment planDetailFragment = new PlanDetailFragment();
                planDetailFragment.arrayListAddPlan = ActivityEditingPlan.arrayListAddPlan;
                ActivityEditingPlan.this.getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, planDetailFragment, planDetailFragment.getClass().getSimpleName()).addToBackStack(null).commit();
            }
        });
        this.calcSip.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivityEditingPlan.arrayListAddPlan.clear();
                ActivityEditingPlan activityEditingPlan = ActivityEditingPlan.this;
                activityEditingPlan.calculateClicked = 1;
                String obj = activityEditingPlan.sipAmount.getText().toString();
                String obj2 = ActivityEditingPlan.this.rateReturn.getText().toString();
                String obj3 = ActivityEditingPlan.this.tunurePer.getText().toString();
                int i = 0;
                if (TextUtils.isEmpty(obj)) {
                    Toast.makeText(ActivityEditingPlan.this.getApplicationContext(), ActivityEditingPlan.this.getResources().getString(R.string.plz_fill_info), 0).show();
                } else if (TextUtils.isEmpty(obj2)) {
                    Toast.makeText(ActivityEditingPlan.this.getApplicationContext(), ActivityEditingPlan.this.getResources().getString(R.string.plz_fill_info), 0).show();
                } else if (TextUtils.isEmpty(obj3)) {
                    Toast.makeText(ActivityEditingPlan.this.getApplicationContext(), ActivityEditingPlan.this.getResources().getString(R.string.plz_fill_info), 0).show();
                } else {
                    double d;
                    int i2;
                    double parseFloat = (double) Float.parseFloat(obj);
                    float parseFloat2 = Float.parseFloat(obj2);
                    float parseFloat3 = Float.parseFloat(obj3);
                    if (ActivityEditingPlan.this.flag == 0) {
                        d = (double) parseFloat3;
                        Double.isNaN(parseFloat);
                        Double.isNaN(d);
                        i2 = (int) ((d * parseFloat) * 12.0d);
                    } else {
                        d = (double) parseFloat3;
                        Double.isNaN(parseFloat);
                        Double.isNaN(d);
                        i2 = (int) (d * parseFloat);
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("₹ ");
                    stringBuilder.append(String.valueOf(i2));
                    ActivityEditingPlan.this.investAmt.setText(stringBuilder.toString());
                    d = (double) ((int) parseFloat);
                    int i3 = ActivityEditingPlan.this.flag == 0 ? ((int) parseFloat3) * 12 : (int) parseFloat3;
                    double d2 = 0.0d;
                    double d3 = d2;
                    while (i <= i3) {
                        SipBean sipBean;
                        if (i == 0) {
                            sipBean = new SipBean();
                            sipBean.setBalanceB("Start Balance");
                            sipBean.setInvestment("Investment");
                            sipBean.setInterst("Interest");
                            sipBean.setBalanceE("End Balance");
                            sipBean.setMonth("Month");
                            ActivityEditingPlan.arrayListAddPlan.add(sipBean);
                        } else {
                            double d4;
                            if (d != 0.0d) {
                                sipBean = new SipBean();
                                Double.isNaN(d);
                                d2 += d;
                                d4 = (double) parseFloat2;
                                Double.isNaN(d4);
                                d2 = (d2 * d4) / 100.0d;
                                double d5 = d2 / 12.0d;
                                Double.isNaN(d);
                                d3 += d;
                                d4 = (double) ((int) Math.round(d5));
                                Double.isNaN(d4);
                                d4 += d3;
                                Math.round(d5);
                                sipBean.setBalanceB(String.valueOf(Math.round(d4)));
                                sipBean.setInvestment(String.valueOf(Math.round(parseFloat)));
                                sipBean.setInterst(String.valueOf(Math.round(d2)));
                                sipBean.setBalanceE(String.valueOf(Math.round(d4)));
                                sipBean.setMonth(String.valueOf(Math.round((float) i)));
                                ActivityEditingPlan.arrayListAddPlan.add(sipBean);
                                d3 = d4;
                            } else {
                                d4 = d2;
                            }
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("₹ ");
                            stringBuilder2.append(String.valueOf(Math.round(d4)));
                            ActivityEditingPlan.this.maturityAmt.setText(stringBuilder2.toString());
                            d2 = d4;
                        }
                        i++;
                    }
                }
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void saveSharedPreferencesLogList(Context context, List<SipBean> list) {
        Editor edit = context.getSharedPreferences(this.TAG_PLAN_LIST, 0).edit();
        edit.putString("myJson", new Gson().toJson((Object) list));
        edit.commit();
    }

    public List<SipBean> loadSharedPreferencesLogList(Context context) {
        ArrayList arrayList = new ArrayList();
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.TAG_PLAN_LIST, 0);
        Gson gson = new Gson();
        String string = sharedPreferences.getString("myJson", "");
        if (string.isEmpty()) {
            return new ArrayList();
        }
        return (List) gson.fromJson(string, new TypeToken<List<SipBean>>() {
        }.getType());
    }

    public void loadBannerAd() {
        try {
            this.adView = new AdView((Context) this, getResources().getString(R.string.banner_ad_id), AdSize.BANNER_HEIGHT_50);
            final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.banner_container);
            linearLayout.setVisibility(8);
            linearLayout.addView(this.adView);
            this.adView.loadAd();
            this.adView.setAdListener(new AdListener() {
                public void onError(Ad ad, AdError adError) {
                    String str = ActivityEditingPlan.this.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("banner ad error.");
                    stringBuilder.append(adError.getErrorMessage());
                    Log.e(str, stringBuilder.toString());
                }

                public void onAdLoaded(Ad ad) {
                    Log.e(ActivityEditingPlan.this.TAG, "banner ad loaded.");
                    linearLayout.setVisibility(0);
                }

                public void onAdClicked(Ad ad) {
                    Log.e(ActivityEditingPlan.this.TAG, "banner ad clicked.");
                }

                public void onLoggingImpression(Ad ad) {
                    Log.e(ActivityEditingPlan.this.TAG, "banner ad onLoggingImpression.");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        AdView adView = this.adView;
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
