package com.example.planner;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdIconView;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.example.planner.MyPlan.ActivityAddingPlans;
import com.example.planner.MyPlan.ActivityMyPlans;
import com.example.planner.SIP.ActivitySipCalculation;
import com.example.planner.STP.ActivityCalculatingSTP;
import com.example.planner.SWP.ActivityCalculatingSWP;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ActivityMyMainDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String TAG = ActivityMyMainDrawer.class.getSimpleName();
    private LinearLayout adView;
    private AdView adView1;
    ImageView addPlan;
    private InterstitialAd interstitialAd;
    NativeAd nativeAd;
    private NativeAdLayout nativeAdLayout;
    NativeBannerAd nativeBannerAd;
    ImageView sipCalc;
    ImageView sipPlan;
    ImageView stpCalc;
    ImageView swpCalc;
    ImageView viewPlan;

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.act_my_main_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadBannerAd();
        showInterstitial();
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
        setTitle("Home");
        this.sipCalc = (ImageView) findViewById(R.id.sipCalc);
        this.sipPlan = (ImageView) findViewById(R.id.sipPlan);
        this.swpCalc = (ImageView) findViewById(R.id.swpCalc);
        this.stpCalc = (ImageView) findViewById(R.id.stpCalc);
        this.addPlan = (ImageView) findViewById(R.id.addPlan);
        this.viewPlan = (ImageView) findViewById(R.id.viewPlan);
        this.sipCalc.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivityMyMainDrawer activityMyMainDrawer;
                if (ActivityMyMainDrawer.this.interstitialAd.isAdLoaded()) {
                    activityMyMainDrawer = ActivityMyMainDrawer.this;
                    activityMyMainDrawer.startActivity(new Intent(activityMyMainDrawer, ActivitySipCalculation.class));
                    ActivityMyMainDrawer.this.interstitialAd.show();
                    return;
                }
                activityMyMainDrawer = ActivityMyMainDrawer.this;
                activityMyMainDrawer.startActivity(new Intent(activityMyMainDrawer, ActivitySipCalculation.class));
            }
        });
        this.swpCalc.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivityMyMainDrawer activityMyMainDrawer;
                if (ActivityMyMainDrawer.this.interstitialAd.isAdLoaded()) {
                    activityMyMainDrawer = ActivityMyMainDrawer.this;
                    activityMyMainDrawer.startActivity(new Intent(activityMyMainDrawer, ActivityCalculatingSWP.class));
                    ActivityMyMainDrawer.this.interstitialAd.show();
                    return;
                }
                activityMyMainDrawer = ActivityMyMainDrawer.this;
                activityMyMainDrawer.startActivity(new Intent(activityMyMainDrawer, ActivityCalculatingSWP.class));
            }
        });
        this.sipPlan.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivityMyMainDrawer activityMyMainDrawer;
                if (ActivityMyMainDrawer.this.interstitialAd.isAdLoaded()) {
                    activityMyMainDrawer = ActivityMyMainDrawer.this;
                    activityMyMainDrawer.startActivity(new Intent(activityMyMainDrawer, ActivityPlanningSIP.class));
                    ActivityMyMainDrawer.this.interstitialAd.show();
                    return;
                }
                activityMyMainDrawer = ActivityMyMainDrawer.this;
                activityMyMainDrawer.startActivity(new Intent(activityMyMainDrawer, ActivityPlanningSIP.class));
            }
        });
        this.stpCalc.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivityMyMainDrawer activityMyMainDrawer;
                if (ActivityMyMainDrawer.this.interstitialAd.isAdLoaded()) {
                    activityMyMainDrawer = ActivityMyMainDrawer.this;
                    activityMyMainDrawer.startActivity(new Intent(activityMyMainDrawer, ActivityCalculatingSTP.class));
                    ActivityMyMainDrawer.this.interstitialAd.show();
                    return;
                }
                activityMyMainDrawer = ActivityMyMainDrawer.this;
                activityMyMainDrawer.startActivity(new Intent(activityMyMainDrawer, ActivityCalculatingSTP.class));
            }
        });
        this.addPlan.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivityMyMainDrawer activityMyMainDrawer;
                if (ActivityMyMainDrawer.this.interstitialAd.isAdLoaded()) {
                    activityMyMainDrawer = ActivityMyMainDrawer.this;
                    activityMyMainDrawer.startActivity(new Intent(activityMyMainDrawer, ActivityAddingPlans.class));
                    ActivityMyMainDrawer.this.interstitialAd.show();
                    return;
                }
                activityMyMainDrawer = ActivityMyMainDrawer.this;
                activityMyMainDrawer.startActivity(new Intent(activityMyMainDrawer, ActivityAddingPlans.class));
            }
        });
        this.viewPlan.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivityMyMainDrawer activityMyMainDrawer;
                if (ActivityMyMainDrawer.this.interstitialAd.isAdLoaded()) {
                    activityMyMainDrawer = ActivityMyMainDrawer.this;
                    activityMyMainDrawer.startActivity(new Intent(activityMyMainDrawer, ActivityMyPlans.class));
                    ActivityMyMainDrawer.this.interstitialAd.show();
                    return;
                }
                activityMyMainDrawer = ActivityMyMainDrawer.this;
                activityMyMainDrawer.startActivity(new Intent(activityMyMainDrawer, ActivityMyPlans.class));
            }
        });
        loadNativeAd();
    }

    private void showInterstitial() {
        this.interstitialAd = new InterstitialAd(this, getString(R.string.inter));
        this.interstitialAd.setAdListener(new InterstitialAdListener() {
            public void onAdClicked(Ad ad) {
            }

            public void onAdLoaded(Ad ad) {
            }

            public void onError(Ad ad, AdError adError) {
            }

            public void onInterstitialDisplayed(Ad ad) {
            }

            public void onLoggingImpression(Ad ad) {
            }

            public void onInterstitialDismissed(Ad ad) {
                ActivityMyMainDrawer.this.interstitialAd.loadAd();
            }
        });
        this.interstitialAd.loadAd();
    }

    public void loadBannerAd() {
        try {
            this.adView1 = new AdView((Context) this, getResources().getString(R.string.banner_ad_id), AdSize.BANNER_HEIGHT_50);
            final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.banner_container);
            linearLayout.setVisibility(8);
            linearLayout.addView(this.adView1);
            this.adView1.loadAd();
            this.adView1.setAdListener(new AdListener() {
                public void onError(Ad ad, AdError adError) {
                    String str = ActivityMyMainDrawer.this.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("banner ad error.");
                    stringBuilder.append(adError.getErrorMessage());
                    Log.e(str, stringBuilder.toString());
                }

                public void onAdLoaded(Ad ad) {
                    Log.e(ActivityMyMainDrawer.this.TAG, "banner ad loaded.");
                    linearLayout.setVisibility(0);
                }

                public void onAdClicked(Ad ad) {
                    Log.e(ActivityMyMainDrawer.this.TAG, "banner ad clicked.");
                }

                public void onLoggingImpression(Ad ad) {
                    Log.e(ActivityMyMainDrawer.this.TAG, "banner ad onLoggingImpression.");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadNativeAd() {
        this.nativeAd = new NativeAd((Context) this, getResources().getString(R.string.native_ad_id));
        this.nativeAdLayout = (NativeAdLayout) findViewById(R.id.native_ad_container);
        this.nativeAdLayout.setVisibility(8);
        this.nativeAd.setAdListener(new NativeAdListener() {
            public void onMediaDownloaded(Ad ad) {
                Log.e(ActivityMyMainDrawer.this.TAG, "Native ad finished downloading all assets.");
            }

            public void onError(Ad ad, AdError adError) {
                String str = ActivityMyMainDrawer.this.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Native ad failed to load: ");
                stringBuilder.append(adError.getErrorMessage());
                Log.e(str, stringBuilder.toString());
            }

            public void onAdLoaded(Ad ad) {
                Log.d(ActivityMyMainDrawer.this.TAG, "Native ad is loaded and ready to be displayed!");
                if (ActivityMyMainDrawer.this.nativeAd != null && ActivityMyMainDrawer.this.nativeAd == ad) {
                    ActivityMyMainDrawer.this.nativeAdLayout.setVisibility(0);
                    ActivityMyMainDrawer activityMyMainDrawer = ActivityMyMainDrawer.this;
                    activityMyMainDrawer.inflateAd(activityMyMainDrawer.nativeAd);
                }
            }

            public void onAdClicked(Ad ad) {
                Log.d(ActivityMyMainDrawer.this.TAG, "Native ad clicked!");
            }

            public void onLoggingImpression(Ad ad) {
                Log.d(ActivityMyMainDrawer.this.TAG, "Native ad impression logged!");
            }
        });
        this.nativeAd.loadAd();
    }

    private void inflateAd(NativeAd nativeAd) {
        nativeAd.unregisterView();
        this.nativeAdLayout = (NativeAdLayout) findViewById(R.id.native_ad_container);
        int i = 0;
        this.adView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.native_ad_layout, this.nativeAdLayout, false);
        this.nativeAdLayout.addView(this.adView);
        LinearLayout linearLayout = (LinearLayout) this.adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(this, nativeAd, this.nativeAdLayout);
        linearLayout.removeAllViews();
        linearLayout.addView(adOptionsView, 0);
        MediaView mediaView = (AdIconView) this.adView.findViewById(R.id.native_ad_icon);
        TextView textView = (TextView) this.adView.findViewById(R.id.native_ad_title);
        MediaView mediaView2 = (MediaView) this.adView.findViewById(R.id.native_ad_media);
        TextView textView2 = (TextView) this.adView.findViewById(R.id.native_ad_social_context);
        TextView textView3 = (TextView) this.adView.findViewById(R.id.native_ad_body);
        TextView textView4 = (TextView) this.adView.findViewById(R.id.native_ad_sponsored_label);
        Button button = (Button) this.adView.findViewById(R.id.native_ad_call_to_action);
        textView.setText(nativeAd.getAdvertiserName());
        textView3.setText(nativeAd.getAdBodyText());
        textView2.setText(nativeAd.getAdSocialContext());
        if (!nativeAd.hasCallToAction()) {
            i = 4;
        }
        button.setVisibility(i);
        button.setText(nativeAd.getAdCallToAction());
        textView4.setText(nativeAd.getSponsoredTranslation());
        List arrayList = new ArrayList();
        arrayList.add(textView);
        arrayList.add(button);
        nativeAd.registerViewForInteraction(this.adView, mediaView2, mediaView, arrayList);
    }

    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen((int) GravityCompat.START)) {
            drawerLayout.closeDrawer((int) GravityCompat.START);
            return;
        }
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawableResource(17170445);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.ratings_layout);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(-1, -2);
        RatingBar ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);
        final TextView textView = (TextView) dialog.findViewById(R.id.tvRatingScale);
        TextView textView2 = (TextView) dialog.findViewById(R.id.btnSubmit);
        textView2.setText("Cancel");
        TextView textView3 = (TextView) dialog.findViewById(R.id.cancel);
        textView3.setText("Exit");
        loadNativeAdBanner(dialog);
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
                textView.setText(String.valueOf(f));
                ActivityMyMainDrawer activityMyMainDrawer;
                StringBuilder stringBuilder;
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        textView.setText("Very bad");
                        Toast.makeText(ActivityMyMainDrawer.this, "Thank you for sharing your feedback", 0).show();
                        dialog.dismiss();
                        return;
                    case 2:
                        textView.setText("Need some improvement");
                        Toast.makeText(ActivityMyMainDrawer.this, "Thank you for sharing your feedback", 0).show();
                        dialog.dismiss();
                        return;
                    case 3:
                        textView.setText("Good");
                        Toast.makeText(ActivityMyMainDrawer.this, "Thank you for sharing your feedback", 0).show();
                        dialog.dismiss();
                        return;
                    case 4:
                        textView.setText("Great");
                        activityMyMainDrawer = ActivityMyMainDrawer.this;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("https://play.google.com/store/apps/details?id=");
                        stringBuilder.append(ActivityMyMainDrawer.this.getPackageName());
                        activityMyMainDrawer.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())));
                        dialog.dismiss();
                        return;
                    case 5:
                        textView.setText("Awesome. I love it");
                        activityMyMainDrawer = ActivityMyMainDrawer.this;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("https://play.google.com/store/apps/details?id=");
                        stringBuilder.append(ActivityMyMainDrawer.this.getPackageName());
                        activityMyMainDrawer.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())));
                        dialog.dismiss();
                        return;
                    default:
                        textView.setText("");
                        return;
                }
            }
        });
        textView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        textView3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                intent.setFlags(32768);
                intent.setFlags(67108864);
                intent.setFlags(268435456);
                ActivityMyMainDrawer.this.startActivity(intent);
                ActivityMyMainDrawer.this.finish();
            }
        });
        dialog.show();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.nav_sip_calculator) {
            startActivity(new Intent(this, ActivitySipCalculation.class));
        } else if (itemId == R.id.nav_sip_planner) {
            startActivity(new Intent(this, ActivityPlanningSIP.class));
        } else if (itemId == R.id.nav_swp_calculator) {
            startActivity(new Intent(this, ActivityCalculatingSWP.class));
        } else if (itemId == R.id.nav_stp_calculator) {
            startActivity(new Intent(this, ActivityCalculatingSTP.class));
        } else if (itemId == R.id.nav_add_plan) {
            startActivity(new Intent(this, ActivityAddingPlans.class));
        } else if (itemId == R.id.nav_view_plan) {
            startActivity(new Intent(this, ActivityMyPlans.class));
        } else if (itemId == R.id.nav_privacy) {
            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.privacy_policylink))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (itemId == R.id.nav_share) {
            try {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.SUBJECT", "EMI Calculator");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("\nLet me recommend you this application\n\n");
                stringBuilder.append("https://play.google.com/store/apps/details?id=");
                stringBuilder.append(getPackageName());
                stringBuilder.append("\n\n");
                intent.putExtra("android.intent.extra.TEXT", stringBuilder.toString());
                startActivity(Intent.createChooser(intent, "choose one"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (itemId == R.id.nav_rate) {
            final Dialog dialog = new Dialog(this);
            dialog.getWindow().setBackgroundDrawableResource(17170445);
            dialog.requestWindowFeature(1);
            dialog.setContentView(R.layout.ratings_layout);
            dialog.setCancelable(false);
            RatingBar ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);
            final TextView textView = (TextView) dialog.findViewById(R.id.tvRatingScale);
            TextView textView2 = (TextView) dialog.findViewById(R.id.btnSubmit);
            textView2.setText("Yes");
            textView2.setVisibility(8);
            TextView textView3 = (TextView) dialog.findViewById(R.id.cancel);
            textView3.setText("Close");
            loadNativeAdBanner(dialog);
            ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
                public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
                    textView.setText(String.valueOf(f));
                    ActivityMyMainDrawer activityMyMainDrawer;
                    StringBuilder stringBuilder;
                    switch ((int) ratingBar.getRating()) {
                        case 1:
                            textView.setText("Very bad");
                            Toast.makeText(ActivityMyMainDrawer.this, "Thank you for sharing your feedback", 0).show();
                            dialog.dismiss();
                            return;
                        case 2:
                            textView.setText("Need some improvement");
                            Toast.makeText(ActivityMyMainDrawer.this, "Thank you for sharing your feedback", 0).show();
                            dialog.dismiss();
                            return;
                        case 3:
                            textView.setText("Good");
                            Toast.makeText(ActivityMyMainDrawer.this, "Thank you for sharing your feedback", 0).show();
                            dialog.dismiss();
                            return;
                        case 4:
                            textView.setText("Great");
                            activityMyMainDrawer = ActivityMyMainDrawer.this;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("https://play.google.com/store/apps/details?id=");
                            stringBuilder.append(ActivityMyMainDrawer.this.getPackageName());
                            activityMyMainDrawer.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())));
                            dialog.dismiss();
                            return;
                        case 5:
                            textView.setText("Awesome. I love it");
                            activityMyMainDrawer = ActivityMyMainDrawer.this;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("https://play.google.com/store/apps/details?id=");
                            stringBuilder.append(ActivityMyMainDrawer.this.getPackageName());
                            activityMyMainDrawer.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())));
                            dialog.dismiss();
                            return;
                        default:
                            textView.setText("");
                            return;
                    }
                }
            });
            textView2.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            textView3.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
        return true;
    }

    private void loadNativeAdBanner(Dialog dialog) {
        this.nativeBannerAd = new NativeBannerAd((Context) this, getResources().getString(R.string.native_banner_id));
        this.nativeAdLayout = (NativeAdLayout) dialog.findViewById(R.id.native_banner_ad_container);
        final TextView textView = (TextView) dialog.findViewById(R.id.textAd);
        textView.setVisibility(0);
        this.nativeBannerAd.setAdListener(new NativeAdListener() {
            public void onMediaDownloaded(Ad ad) {
                Log.e(ActivityMyMainDrawer.this.TAG, "Native ad finished downloading all assets.");
            }

            public void onError(Ad ad, AdError adError) {
                String str = ActivityMyMainDrawer.this.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Native ad failed to load: ");
                stringBuilder.append(adError.getErrorMessage());
                Log.e(str, stringBuilder.toString());
            }

            public void onAdLoaded(Ad ad) {
                Log.d(ActivityMyMainDrawer.this.TAG, "Native ad is loaded and ready to be displayed!");
                if (ActivityMyMainDrawer.this.nativeBannerAd != null && ActivityMyMainDrawer.this.nativeBannerAd == ad) {
                    textView.setVisibility(8);
                    ActivityMyMainDrawer activityMyMainDrawer = ActivityMyMainDrawer.this;
                    activityMyMainDrawer.inflateAd(activityMyMainDrawer.nativeBannerAd);
                }
            }

            public void onAdClicked(Ad ad) {
                Log.d(ActivityMyMainDrawer.this.TAG, "Native ad clicked!");
            }

            public void onLoggingImpression(Ad ad) {
                Log.d(ActivityMyMainDrawer.this.TAG, "Native ad impression logged!");
            }
        });
        this.nativeBannerAd.loadAd();
    }

    private void inflateAd(NativeBannerAd nativeBannerAd) {
        this.nativeBannerAd.unregisterView();
        int i = 0;
        View view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.native_banner_ad_layout, this.nativeAdLayout, false);
        this.nativeAdLayout.addView(view);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(this, this.nativeBannerAd, this.nativeAdLayout);
        relativeLayout.removeAllViews();
        relativeLayout.addView(adOptionsView, 0);
        TextView textView = (TextView) view.findViewById(R.id.native_ad_title);
        TextView textView2 = (TextView) view.findViewById(R.id.native_ad_social_context);
        TextView textView3 = (TextView) view.findViewById(R.id.native_ad_sponsored_label);
        MediaView mediaView = (AdIconView) view.findViewById(R.id.native_icon_view);
        Button button = (Button) view.findViewById(R.id.native_ad_call_to_action);
        button.setText(this.nativeBannerAd.getAdCallToAction());
        if (!this.nativeBannerAd.hasCallToAction()) {
            i = 4;
        }
        button.setVisibility(i);
        textView.setText(this.nativeBannerAd.getAdvertiserName());
        textView2.setText(this.nativeBannerAd.getAdSocialContext());
        textView3.setText(this.nativeBannerAd.getSponsoredTranslation());
        List arrayList = new ArrayList();
        arrayList.add(textView);
        arrayList.add(button);
        this.nativeBannerAd.registerViewForInteraction(view, mediaView, arrayList);
    }
}
