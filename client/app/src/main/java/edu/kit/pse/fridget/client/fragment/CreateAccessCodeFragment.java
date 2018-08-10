package edu.kit.pse.fridget.client.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.AccessCode;
import edu.kit.pse.fridget.client.datamodel.Flatshare;
import edu.kit.pse.fridget.client.service.AccessCodeService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateAccessCodeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateAccessCodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateAccessCodeFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private Flatshare flatshare;

    private OnFragmentInteractionListener mListener;

    public CreateAccessCodeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateAccessCodeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateAccessCodeFragment newInstance(String param1, String param2) {
        CreateAccessCodeFragment fragment = new CreateAccessCodeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_access_code, container, false);
    }


    public void createAccess(View view) {
        if (view.getId() == R.id.createAccess) {
            final Context context = view.getContext();
            Intent intent = new Intent(context, HomeActivity.class);
            RetrofitClientInstance.getRetrofitInstance().create(AccessCodeService.class).generateAccessCode(flatshare).enqueue(new Callback<AccessCode>() {
                @Override
                public void onResponse(Call<AccessCode> call, Response<AccessCode> response) {
                    Log.i("Generate Access Code", String.format("Access Code generated", new Gson().toJson(null)));
                    context.startActivity(intent);
                }

                @Override
                public void onFailure(Call<AccessCode> call, Throwable t) {
                    Log.e("Delete Member", "Deleting Member has failed.");
                }

            });

        }
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

