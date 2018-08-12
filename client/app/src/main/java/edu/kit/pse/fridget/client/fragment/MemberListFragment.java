package edu.kit.pse.fridget.client.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.FragmentMemberListBinding;
import edu.kit.pse.fridget.client.viewmodel.fragmentVM.MemberListFragmentVM;


public class MemberListFragment extends Fragment {

    private static final String TAG = MemberListFragment.class.getSimpleName();
    MemberListFragmentVM vm = new MemberListFragmentVM();
    String flatshareId;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.flatshareId = getArguments().getString("FlatshareId");
        vm.setFlatshareId(flatshareId);

        vm.updateView();
        vm.fetchData();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(TAG, "Calling onCreateView");


        FragmentMemberListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false);

        this.flatshareId = getArguments().getString("FlatshareId");
        vm.setFlatshareId(flatshareId);

        vm.updateView();
        vm.fetchData();

        binding.setVm(vm);
        binding.setLifecycleOwner(this);

        return binding.getRoot();

    }



}