package com.example.roni.profiler.createAccount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roni.profiler.BaseFragment;
import com.example.roni.profiler.R;

public class CreateAccountFragment extends BaseFragment implements CreateAccountContract.View {

    public CreateAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CreateAccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateAccountFragment newInstance() {
        CreateAccountFragment fragment = new CreateAccountFragment();
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
        View v = inflater.inflate(R.layout.fragment_create_account, container, false);
        return v;
    }

    @Override
    public void setPresenter(CreateAccountContract.Presenter presenter) {
        
    }

    @Override
    public void showToast(int stringId) {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getConfirmationPassword() {
        return null;
    }

    @Override
    public void goToProfilePageActivity() {

    }
}
