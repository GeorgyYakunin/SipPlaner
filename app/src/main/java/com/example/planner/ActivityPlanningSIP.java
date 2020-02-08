package com.example.planner;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.NativeBannerAdView;
import com.facebook.ads.NativeBannerAdView.Type;
import com.example.planner.SIP.SIPDetailFragment;
import java.util.ArrayList;

public class ActivityPlanningSIP extends AppCompatActivity {
    public static ArrayList<SipBean> arrayList;
    public static ArrayList<SipBean> arrayListSIP;
    public String TAG = ActivityPlanningSIP.class.getSimpleName();
    AdView adView;
    private SipAdapter adapter;
    TextView calcSP;
    TextView detail;
    private int flag;
    ListView list;
    private NativeBannerAd nativeBannerAd;
    private LinearLayout native_banner;
    TextView profit;
    TextView restSP;
    EditText spAmount;
    EditText spDuration;
    EditText spRateReturn;
    Spinner tnuree;
    TextView widro;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.act_sip_planning);
        this.spAmount = (EditText) findViewById(R.id.spAmount);
        this.spRateReturn = (EditText) findViewById(R.id.spRateReturn);
        this.spDuration = (EditText) findViewById(R.id.spDuration);
        this.calcSP = (TextView) findViewById(R.id.calcSP);
        this.restSP = (TextView) findViewById(R.id.restSP);
        this.detail = (TextView) findViewById(R.id.detail);
        this.profit = (TextView) findViewById(R.id.profit);
        loadBannerAd();
        nativeBannerAd();
        this.widro = (TextView) findViewById(R.id.widro);
        setTitle(getResources().getString(R.string.sip_planner));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        arrayListSIP = new ArrayList();
        this.restSP.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivityPlanningSIP.this.spAmount.setText("");
                ActivityPlanningSIP.this.spRateReturn.setText("");
                ActivityPlanningSIP.this.spDuration.setText("");
                ActivityPlanningSIP.this.widro.setText("00.00");
                ActivityPlanningSIP.this.profit.setText("00.00");
                ActivityPlanningSIP.arrayListSIP.clear();
            }
        });
        this.detail.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SIPDetailFragment sIPDetailFragment = new SIPDetailFragment();
                SIPDetailFragment.arrayList = ActivityPlanningSIP.arrayListSIP;
                ActivityPlanningSIP.this.getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, sIPDetailFragment, sIPDetailFragment.getClass().getSimpleName()).addToBackStack(null).commit();
            }
        });
        this.calcSP.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivityPlanningSIP.arrayListSIP.clear();
                String obj = ActivityPlanningSIP.this.spAmount.getText().toString();
                String obj2 = ActivityPlanningSIP.this.spRateReturn.getText().toString();
                String obj3 = ActivityPlanningSIP.this.spDuration.getText().toString();
                int i = 0;
                if (TextUtils.isEmpty(obj)) {
                    Toast.makeText(ActivityPlanningSIP.this.getApplicationContext(), ActivityPlanningSIP.this.getResources().getString(R.string.plz_fill_info), 0).show();
                } else if (TextUtils.isEmpty(obj2)) {
                    Toast.makeText(ActivityPlanningSIP.this.getApplicationContext(), ActivityPlanningSIP.this.getResources().getString(R.string.plz_fill_info), 0).show();
                } else if (TextUtils.isEmpty(obj3)) {
                    Toast.makeText(ActivityPlanningSIP.this.getApplicationContext(), ActivityPlanningSIP.this.getResources().getString(R.string.plz_fill_info), 0).show();
                } else {
                    double parseFloat = (double) Float.parseFloat(obj);
                    float parseFloat2 = Float.parseFloat(obj2);
                    int parseFloat3 = ((int) Float.parseFloat(obj3)) * 12;
                    parseFloat2 = (parseFloat2 / 100.0f) / 12.0f;
                    float f = parseFloat2 + 1.0f;
                    float pow = ((float) ((int) parseFloat)) / (((((float) Math.pow((double) f, (double) parseFloat3)) - 1.0f) / parseFloat2) * f);
                    float f2 = 0.0f;
                    f = 0.0f;
                    while (i <= parseFloat3) {
                        if (i == 0) {
                            SipBean sipBean = new SipBean();
                            sipBean.setBalanceB("Start Balance");
                            sipBean.setInvestment("Investment");
                            sipBean.setInterst("Interest");
                            sipBean.setBalanceE("End Balance");
                            sipBean.setMonth("Month");
                            ActivityPlanningSIP.arrayListSIP.add(sipBean);
                        } else {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(f);
                            stringBuilder.append("");
                            Log.e("start_bal::", stringBuilder.toString());
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(pow);
                            stringBuilder.append("");
                            Log.e("investment::", stringBuilder.toString());
                            float f3 = f + pow;
                            float f4 = f3 * parseFloat2;
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(f4);
                            stringBuilder2.append("");
                            Log.e("interest::", stringBuilder2.toString());
                            f3 += f4;
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(f3);
                            stringBuilder2.append("");
                            Log.e("end_bal::", stringBuilder2.toString());
                            SipBean sipBean2 = new SipBean();
                            sipBean2.setBalanceB(String.valueOf(Math.round(f)));
                            sipBean2.setInvestment(String.valueOf(Math.round(pow)));
                            sipBean2.setInterst(String.valueOf(Math.round(f4)));
                            sipBean2.setBalanceE(String.valueOf(Math.round(f3)));
                            sipBean2.setMonth(String.valueOf(Math.round((float) i)));
                            ActivityPlanningSIP.arrayListSIP.add(sipBean2);
                            f2 += pow;
                            f = f3;
                        }
                        i++;
                    }
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(f2);
                    stringBuilder3.append("");
                    Log.e("total_investment::", stringBuilder3.toString());
                    TextView textView = ActivityPlanningSIP.this.widro;
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("₹ ");
                    stringBuilder3.append(Math.round(f2));
                    textView.setText(stringBuilder3.toString());
                    textView = ActivityPlanningSIP.this.profit;
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("₹ ");
                    stringBuilder3.append(Math.round(pow));
                    textView.setText(stringBuilder3.toString());
                }
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void nativeBannerAd() {
        this.nativeBannerAd = new NativeBannerAd((Context) this, getResources().getString(R.string.native_banner_id));
        final NativeAdLayout nativeAdLayout = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
        this.native_banner = (LinearLayout) findViewById(R.id.native_banner);
        this.native_banner.setVisibility(8);
        this.nativeBannerAd.setAdListener(new NativeAdListener() {
            public void onAdClicked(Ad ad) {
            }

            public void onError(Ad ad, AdError adError) {
            }

            public void onLoggingImpression(Ad ad) {
            }

            public void onMediaDownloaded(Ad ad) {
            }

            public void onAdLoaded(Ad ad) {
                if (ActivityPlanningSIP.this.nativeBannerAd != null && ActivityPlanningSIP.this.nativeBannerAd == ad) {
                    ActivityPlanningSIP.this.native_banner.setVisibility(0);
                    ActivityPlanningSIP sIPPlannerActivityPlanningSIP = ActivityPlanningSIP.this;
                    nativeAdLayout.addView(NativeBannerAdView.render(sIPPlannerActivityPlanningSIP, sIPPlannerActivityPlanningSIP.nativeBannerAd, Type.HEIGHT_100));
                }
            }
        });
        this.nativeBannerAd.loadAd();
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
                    String str = ActivityPlanningSIP.this.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("banner ad error.");
                    stringBuilder.append(adError.getErrorMessage());
                    Log.e(str, stringBuilder.toString());
                }

                public void onAdLoaded(Ad ad) {
                    Log.e(ActivityPlanningSIP.this.TAG, "banner ad loaded.");
                    linearLayout.setVisibility(0);
                }

                public void onAdClicked(Ad ad) {
                    Log.e(ActivityPlanningSIP.this.TAG, "banner ad clicked.");
                }

                public void onLoggingImpression(Ad ad) {
                    Log.e(ActivityPlanningSIP.this.TAG, "banner ad onLoggingImpression.");
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
