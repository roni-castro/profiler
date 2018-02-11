package com.example.roni.profiler.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.example.roni.profiler.R;
import com.example.roni.profiler.dataModel.auth.User;
import com.example.roni.profiler.ui.base.BaseFragment;
import com.example.roni.profiler.ui.createAccount.CreateAccountActivity;
import com.example.roni.profiler.ui.profilePage.ProfilePageActivity;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginContract.AppView {

    @Inject
    LoginContract.Presenter<LoginContract.AppView> presenter;

    @BindView(R.id.edit_txt_email) TextInputEditText emailEditText;
    @BindView(R.id.edit_txt_password) TextInputEditText passwordEditText;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public int getFragmentViewResId() {
        return R.layout.fragment_login;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.checkIfExistsLoggedUser();

    }

    @Override
    protected void setUpCreatedView(View view) {}

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
    public String getEmail() {
        return emailEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public void showMessage(int stringResId) {
        showToast(stringResId);
    }

    @Override
    public void showMessage(String message) {
        showToast(message);
    }

    @Override
    public void goToCreateAccountActivity() {
        Intent intent = new Intent(getActivity(), CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToForgotPasswordActivity() {}

    @Override
    public void goToProfilePageActivity() {
        Intent intent = new Intent(getActivity(), ProfilePageActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void saveUserData(String uid, String email, String name) {
        SharedPreferences prefs = getActivity().getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        User user = new User(uid, email, name);
        String json = gson.toJson(user);
        editor.putString("USER_DATA", json);
        editor.apply();
    }

    @OnClick(R.id.btn_login)
    public void loginButtonClicked(View view){
        presenter.onLoginClick();
    }

    @OnClick(R.id.btn_register)
    public void registerButtonClicked(View view){
        presenter.onRegisterClick();
    }
}
