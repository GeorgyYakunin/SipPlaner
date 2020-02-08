package com.example.planner.STP;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.example.planner.R;
import com.example.planner.SipBean;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Transferor_detail_fragment extends Fragment {
    String TAG = Transferor_detail_fragment.class.getSimpleName();
    ListView list;
    View v;

    public class SipAdapter extends BaseAdapter {
        ArrayList<SipBean> arrayList;
        Context context;
        Double tInterst = Double.valueOf(0.0d);
        Double tInvest = Double.valueOf(0.0d);
        int yr = 1;

        public long getItemId(int i) {
            return (long) i;
        }

        public SipAdapter(Context context, ArrayList<SipBean> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        public int getCount() {
            return this.arrayList.size();
        }

        public Object getItem(int i) {
            return this.arrayList.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            view = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.single_sip, viewGroup, false);
            TextView textView = (TextView) view.findViewById(R.id.tx_yr);
            TextView textView2 = (TextView) view.findViewById(R.id.tx_month);
            TextView textView3 = (TextView) view.findViewById(R.id.tx_investment);
            TextView textView4 = (TextView) view.findViewById(R.id.tx_interest);
            TextView textView5 = (TextView) view.findViewById(R.id.tx_balanceB);
            TextView textView6 = (TextView) view.findViewById(R.id.tx_balanceE);
            if (i % 2 == 0) {
                textView.setBackgroundColor(Color.parseColor("#FCF3CF"));
                textView2.setBackgroundColor(Color.parseColor("#FCF3CF"));
                textView3.setBackgroundColor(Color.parseColor("#FCF3CF"));
                textView4.setBackgroundColor(Color.parseColor("#FCF3CF"));
                textView5.setBackgroundColor(Color.parseColor("#FCF3CF"));
                textView6.setBackgroundColor(Color.parseColor("#FCF3CF"));
            } else {
                textView.setBackgroundColor(Color.parseColor("#FDEBD0"));
                textView2.setBackgroundColor(Color.parseColor("#FDEBD0"));
                textView3.setBackgroundColor(Color.parseColor("#FDEBD0"));
                textView4.setBackgroundColor(Color.parseColor("#FDEBD0"));
                textView5.setBackgroundColor(Color.parseColor("#FDEBD0"));
                textView6.setBackgroundColor(Color.parseColor("#FDEBD0"));
            }
            DecimalFormat decimalFormat = new DecimalFormat("#");
            textView.setText(Integer.toString(this.yr));
            textView2.setText(((SipBean) this.arrayList.get(i)).getMonth());
            textView5.setText(((SipBean) this.arrayList.get(i)).getBalanceB());
            textView6.setText(((SipBean) this.arrayList.get(i)).getBalanceE());
            textView4.setText(((SipBean) this.arrayList.get(i)).getInterst());
            textView3.setText(((SipBean) this.arrayList.get(i)).getInvestment());
            return view;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.v = layoutInflater.inflate(R.layout.frag_transferor_detail, viewGroup, false);
        this.list = (ListView) this.v.findViewById(R.id.list);
        LinearLayout linearLayout = (LinearLayout) this.v.findViewById(R.id.no_data);
        if (ActivityCalculatingSTP.arrayList_transferor.size() > 0) {
            this.list.setAdapter(new SipAdapter(getContext(), ActivityCalculatingSTP.arrayList_transferor));
            linearLayout.setVisibility(View.GONE);
            this.list.setVisibility(View.VISIBLE);
        } else {
            linearLayout.setVisibility(View.VISIBLE);
            this.list.setVisibility(View.GONE);
        }
        return this.v;
    }

    public void onResume() {
        super.onResume();
        getActivity().getWindow().setSoftInputMode(3);
    }
}
