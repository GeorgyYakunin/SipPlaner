package com.example.planner.STP;

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
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.NativeBannerAdView;
import com.facebook.ads.NativeBannerAdView.Type;
import com.example.planner.R;
import com.example.planner.SipBean;
import java.util.ArrayList;

public class ActivityCalculatingSTP extends AppCompatActivity {
    public static ArrayList<SipBean> arrayList_transferee;
    public static ArrayList<SipBean> arrayList_transferor;
    public String TAG = ActivityCalculatingSTP.class.getSimpleName();
    AdView adView;
    TextView amount_transfered;
    TextView calcSip;
    TextView details;
    EditText initial_investment_amount;
    private NativeBannerAd nativeBannerAd;
    private LinearLayout native_banner;
    EditText rateReturn_transferee;
    EditText rateReturn_trasferor;
    TextView restSip;
    int selectFlag = 0;
    Spinner spinner;
    EditText stpAmount;
    TextView total_profit;
    TextView transderer_bal;
    TextView transferee_bal;
    EditText tunurePer;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.act_stp_calculating);
        this.native_banner = (LinearLayout) findViewById(R.id.native_banner);
        setTitle(getResources().getString(R.string.stp_calculator));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        loadBannerAd();
        nativeBannerAd();
        arrayList_transferor = new ArrayList();
        arrayList_transferee = new ArrayList();
        this.stpAmount = (EditText) findViewById(R.id.stpAmount);
        this.initial_investment_amount = (EditText) findViewById(R.id.initial_investment_amount);
        this.rateReturn_trasferor = (EditText) findViewById(R.id.rateReturn_trasferor);
        this.rateReturn_transferee = (EditText) findViewById(R.id.rateReturn_transferee);
        this.tunurePer = (EditText) findViewById(R.id.tunurePer);
        this.calcSip = (TextView) findViewById(R.id.calcSip);
        this.restSip = (TextView) findViewById(R.id.restSip);
        this.amount_transfered = (TextView) findViewById(R.id.amount_transfered);
        this.total_profit = (TextView) findViewById(R.id.total_profit);
        this.transderer_bal = (TextView) findViewById(R.id.transderer_bal);
        this.transferee_bal = (TextView) findViewById(R.id.transferee_bal);
        this.restSip.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivityCalculatingSTP.this.stpAmount.setText("");
                ActivityCalculatingSTP.this.initial_investment_amount.setText("");
                ActivityCalculatingSTP.this.rateReturn_transferee.setText("");
                ActivityCalculatingSTP.this.rateReturn_trasferor.setText("");
                ActivityCalculatingSTP.this.tunurePer.setText("");
                ActivityCalculatingSTP.this.amount_transfered.setText("00.00");
                ActivityCalculatingSTP.this.total_profit.setText("00.00");
                ActivityCalculatingSTP.this.transferee_bal.setText("00.00");
                ActivityCalculatingSTP.this.transderer_bal.setText("00.00");
                ActivityCalculatingSTP.arrayList_transferee.clear();
                ActivityCalculatingSTP.arrayList_transferor.clear();
                ActivityCalculatingSTP.this.spinner.setSelection(0);
            }
        });
        this.details = (TextView) findViewById(R.id.details);
        this.details.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                STPDetailFragment sTPDetailFragment = new STPDetailFragment();
                ActivityCalculatingSTP.this.getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, sTPDetailFragment, sTPDetailFragment.getClass().getSimpleName()).addToBackStack(null).commit();
            }
        });
        this.spinner = (Spinner) findViewById(R.id.tunure);
        this.spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ActivityCalculatingSTP.this.selectFlag = i;
            }
        });
        this.calcSip.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivityCalculatingSTP.arrayList_transferee.clear();
                ActivityCalculatingSTP.arrayList_transferor.clear();
                String obj = ActivityCalculatingSTP.this.stpAmount.getText().toString();
                String obj2 = ActivityCalculatingSTP.this.initial_investment_amount.getText().toString();
                String obj3 = ActivityCalculatingSTP.this.rateReturn_trasferor.getText().toString();
                String obj4 = ActivityCalculatingSTP.this.rateReturn_transferee.getText().toString();
                String obj5 = ActivityCalculatingSTP.this.tunurePer.getText().toString();
                int i = 0;
                if (TextUtils.isEmpty(obj)) {
                    Toast.makeText(ActivityCalculatingSTP.this.getApplicationContext(), ActivityCalculatingSTP.this.getResources().getString(R.string.plz_fill_info), 0).show();
                } else if (TextUtils.isEmpty(obj2)) {
                    Toast.makeText(ActivityCalculatingSTP.this.getApplicationContext(), ActivityCalculatingSTP.this.getResources().getString(R.string.plz_fill_info), 0).show();
                } else if (TextUtils.isEmpty(obj3)) {
                    Toast.makeText(ActivityCalculatingSTP.this.getApplicationContext(), ActivityCalculatingSTP.this.getResources().getString(R.string.plz_fill_info), 0).show();
                } else if (TextUtils.isEmpty(obj4)) {
                    Toast.makeText(ActivityCalculatingSTP.this.getApplicationContext(), ActivityCalculatingSTP.this.getResources().getString(R.string.plz_fill_info), 0).show();
                } else if (TextUtils.isEmpty(obj5)) {
                    Toast.makeText(ActivityCalculatingSTP.this.getApplicationContext(), ActivityCalculatingSTP.this.getResources().getString(R.string.plz_fill_info), 0).show();
                } else {
                    float parseFloat = Float.parseFloat(obj);
                    float parseFloat2 = Float.parseFloat(obj2);
                    float parseFloat3 = Float.parseFloat(obj3);
                    float parseFloat4 = Float.parseFloat(obj4);
                    float parseFloat5 = Float.parseFloat(obj5);
                    int i2 = ActivityCalculatingSTP.this.selectFlag == 0 ? ((int) parseFloat5) * 12 : (int) parseFloat5;
                    float f = parseFloat2;
                    parseFloat2 = 0.0f;
                    float f2 = 0.0f;
                    float f3 = 0.0f;
                    float f4 = 0.0f;
                    float f5 = 0.0f;
                    while (i <= i2) {
                        SipBean sipBean;
                        if (i == 0) {
                            sipBean = new SipBean();
                            sipBean.setBalanceB("Balance at Begin (₹)");
                            sipBean.setInvestment("Transferred In (₹)");
                            sipBean.setInterst("Interest Earned (₹)");
                            sipBean.setBalanceE("Balance at End (₹)");
                            sipBean.setMonth("Month");
                            ActivityCalculatingSTP.arrayList_transferee.add(sipBean);
                            sipBean = new SipBean();
                            sipBean.setBalanceB("Balance at Begin (₹)");
                            sipBean.setInvestment("Transferred Out (₹)");
                            sipBean.setInterst("Interest Earned (₹)");
                            sipBean.setBalanceE("Balance at End (₹)");
                            sipBean.setMonth("Month");
                            ActivityCalculatingSTP.arrayList_transferor.add(sipBean);
                        } else {
                            SipBean sipBean2 = new SipBean();
                            if (i > 1 && parseFloat >= f2) {
                                parseFloat = 0.0f;
                            }
                            f2 = ((f - parseFloat) * (parseFloat3 / 100.0f)) / 12.0f;
                            float round = (((float) Math.round(f2)) - parseFloat) + f;
                            sipBean2.setBalanceB(String.valueOf(Math.round(f)));
                            sipBean2.setInvestment(String.valueOf(Math.round(parseFloat)));
                            sipBean2.setInterst(String.valueOf(Math.round(f2)));
                            sipBean2.setBalanceE(String.valueOf(Math.round(round)));
                            sipBean2.setMonth(String.valueOf(i));
                            ActivityCalculatingSTP.arrayList_transferor.add(sipBean2);
                            f3 = f5 + parseFloat;
                            f = ((parseFloat4 / 100.0f) * f3) / 12.0f;
                            f3 += (float) Math.round(f);
                            sipBean = new SipBean();
                            sipBean.setBalanceB(String.valueOf(Math.round(f5)));
                            sipBean.setInvestment(String.valueOf(Math.round(parseFloat)));
                            sipBean.setInterst(String.valueOf(Math.round(f)));
                            sipBean.setBalanceE(String.valueOf(Math.round(f3)));
                            sipBean.setMonth(String.valueOf(i));
                            ActivityCalculatingSTP.arrayList_transferee.add(sipBean);
                            f4 += f + f2;
                            parseFloat2 += parseFloat;
                            f5 = f3;
                            f2 = round;
                            f = f2;
                        }
                        i++;
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("₹ ");
                    stringBuilder.append(String.valueOf(Math.round(parseFloat2)));
                    ActivityCalculatingSTP.this.amount_transfered.setText(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("₹ ");
                    stringBuilder.append(String.valueOf(Math.round(f2)));
                    ActivityCalculatingSTP.this.transderer_bal.setText(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("₹ ");
                    stringBuilder.append(String.valueOf(Math.round(f3)));
                    ActivityCalculatingSTP.this.transferee_bal.setText(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("₹ ");
                    stringBuilder.append(String.valueOf(Math.round(f4)));
                    ActivityCalculatingSTP.this.total_profit.setText(stringBuilder.toString());
                }
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
                    String str = ActivityCalculatingSTP.this.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("banner ad error.");
                    stringBuilder.append(adError.getErrorMessage());
                    Log.e(str, stringBuilder.toString());
                }

                public void onAdLoaded(Ad ad) {
                    Log.e(ActivityCalculatingSTP.this.TAG, "banner ad loaded.");
                    linearLayout.setVisibility(0);
                }

                public void onAdClicked(Ad ad) {
                    Log.e(ActivityCalculatingSTP.this.TAG, "banner ad clicked.");
                }

                public void onLoggingImpression(Ad ad) {
                    Log.e(ActivityCalculatingSTP.this.TAG, "banner ad onLoggingImpression.");
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
                if (ActivityCalculatingSTP.this.nativeBannerAd != null && ActivityCalculatingSTP.this.nativeBannerAd == ad) {
                    ActivityCalculatingSTP.this.native_banner.setVisibility(0);
                    ActivityCalculatingSTP sTPActivityCalculating = ActivityCalculatingSTP.this;
                    nativeAdLayout.addView(NativeBannerAdView.render(sTPActivityCalculating, sTPActivityCalculating.nativeBannerAd, Type.HEIGHT_100));
                }
            }
        });
        this.nativeBannerAd.loadAd();
    }
}
