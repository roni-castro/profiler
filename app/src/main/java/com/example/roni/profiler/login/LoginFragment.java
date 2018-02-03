package com.example.roni.profiler.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roni.profiler.BaseFragment;
import com.example.roni.profiler.R;
import com.example.roni.profiler.createAccount.CreateAccountActivity;
import com.example.roni.profiler.profilePage.ProfilePageActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginContract.View {
    private LoginContract.Presenter presenter;
    @BindView(R.id.edit_txt_email) TextInputEditText emailEditText;
    @BindView(R.id.edit_txt_password) TextInputEditText passwordEditText;

    public LoginFragment() {
        // Required empty public constructor
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true); // Helps the view/Presenter/Service survive orientation change
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(presenter == null){
            presenter = new LoginPresenter();
        }
        presenter.subscribe();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
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
    public void goToCreateAccountActivity() {
        Intent intent = new Intent(getActivity(), CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToProfilePageActivity() {
        Intent intent = new Intent(getActivity(), ProfilePageActivity.class);
        startActivity(intent);
        getActivity().finish();
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
