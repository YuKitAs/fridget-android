package edu.kit.pse.fridget.client.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.viewmodel.fragmentVM.LeaveFlatshareFragmentVM;
import android.databinding.DataBindingUtil;

import edu.kit.pse.fridget.client.databinding.FragmentLeaveFlatshareBinding;

public class LeaveFlatshareFragment extends Fragment {

    private static final String TAG = LeaveFlatshareFragment.class.getSimpleName();

    LeaveFlatshareFragmentVM vm = new LeaveFlatshareFragmentVM();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i(TAG, "Calling onCreateView");
        FragmentLeaveFlatshareBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_leave_flatshare, container, false);
        //binding.setVm(vm);
        return binding.getRoot();
    }

}
