package com.example.roni.profiler.createAccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.example.roni.profiler.BaseFragment;
import com.example.roni.profiler.R;
import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.auth.AuthServiceInjection;
import com.example.roni.profiler.dataModel.scheduler.SchedulerProviderInjection;
import com.example.roni.profiler.profilePage.ProfilePageActivity;
import com.example.roni.profiler.utils.BaseSchedulerProvider;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateAccountFragment extends BaseFragment implements CreateAccountContract.AppView {
    CreateAccountContract.Presenter presenter;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(presenter == null){
            AuthService authService = AuthServiceInjection.getAuthService();
            BaseSchedulerProvider schedulerProvider = SchedulerProviderInjection.getSchedulerProvider();
            presenter = new CreateAccountPresenter(authService, this, schedulerProvider);
            presenter.subscribe();
        }
    }

    @Override
    public void setPresenter(CreateAccountContract.Presenter presenter) {
        this.presenter = presenter;
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

    @OnClick(R.id.btn_create_account)
    public void onCreateAccountClick(View v){
        presenter.onCreateAccount();
    }
}
