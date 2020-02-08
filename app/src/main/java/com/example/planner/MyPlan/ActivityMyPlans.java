package com.example.planner.MyPlan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.planner.R;
import com.example.planner.SipBean;
import java.util.ArrayList;
import java.util.List;

public class ActivityMyPlans extends AppCompatActivity {
    public String TAG = ActivityMyPlans.class.getSimpleName();
    String TAG_PLAN_LIST = "PlanList";
    AdView adView;
    RecyclerView bestRecyclerView;
    MyAdapter mAdapter;
    private NativeBannerAd nativeBannerAd;
    private LinearLayout native_banner;
    LinearLayout no_data;
    List<SipBean> planList;

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private final String TAG = MyAdapter.class.getSimpleName();
        private Context context;
        private List<SipBean> productList;

        public MyAdapter(Context context, List<SipBean> list) {
            this.context = context;
            this.productList = list;
        }

        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_of_my_plans, viewGroup, false));
        }

        public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
            final SipBean sipBean = (SipBean) this.productList.get(i);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(sipBean.getId());
            stringBuilder.append("");
            Log.e("plan id::", stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append(sipBean.getName());
            stringBuilder.append("");
            Log.e("plan name::", stringBuilder.toString());
            myViewHolder.name.setText(sipBean.getName());
            myViewHolder.mnthly_investment.setText(sipBean.getMnthly_investment());
            myViewHolder.rateReturn.setText(sipBean.getRate_of_return());
            myViewHolder.tunurePer.setText(sipBean.getYear());
            myViewHolder.strt_dt.setText(sipBean.getStart_date());
            myViewHolder.end_dt.setText(sipBean.getEnd_date());
            myViewHolder.investAmt.setText(sipBean.getInvestment());
            myViewHolder.maturityAmt.setText(sipBean.getBalanceE());
            myViewHolder.delete.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MyAdapter.this.productList.remove(sipBean);
                    ActivityMyPlans.this.saveSharedPreferencesLogList(ActivityMyPlans.this.getApplicationContext(), MyAdapter.this.productList);
                    MyAdapter.this.notifyDataSetChanged();
                    if (MyAdapter.this.productList.size() == 0) {
                        ActivityMyPlans.this.no_data.setVisibility(0);
                    }
                }
            });
            myViewHolder.edit_unit.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(ActivityMyPlans.this, ActivityEditingPlan.class);
                    ActivityEditingPlan.mySipBean = sipBean;
                    ActivityMyPlans.this.startActivity(intent);
                }
            });
        }

        public int getItemCount() {
            return this.productList.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView delete;
        TextView edit_unit;
        TextView end_dt;
        TextView investAmt;
        TextView maturityAmt;
        TextView mnthly_investment;
        TextView name;
        TextView rateReturn;
        TextView strt_dt;
        TextView tunurePer;

        MyViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.name);
            this.mnthly_investment = (TextView) view.findViewById(R.id.mnthly_investment);
            this.rateReturn = (TextView) view.findViewById(R.id.rateReturn);
            this.tunurePer = (TextView) view.findViewById(R.id.tunurePer);
            this.investAmt = (TextView) view.findViewById(R.id.investAmt);
            this.maturityAmt = (TextView) view.findViewById(R.id.maturityAmt);
            this.strt_dt = (TextView) view.findViewById(R.id.strt_dt);
            this.end_dt = (TextView) view.findViewById(R.id.end_dt);
            this.delete = (TextView) view.findViewById(R.id.delete);
            this.edit_unit = (TextView) view.findViewById(R.id.edit);
        }
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.act_my_plans);
        this.native_banner = (LinearLayout) findViewById(R.id.native_banner);
        setTitle(getResources().getString(R.string.view_investment));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        loadBannerAd();
        nativeBannerAd();
        this.bestRecyclerView = (RecyclerView) findViewById(R.id.product_list);
        this.bestRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        this.bestRecyclerView.setHasFixedSize(true);
        this.bestRecyclerView.setNestedScrollingEnabled(false);
        this.no_data = (LinearLayout) findViewById(R.id.no_data);
    }


    public void onResume() {
        super.onResume();
        this.planList = loadSharedPreferencesLogList(getApplicationContext());
        if (this.planList.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("planlist size::");
            stringBuilder.append(this.planList.size());
            Log.e("myPlan", stringBuilder.toString());
            this.mAdapter = new MyAdapter(this, this.planList);
            this.bestRecyclerView.setAdapter(this.mAdapter);
            this.no_data.setVisibility(8);
            this.bestRecyclerView.setVisibility(0);
            return;
        }
        this.no_data.setVisibility(0);
        this.bestRecyclerView.setVisibility(8);
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

    public void saveSharedPreferencesLogList(Context context, List<SipBean> list) {
        Editor edit = context.getSharedPreferences(this.TAG_PLAN_LIST, 0).edit();
        edit.putString("myJson", new Gson().toJson((Object) list));
        edit.commit();
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
                    String str = ActivityMyPlans.this.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("banner ad error.");
                    stringBuilder.append(adError.getErrorMessage());
                    Log.e(str, stringBuilder.toString());
                }

                public void onAdLoaded(Ad ad) {
                    Log.e(ActivityMyPlans.this.TAG, "banner ad loaded.");
                    linearLayout.setVisibility(0);
                }

                public void onAdClicked(Ad ad) {
                    Log.e(ActivityMyPlans.this.TAG, "banner ad clicked.");
                }

                public void onLoggingImpression(Ad ad) {
                    Log.e(ActivityMyPlans.this.TAG, "banner ad onLoggingImpression.");
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
                if (ActivityMyPlans.this.nativeBannerAd != null && ActivityMyPlans.this.nativeBannerAd == ad) {
                    ActivityMyPlans.this.native_banner.setVisibility(0);
                    ActivityMyPlans myplanactivity = ActivityMyPlans.this;
                    nativeAdLayout.addView(NativeBannerAdView.render(myplanactivity, myplanactivity.nativeBannerAd, Type.HEIGHT_100));
                }
            }
        });
        this.nativeBannerAd.loadAd();
    }
}
