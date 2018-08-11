package edu.kit.pse.fridget.client.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import com.google.gson.Gson;

import java.util.List;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.datamodel.Flatshare;
import edu.kit.pse.fridget.client.datamodel.representation.UserMembershipRepresentation;
import edu.kit.pse.fridget.client.service.MembershipService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MemberListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MemberListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemberListFragment extends Fragment {
    SimpleCursorAdapter mAdapter;
    private Flatshare flat;
    private String flShID = flat.getId();

    private OnFragmentInteractionListener mListener;

    private static final String TAG = MemberListFragment.class.getSimpleName();

    public MemberListFragment() {

    }

    public void updateLists() {
        getMemberList(flShID);
    }

    public void getMemberList(String flatshareId) {
        RetrofitClientInstance.getRetrofitInstance().create(MembershipService.class).getMemberList(flatshareId).enqueue(new Callback<List<UserMembershipRepresentation>>() {
            @Override
            public void onResponse(Call<List<UserMembershipRepresentation>> call, Response<List<UserMembershipRepresentation>> response) {
                List<UserMembershipRepresentation> body = response.body();
                if (body != null) {
                    Log.i(TAG, String.format("Member list fetched: %s", new Gson().toJson(body)));
                }
            }

            @Override
            public void onFailure(Call<List<UserMembershipRepresentation>> call, Throwable t) {
                Log.e(TAG, "Fetching member list failed.");
                t.printStackTrace();
            }
        });
    }


    //GradientDrawable drawable = (GradientDrawable) getDrawable(R.drawable.magnet);
    //drawable.setColor(Color.parseColor("#000000"));

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MemberListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MemberListFragment newInstance(String param1, String param2) {
        MemberListFragment fragment = new MemberListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //this.setListAdapter(new ArrayAdapter<String>(
        //      this, R.layout.list_activity,
        //    R.id.,userName));

    }

    @Override
    public void onStart() {
        super.onStart();
        updateLists();
        Log.i(TAG, "Calling onStart");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_member_list, container, false);
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
