package com.example.planner.SIP;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.NativeBannerAdView;
import com.facebook.ads.NativeBannerAdView.Type;
import com.example.planner.MyApplication;
import com.example.planner.R;
import com.example.planner.SipBean;
import java.util.ArrayList;

public class ActivitySipCalculation extends AppCompatActivity {
    public static ArrayList<SipBean> arrayListSIP;
    public String TAG = ActivitySipCalculation.class.getSimpleName();
    AdView adView;
    MyApplication application;
    TextView calcSip;
    TextView detail;
    private int flag;
    private InterstitialAd interstitialAd;
    TextView investAmt;
    EditText lumSum;
    TextView maturityAmt;
    private NativeBannerAd nativeBannerAd;
    private LinearLayout native_banner;
    EditText rateReturn;
    TextView restSip;
    EditText sipAmount;
    Spinner tnuree;
    EditText tunurePer;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.act_sip_calculating);
        this.native_banner = (LinearLayout) findViewById(R.id.native_banner);
        this.application = MyApplication.getInstance();
        setTitle(getResources().getString(R.string.sip_calculator));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        loadBannerAd();
        nativeBannerAd();
        this.sipAmount = (EditText) findViewById(R.id.sipAmount);
        this.rateReturn = (EditText) findViewById(R.id.rateReturn);
        this.tunurePer = (EditText) findViewById(R.id.tunurePer);
        this.lumSum = (EditText) findViewById(R.id.lumSum);
        this.investAmt = (TextView) findViewById(R.id.investAmt);
        this.maturityAmt = (TextView) findViewById(R.id.maturityAmt);
        this.calcSip = (TextView) findViewById(R.id.calcSip);
        this.restSip = (TextView) findViewById(R.id.restSip);
        this.detail = (TextView) findViewById(R.id.detail);
        this.tnuree = (Spinner) findViewById(R.id.tunure);
        this.tnuree.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    ActivitySipCalculation.this.flag = 0;
                } else if (i == 1) {
                    ActivitySipCalculation.this.flag = 1;
                }
            }
        });
        arrayListSIP = new ArrayList();
        this.restSip.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivitySipCalculation.this.sipAmount.setText("");
                ActivitySipCalculation.this.lumSum.setText("");
                ActivitySipCalculation.this.rateReturn.setText("");
                ActivitySipCalculation.this.tunurePer.setText("");
                ActivitySipCalculation.this.tnuree.setSelection(0);
                ActivitySipCalculation.arrayListSIP.clear();
                ActivitySipCalculation.this.investAmt.setText("00.00");
                ActivitySipCalculation.this.maturityAmt.setText("00.00");
            }
        });
        this.detail.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SIPDetailFragment sIPDetailFragment = new SIPDetailFragment();
                SIPDetailFragment.arrayList = ActivitySipCalculation.arrayListSIP;
                ActivitySipCalculation.this.getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, sIPDetailFragment, sIPDetailFragment.getClass().getSimpleName()).addToBackStack(null).commit();
            }
        });
        this.calcSip.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivitySipCalculation.arrayListSIP.clear();
                String obj = ActivitySipCalculation.this.sipAmount.getText().toString();
                String obj2 = ActivitySipCalculation.this.lumSum.getText().toString();
                String obj3 = ActivitySipCalculation.this.rateReturn.getText().toString();
                String obj4 = ActivitySipCalculation.this.tunurePer.getText().toString();
                int i = 0;
                if (TextUtils.isEmpty(obj)) {
                    Toast.makeText(ActivitySipCalculation.this.getApplicationContext(), ActivitySipCalculation.this.getResources().getString(R.string.plz_fill_info), Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(obj3)) {
                    Toast.makeText(ActivitySipCalculation.this.getApplicationContext(), ActivitySipCalculation.this.getResources().getString(R.string.plz_fill_info), Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(obj4)) {
                    Toast.makeText(ActivitySipCalculation.this.getApplicationContext(), ActivitySipCalculation.this.getResources().getString(R.string.plz_fill_info), Toast.LENGTH_SHORT).show();
                } else {
                    double d;
                    double d2;
                    int i2;
                    double parseFloat = (double) Float.parseFloat(obj);
                    double d3 = 0.0d;
                    if (TextUtils.isEmpty(obj2)) {
                        d = 0.0d;
                    } else {
                        d = (double) Float.parseFloat(obj2);
                    }
                    float parseFloat2 = Float.parseFloat(obj3);
                    float parseFloat3 = Float.parseFloat(obj4);
                    if (ActivitySipCalculation.this.flag == 0) {
                        d2 = (double) parseFloat3;
                        Double.isNaN(parseFloat);
                        Double.isNaN(d2);
                        d2 = (double) ((int) ((d2 * parseFloat) * 12.0d));
                        Double.isNaN(d2);
                        i2 = (int) (d2 + d);
                    } else {
                        d2 = (double) parseFloat3;
                        Double.isNaN(parseFloat);
                        Double.isNaN(d2);
                        d2 = (double) ((int) (d2 * parseFloat));
                        Double.isNaN(d2);
                        i2 = (int) (d2 + d);
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("₹ ");
                    stringBuilder.append(String.valueOf(i2));
                    ActivitySipCalculation.this.investAmt.setText(stringBuilder.toString());
                    parseFloat = (double) ((int) parseFloat);
                    Double.isNaN(parseFloat);
                    d += parseFloat;
                    int i3 = ActivitySipCalculation.this.flag == 0 ? ((int) parseFloat3) * 12 : (int) parseFloat3;
                    d2 = 0.0d;
                    double d4 = d2;
                    while (i <= i3) {
                        double d5;
                        SipBean sipBean;
                        if (i == 0) {
                            sipBean = new SipBean();
                            sipBean.setBalanceB("Start Balance");
                            sipBean.setInvestment("Investment");
                            sipBean.setInterst("Interest");
                            sipBean.setBalanceE("End Balance");
                            sipBean.setMonth("Month");
                            ActivitySipCalculation.arrayListSIP.add(sipBean);
                            d5 = d3;
                        } else {
                            if (parseFloat != d3) {
                                sipBean = new SipBean();
                                if (i == 1) {
                                    sipBean.setInvestment(String.valueOf(Math.round(d)));
                                    d2 += d;
                                    d3 = (double) parseFloat2;
                                    Double.isNaN(d3);
                                    d2 = (d2 * d3) / 100.0d;
                                    d3 = 12.0d;
                                } else {
                                    sipBean.setInvestment(String.valueOf(Math.round(parseFloat)));
                                    Double.isNaN(parseFloat);
                                    d2 += parseFloat;
                                    d3 = (double) parseFloat2;
                                    Double.isNaN(d3);
                                    d2 = (d2 * d3) / 100.0d;
                                    d3 = 12.0d;
                                }
                                d2 /= d3;
                                if (i == 1) {
                                    d5 = d4 + d;
                                    d3 = (double) ((int) Math.round(d2));
                                    Double.isNaN(d3);
                                    d3 = d5 + d3;
                                } else {
                                    Double.isNaN(parseFloat);
                                    d3 = d4 + parseFloat;
                                    d5 = (double) ((int) Math.round(d2));
                                    Double.isNaN(d5);
                                    d3 += d5;
                                }
                                sipBean.setBalanceB(String.valueOf(Math.round(d4)));
                                Math.round(d2);
                                sipBean.setInterst(String.valueOf(Math.round(d2)));
                                sipBean.setBalanceE(String.valueOf(Math.round(d3)));
                                sipBean.setMonth(String.valueOf(i));
                                ActivitySipCalculation.arrayListSIP.add(sipBean);
                                d4 = d3;
                            } else {
                                d3 = d2;
                            }
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("₹ ");
                            d5 = 0.0d;
                            stringBuilder2.append(String.valueOf(Math.round(d3 + 0.0d)));
                            ActivitySipCalculation.this.maturityAmt.setText(stringBuilder2.toString());
                            d2 = d3;
                        }
                        i++;
                        d3 = d5;
                    }
                }
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        if (this.application.adCount == 0) {
            this.application.adCount = 1;
            showInterstitial();
            return;
        }
        super.onBackPressed();
    }

    public void showInterstitial() {
        try {
            this.interstitialAd = new InterstitialAd(getApplicationContext(), "325150171489288_325150758155896");
            this.interstitialAd.setAdListener(new InterstitialAdListener() {
                public void onInterstitialDisplayed(Ad ad) {
                    Log.e(ActivitySipCalculation.this.TAG, "Interstitial ad displayed.");
                }

                public void onInterstitialDismissed(Ad ad) {
                    Log.e(ActivitySipCalculation.this.TAG, "Interstitial ad dismissed.");
                    onBackPressed();
                }

                public void onError(Ad ad, AdError adError) {
                    String str = ActivitySipCalculation.this.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Interstitial ad failed to load: ");
                    stringBuilder.append(adError.getErrorMessage());
                    Log.e(str, stringBuilder.toString());
                     onBackPressed();
                }

                public void onAdLoaded(Ad ad) {
                    Log.d(ActivitySipCalculation.this.TAG, "Interstitial ad is loaded and ready to be displayed!");
                    ActivitySipCalculation.this.interstitialAd.show();
                }

                public void onAdClicked(Ad ad) {
                    Log.d(ActivitySipCalculation.this.TAG, "Interstitial ad clicked!");
                }

                public void onLoggingImpression(Ad ad) {
                    Log.d(ActivitySipCalculation.this.TAG, "Interstitial ad impression logged!");
                }
            });
            this.interstitialAd.loadAd();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    String str = ActivitySipCalculation.this.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("banner ad error.");
                    stringBuilder.append(adError.getErrorMessage());
                    Log.e(str, stringBuilder.toString());
                }

                public void onAdLoaded(Ad ad) {
                    Log.e(ActivitySipCalculation.this.TAG, "banner ad loaded.");
                    linearLayout.setVisibility(0);
                }

                public void onAdClicked(Ad ad) {
                    Log.e(ActivitySipCalculation.this.TAG, "banner ad clicked.");
                }

                public void onLoggingImpression(Ad ad) {
                    Log.e(ActivitySipCalculation.this.TAG, "banner ad onLoggingImpression.");
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

    private void nativeBannerAd() {
        this.nativeBannerAd = new NativeBannerAd((Context) this, getResources().getString(R.string.native_banner_id));
        final NativeAdLayout nativeAdLayout = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
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
                if (ActivitySipCalculation.this.nativeBannerAd != null && ActivitySipCalculation.this.nativeBannerAd == ad) {
                    ActivitySipCalculation.this.native_banner.setVisibility(0);
                    ActivitySipCalculation activitySipCalculation = ActivitySipCalculation.this;
                    nativeAdLayout.addView(NativeBannerAdView.render(activitySipCalculation, activitySipCalculation.nativeBannerAd, Type.HEIGHT_100));
                }
            }
        });
        this.nativeBannerAd.loadAd();
    }
}
