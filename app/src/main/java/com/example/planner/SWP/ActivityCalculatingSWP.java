package com.example.planner.SWP;

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

public class ActivityCalculatingSWP extends AppCompatActivity {
    public static ArrayList<SipBean> arrayList;
    public String TAG = ActivityCalculatingSWP.class.getSimpleName();
    AdView adView;
    TextView calcSwp;
    TextView detail;
    TextView endblance;
    Spinner fequancy;
    private int flag;
    int frequencyFlag = 1;
    private NativeBannerAd nativeBannerAd;
    private LinearLayout native_banner;
    TextView noOFWidro;
    TextView profit;
    TextView restSwp;
    EditText swpAmount;
    EditText swpDuration;
    EditText swpInvestment;
    EditText swpRateReturn;
    Spinner swpTunure;
    TextView widro;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.act_swp_calculating);
        nativeBannerAd();
        setTitle(getResources().getString(R.string.swp_calculator));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        loadBannerAd();
        this.swpAmount = (EditText) findViewById(R.id.swpAmount);
        this.swpRateReturn = (EditText) findViewById(R.id.swpRateReturn);
        this.swpDuration = (EditText) findViewById(R.id.swpDuration);
        this.swpInvestment = (EditText) findViewById(R.id.swpInvestment);
        this.widro = (TextView) findViewById(R.id.widro);
        this.profit = (TextView) findViewById(R.id.profit);
        this.endblance = (TextView) findViewById(R.id.endblance);
        this.noOFWidro = (TextView) findViewById(R.id.noOFWidro);
        this.detail = (TextView) findViewById(R.id.detail);
        this.calcSwp = (TextView) findViewById(R.id.calcSwp);
        this.restSwp = (TextView) findViewById(R.id.restSwp);
        this.swpTunure = (Spinner) findViewById(R.id.swpTunure);
        this.fequancy = (Spinner) findViewById(R.id.fequancy);
        arrayList = new ArrayList();
        this.swpTunure.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    ActivityCalculatingSWP.this.flag = 0;
                } else if (i == 1) {
                    ActivityCalculatingSWP.this.flag = 1;
                }
            }
        });
        this.detail.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SWPDetailFragment sWPDetailFragment = new SWPDetailFragment();
                ActivityCalculatingSWP.this.getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, sWPDetailFragment, sWPDetailFragment.getClass().getSimpleName()).addToBackStack(null).commit();
            }
        });
        this.fequancy.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    ActivityCalculatingSWP.this.frequencyFlag = 1;
                } else if (i == 1) {
                    ActivityCalculatingSWP.this.frequencyFlag = 2;
                } else if (i == 2) {
                    ActivityCalculatingSWP.this.frequencyFlag = 3;
                } else if (i == 3) {
                    ActivityCalculatingSWP.this.frequencyFlag = 4;
                } else if (i == 4) {
                    ActivityCalculatingSWP.this.frequencyFlag = 5;
                }
            }
        });
        this.restSwp.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivityCalculatingSWP.this.swpAmount.setText("");
                ActivityCalculatingSWP.this.swpDuration.setText("");
                ActivityCalculatingSWP.this.swpInvestment.setText("");
                ActivityCalculatingSWP.this.swpRateReturn.setText("");
                ActivityCalculatingSWP.this.fequancy.setSelection(0);
                ActivityCalculatingSWP.this.swpTunure.setSelection(0);
                ActivityCalculatingSWP.this.noOFWidro.setText("00.00");
                ActivityCalculatingSWP.this.endblance.setText("00.00");
                ActivityCalculatingSWP.this.profit.setText("00.00");
                ActivityCalculatingSWP.this.widro.setText("00.00");
                ActivityCalculatingSWP.this.frequencyFlag = 1;
                ActivityCalculatingSWP.arrayList.clear();
            }
        });
        this.calcSwp.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivityCalculatingSWP.arrayList.clear();
                String obj = ActivityCalculatingSWP.this.swpAmount.getText().toString();
                String obj2 = ActivityCalculatingSWP.this.swpInvestment.getText().toString();
                String obj3 = ActivityCalculatingSWP.this.swpRateReturn.getText().toString();
                String obj4 = ActivityCalculatingSWP.this.swpDuration.getText().toString();
                if (TextUtils.isEmpty(obj)) {
                    Toast.makeText(ActivityCalculatingSWP.this.getApplicationContext(), ActivityCalculatingSWP.this.getResources().getString(R.string.plz_fill_info), 0).show();
                } else if (TextUtils.isEmpty(obj2)) {
                    Toast.makeText(ActivityCalculatingSWP.this.getApplicationContext(), ActivityCalculatingSWP.this.getResources().getString(R.string.plz_fill_info), 0).show();
                } else if (TextUtils.isEmpty(obj3)) {
                    Toast.makeText(ActivityCalculatingSWP.this.getApplicationContext(), ActivityCalculatingSWP.this.getResources().getString(R.string.plz_fill_info), 0).show();
                } else if (TextUtils.isEmpty(obj4)) {
                    Toast.makeText(ActivityCalculatingSWP.this.getApplicationContext(), ActivityCalculatingSWP.this.getResources().getString(R.string.plz_fill_info), 0).show();
                } else {
                    int i;
                    double parseDouble = Double.parseDouble(obj);
                    double parseDouble2 = Double.parseDouble(obj2);
                    double parseDouble3 = Double.parseDouble(obj3);
                    double parseDouble4 = Double.parseDouble(obj4);
                    double d = (double) ((int) parseDouble);
                    int i2 = ActivityCalculatingSWP.this.flag == 0 ? ((int) parseDouble4) * 12 : (int) parseDouble4;
                    double d2 = 12.0d;
                    double d3 = 100.0d;
                    double d4 = 0.0d;
                    double d5;
                    double d6;
                    int i3;
                    int i4;
                    double d7;
                    TextView textView;
                    if (ActivityCalculatingSWP.this.frequencyFlag == 1) {
                        d5 = 0.0d;
                        d6 = d5;
                        d4 = parseDouble2;
                        i3 = 0;
                        i4 = 0;
                        while (i3 <= i2) {
                            SipBean sipBean;
                            if (i3 == 0) {
                                sipBean = new SipBean();
                                sipBean.setBalanceB("Balance at Begin (₹)");
                                sipBean.setInvestment("Withdrawal (₹)");
                                sipBean.setInterst("Interest Earned (₹)");
                                sipBean.setBalanceE("Balance at End (₹)");
                                sipBean.setMonth("Month");
                                ActivityCalculatingSWP.arrayList.add(sipBean);
                            } else {
                                Double.isNaN(d);
                                d6 = d4 - d;
                                d7 = ((d6 * parseDouble3) / 100.0d) / d2;
                                d2 = (double) Math.round(d7);
                                Double.isNaN(d2);
                                d6 += d2;
                                sipBean = new SipBean();
                                sipBean.setBalanceB(String.valueOf(Math.round(d4)));
                                sipBean.setInvestment(String.valueOf(Math.round(parseDouble)));
                                sipBean.setInterst(String.valueOf(Math.round(d7)));
                                sipBean.setBalanceE(String.valueOf(Math.round(d6)));
                                sipBean.setMonth(String.valueOf(i3));
                                ActivityCalculatingSWP.arrayList.add(sipBean);
                                i4 = (int) (((long) i4) + Math.round(d7));
                                Double.isNaN(d);
                                d5 += d;
                                textView = ActivityCalculatingSWP.this.noOFWidro;
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("");
                                stringBuilder.append(i3);
                                textView.setText(stringBuilder.toString());
                                if (d6 < d) {
                                    i = i4;
                                    d4 = d5;
                                    break;
                                }
                                d4 = d6;
                            }
                            i3++;
                            d2 = 12.0d;
                        }
                        i = i4;
                        d4 = d5;
                        d3 = d6;
                    } else {
                        int i5 = 3;
                        double d8;
                        int i6;
                        SipBean sipBean2;
                        int i7;
                        double d9;
                        double round;
                        StringBuilder stringBuilder2;
                        SipBean sipBean3;
                        if (ActivityCalculatingSWP.this.frequencyFlag == 2) {
                            d6 = parseDouble2;
                            d5 = 0.0d;
                            d7 = d5;
                            d8 = d7;
                            i3 = 0;
                            i4 = 0;
                            i6 = 0;
                            while (i3 <= i2) {
                                if (i3 == 0) {
                                    sipBean2 = new SipBean();
                                    sipBean2.setBalanceB("Balance at Begin (₹)");
                                    sipBean2.setInvestment("Withdrawal (₹)");
                                    sipBean2.setInterst("Interest Earned (₹)");
                                    sipBean2.setBalanceE("Balance at End (₹)");
                                    sipBean2.setMonth("Month");
                                    ActivityCalculatingSWP.arrayList.add(sipBean2);
                                    i7 = i2;
                                    d9 = parseDouble3;
                                } else {
                                    i = (i3 - 2) % i5;
                                    if (d5 != 0.0d) {
                                        d5 = 0.0d;
                                    }
                                    if (i == 0) {
                                        i4++;
                                        d5 = d;
                                    }
                                    d2 = d6 - d5;
                                    d3 = ((d2 * parseDouble3) / d3) / 12.0d;
                                    d9 = parseDouble3;
                                    round = (double) Math.round(d3);
                                    Double.isNaN(round);
                                    round += d2;
                                    i7 = i2;
                                    StringBuilder stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append(d2);
                                    stringBuilder3.append("");
                                    Log.e("begin bal::", stringBuilder3.toString());
                                    stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append(parseDouble);
                                    stringBuilder2.append("");
                                    Log.e("Withdrawal::", stringBuilder2.toString());
                                    stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append(d3);
                                    stringBuilder2.append("");
                                    Log.e("Interest Earned::", stringBuilder2.toString());
                                    stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append(round);
                                    stringBuilder2.append("");
                                    Log.e("end_bal::", stringBuilder2.toString());
                                    sipBean3 = new SipBean();
                                    sipBean3.setBalanceB(String.valueOf(Math.round(d6)));
                                    sipBean3.setInvestment(String.valueOf(Math.round(parseDouble)));
                                    sipBean3.setInterst(String.valueOf(Math.round(d3)));
                                    sipBean3.setBalanceE(String.valueOf(Math.round(round)));
                                    sipBean3.setMonth(String.valueOf(i3));
                                    ActivityCalculatingSWP.arrayList.add(sipBean3);
                                    i2 = (int) (((long) i6) + Math.round(d3));
                                    d7 += d5;
                                    textView = ActivityCalculatingSWP.this.noOFWidro;
                                    stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append("");
                                    stringBuilder2.append(i4);
                                    textView.setText(stringBuilder2.toString());
                                    if (round < d) {
                                        d8 = round;
                                        d4 = d7;
                                        i = i2;
                                        break;
                                    }
                                    i6 = i2;
                                    d6 = round;
                                    d8 = d6;
                                }
                                i3++;
                                parseDouble3 = d9;
                                i2 = i7;
                                i5 = 3;
                                d3 = 100.0d;
                            }
                            i = i6;
                            d4 = d7;
                            d3 = d8;
                        } else {
                            i7 = i2;
                            d9 = parseDouble3;
                            double round2;
                            if (ActivityCalculatingSWP.this.frequencyFlag == 3) {
                                d2 = parseDouble2;
                                round = 0.0d;
                                d3 = round;
                                d5 = d3;
                                i2 = i7;
                                i3 = 0;
                                i4 = 0;
                                i6 = 0;
                                while (i3 <= i2) {
                                    if (i3 == 0) {
                                        SipBean sipBean4 = new SipBean();
                                        i7 = i2;
                                        sipBean4.setBalanceB("Balance at Begin (₹)");
                                        sipBean4.setInvestment("Withdrawal (₹)");
                                        sipBean4.setInterst("Interest Earned (₹)");
                                        sipBean4.setBalanceE("Balance at End (₹)");
                                        sipBean4.setMonth("Month");
                                        ActivityCalculatingSWP.arrayList.add(sipBean4);
                                        d7 = d;
                                    } else {
                                        i7 = i2;
                                        i2 = i3 - 5;
                                        int i8 = i2 % 3;
                                        if (round != 0.0d) {
                                            round = 0.0d;
                                        }
                                        if (i2 % 6 == 0) {
                                            i4++;
                                            round = d;
                                        }
                                        d5 = d2 - round;
                                        d6 = ((d5 * d9) / 100.0d) / 12.0d;
                                        d7 = d;
                                        round2 = (double) Math.round(d6);
                                        Double.isNaN(round2);
                                        d5 += round2;
                                        sipBean3 = new SipBean();
                                        sipBean3.setBalanceB(String.valueOf(Math.round(d2)));
                                        sipBean3.setInvestment(String.valueOf(Math.round(parseDouble)));
                                        sipBean3.setInterst(String.valueOf(Math.round(d6)));
                                        sipBean3.setBalanceE(String.valueOf(Math.round(d5)));
                                        sipBean3.setMonth(String.valueOf(i3));
                                        ActivityCalculatingSWP.arrayList.add(sipBean3);
                                        i2 = (int) (((long) i6) + Math.round(d6));
                                        d3 += round;
                                        textView = ActivityCalculatingSWP.this.noOFWidro;
                                        stringBuilder2 = new StringBuilder();
                                        stringBuilder2.append("");
                                        stringBuilder2.append(i4);
                                        textView.setText(stringBuilder2.toString());
                                        if (d5 < d7) {
                                            i = i2;
                                            d4 = d3;
                                            break;
                                        }
                                        i6 = i2;
                                        d2 = d5;
                                    }
                                    i3++;
                                    d = d7;
                                    i2 = i7;
                                }
                                i = i6;
                                d4 = d3;
                                d3 = d5;
                            } else {
                                d7 = d;
                                if (ActivityCalculatingSWP.this.frequencyFlag == 4) {
                                    d = parseDouble2;
                                    parseDouble3 = 0.0d;
                                    d2 = parseDouble3;
                                    d3 = d2;
                                    i2 = i7;
                                    i3 = 0;
                                    i4 = 0;
                                    i6 = 0;
                                    while (i3 <= i2) {
                                        if (i3 == 0) {
                                            sipBean2 = new SipBean();
                                            i7 = i2;
                                            sipBean2.setBalanceB("Balance at Begin (₹)");
                                            sipBean2.setInvestment("Withdrawal (₹)");
                                            sipBean2.setInterst("Interest Earned (₹)");
                                            sipBean2.setBalanceE("Balance at End (₹)");
                                            sipBean2.setMonth("Month");
                                            ActivityCalculatingSWP.arrayList.add(sipBean2);
                                        } else {
                                            i7 = i2;
                                            i2 = (i3 - 5) % 3;
                                            if (parseDouble3 != 0.0d) {
                                                parseDouble3 = 0.0d;
                                            }
                                            if ((i3 - 11) % 12 == 0) {
                                                i4++;
                                                parseDouble3 = d7;
                                            }
                                            d3 = d - parseDouble3;
                                            d5 = ((d3 * d9) / 100.0d) / 12.0d;
                                            d8 = parseDouble3;
                                            round = (double) Math.round(d5);
                                            Double.isNaN(round);
                                            round += d3;
                                            sipBean3 = new SipBean();
                                            sipBean3.setBalanceB(String.valueOf(Math.round(d)));
                                            sipBean3.setInvestment(String.valueOf(Math.round(parseDouble)));
                                            sipBean3.setInterst(String.valueOf(Math.round(d5)));
                                            sipBean3.setBalanceE(String.valueOf(Math.round(round)));
                                            sipBean3.setMonth(String.valueOf(i3));
                                            ActivityCalculatingSWP.arrayList.add(sipBean3);
                                            i2 = (int) (((long) i6) + Math.round(d5));
                                            round2 = d2 + d8;
                                            textView = ActivityCalculatingSWP.this.noOFWidro;
                                            StringBuilder stringBuilder4 = new StringBuilder();
                                            stringBuilder4.append("");
                                            stringBuilder4.append(i4);
                                            textView.setText(stringBuilder4.toString());
                                            if (round < d7) {
                                                d3 = round;
                                                d4 = round2;
                                                i = i2;
                                                break;
                                            }
                                            i6 = i2;
                                            d3 = round;
                                            d2 = round2;
                                            d = d3;
                                            parseDouble3 = d8;
                                        }
                                        i3++;
                                        i2 = i7;
                                    }
                                    i = i6;
                                    d4 = d2;
                                } else {
                                    d3 = 0.0d;
                                    i = 0;
                                }
                            }
                        }
                    }
                    TextView textView2 = ActivityCalculatingSWP.this.endblance;
                    StringBuilder stringBuilder5 = new StringBuilder();
                    stringBuilder5.append("");
                    stringBuilder5.append(Math.round(d3));
                    textView2.setText(stringBuilder5.toString());
                    textView2 = ActivityCalculatingSWP.this.profit;
                    stringBuilder5 = new StringBuilder();
                    stringBuilder5.append("");
                    stringBuilder5.append(Math.round((float) i));
                    textView2.setText(stringBuilder5.toString());
                    textView2 = ActivityCalculatingSWP.this.widro;
                    stringBuilder5 = new StringBuilder();
                    stringBuilder5.append("");
                    stringBuilder5.append(Math.round(d4));
                    textView2.setText(stringBuilder5.toString());
                }
            }
        });
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
                if (ActivityCalculatingSWP.this.nativeBannerAd != null && ActivityCalculatingSWP.this.nativeBannerAd == ad) {
                    ActivityCalculatingSWP.this.native_banner.setVisibility(0);
                    ActivityCalculatingSWP sWPCalcActivityCalculatingSWP = ActivityCalculatingSWP.this;
                    nativeAdLayout.addView(NativeBannerAdView.render(sWPCalcActivityCalculatingSWP, sWPCalcActivityCalculatingSWP.nativeBannerAd, Type.HEIGHT_100));
                }
            }
        });
        this.nativeBannerAd.loadAd();
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
                    String str = ActivityCalculatingSWP.this.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("banner ad error.");
                    stringBuilder.append(adError.getErrorMessage());
                    Log.e(str, stringBuilder.toString());
                }

                public void onAdLoaded(Ad ad) {
                    Log.e(ActivityCalculatingSWP.this.TAG, "banner ad loaded.");
                    linearLayout.setVisibility(0);
                }

                public void onAdClicked(Ad ad) {
                    Log.e(ActivityCalculatingSWP.this.TAG, "banner ad clicked.");
                }

                public void onLoggingImpression(Ad ad) {
                    Log.e(ActivityCalculatingSWP.this.TAG, "banner ad onLoggingImpression.");
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
