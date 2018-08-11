package edu.kit.pse.fridget.client.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import edu.kit.pse.fridget.client.databinding.FragmentMemberListBinding;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.viewmodel.fragmentVM.MemberListFragmentVM;


public class MemberListFragment extends Fragment {

    private static final String TAG = MemberListFragment.class.getSimpleName();
    String flatshareID = null;
    MemberListFragmentVM vm = new MemberListFragmentVM();


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm.updateLists();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.i(TAG, "Calling onCreateView");

        FragmentMemberListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false);

        vm.updateLists();
        binding.setVm(vm);

        return binding.getRoot();

    }



}