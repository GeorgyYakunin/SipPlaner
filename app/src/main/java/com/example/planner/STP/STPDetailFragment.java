package com.example.planner.STP;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.planner.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class STPDetailFragment extends Fragment {
    View v;

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList();
        private final List<String> mFragmentTitleList = new ArrayList();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return (Fragment) this.mFragmentList.get(i);
        }

        public int getCount() {
            return this.mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String str) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("called::");
            stringBuilder.append(fragment);
            Log.e("add fragment::", stringBuilder.toString());
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(str);
        }

        public CharSequence getPageTitle(int i) {
            return (CharSequence) this.mFragmentTitleList.get(i);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.v = layoutInflater.inflate(R.layout.frag_stp_details, viewGroup, false);
        ViewPager viewPager = (ViewPager) this.v.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        ((TabLayout) this.v.findViewById(R.id.tabs)).setupWithViewPager(viewPager);
        return this.v;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new Transferor_detail_fragment(), "Transferor Scheme");
        viewPagerAdapter.addFragment(new Transferee_detail_fragment(), "Transferee Scheme");
        viewPager.setAdapter(viewPagerAdapter);
    }

    public void onResume() {
        super.onResume();
        getActivity().getWindow().setSoftInputMode(3);
    }
}
