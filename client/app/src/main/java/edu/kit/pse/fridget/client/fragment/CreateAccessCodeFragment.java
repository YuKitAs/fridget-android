package edu.kit.pse.fridget.client.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.FragmentCreateAccessCodeBinding;
import edu.kit.pse.fridget.client.viewmodel.fragmentVM.CreateAccessCodeFragmentVM;


public class CreateAccessCodeFragment extends Fragment {

    private static final String TAG = CreateAccessCodeFragment.class.getSimpleName();
    private String flatshareId;
    private CreateAccessCodeFragmentVM createAccessCodeVM = new CreateAccessCodeFragmentVM();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "Calling onCreateView");
        // Inflate the layout for this fragment

        FragmentCreateAccessCodeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_access_code, container, false);
        binding.setCreateAccessCodeFragmentVM(createAccessCodeVM);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i(TAG, "Calling onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        this.flatshareId = getArguments().getString("FlatshareId");
        createAccessCodeVM.update(flatshareId);
    }

}





