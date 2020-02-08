package com.example.planner.MyPlan;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.example.planner.R;
import com.example.planner.SipAdapter;
import com.example.planner.SipBean;
import java.util.ArrayList;

public class PlanDetailFragment extends Fragment {
    ArrayList<SipBean> arrayListAddPlan;
    ListView list;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.frag_details_plan, viewGroup, false);
        this.list = (ListView) inflate.findViewById(R.id.list);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.no_data);
        if (this.arrayListAddPlan.size() > 0) {
            this.list.setAdapter(new SipAdapter(getContext(), this.arrayListAddPlan));
            linearLayout.setVisibility(8);
            this.list.setVisibility(0);
        } else {
            linearLayout.setVisibility(0);
            this.list.setVisibility(8);
        }
        return inflate;
    }

    public void onResume() {
        super.onResume();
        getActivity().getWindow().setSoftInputMode(3);
    }
}
