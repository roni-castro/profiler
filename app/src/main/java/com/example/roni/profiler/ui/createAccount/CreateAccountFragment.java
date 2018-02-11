package com.example.roni.profiler.ui.createAccount;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.example.roni.profiler.R;
import com.example.roni.profiler.dataModel.auth.User;
import com.example.roni.profiler.ui.base.BaseFragment;
import com.example.roni.profiler.ui.profilePage.ProfilePageActivity;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateAccountFragment extends BaseFragment implements CreateAccountContract.AppView {
    @Inject
    CreateAccountContract.Presenter<CreateAccountContract.AppView> presenter;

    @BindView(R.id.edit_txt_name)
    TextInputEditText nameEditText;
    @BindView(R.id.edit_txt_email)
    TextInputEditText emailEditText;
    @BindView(R.id.edit_txt_password)
    TextInputEditText passwordEditText;
    @BindView(R.id.edit_txt_confirmation_password)
    TextInputEditText confirmationPasswordEditText;

    public CreateAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CreateAccountFragment.
     */
    public static CreateAccountFragment newInstance() {
        CreateAccountFragment fragment = new CreateAccountFragment();
        return fragment;
    }

    @Override
    public int getFragmentViewResId() {
        return R.layout.fragment_create_account;
    }

    @Override
    protected void setUpCreatedView(View view) {

    }

    @Override
    protected void injectViewIntoComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    public void attachViewToPresenter() {
        presenter.onAttach(this);
    }

    @Override
    public void detachViewFromPresenter() {
        presenter.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showMessage(int stringId) {
        showToast(stringId);
    }

    @Override
    public void showMessage(String message) {
        showToast(message);
    }

    @Override
    public String getName() {
        return nameEditText.getText().toString();
    }

    @Override
    public String getEmail() {
        return emailEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public String getConfirmationPassword() {
        return confirmationPasswordEditText.getText().toString();
    }

    @Override
    public void goToProfilePageActivity() {
        Intent intent = new Intent(getActivity(), ProfilePageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void saveUserData(String userUid, String userEmail, String userName) {
        SharedPreferences prefs = getActivity().getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        User user = new User(userUid, userEmail, userName);
        String json = gson.toJson(user);
        editor.putString("USER_DATA", json);
        editor.apply();

    }

    @OnClick(R.id.btn_create_account)
    public void onCreateAccountClick(View v){
        presenter.onCreateAccount();
    }
}
