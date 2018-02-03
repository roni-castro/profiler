package com.example.roni.profiler;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.auth.AuthServiceInjection;
import com.example.roni.profiler.dataModel.auth.FakeAuthService;
import com.example.roni.profiler.dataModel.scheduler.SchedulerProviderInjection;
import com.example.roni.profiler.login.LoginContract;
import com.example.roni.profiler.login.LoginPresenter;
import com.example.roni.profiler.utils.SchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by roni on 03/02/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {
    private LoginPresenter presenter;
    private AuthService authService;

    @Mock
    LoginContract.View loginView;

    @Before
    public void setUpTest(){
        MockitoAnnotations.initMocks(this);
        authService = AuthServiceInjection.getAuthService();

        presenter = new LoginPresenter(
                authService,
                loginView,
                SchedulerProviderInjection.getSchedulerProvider());
        authService = AuthServiceInjection.getAuthService();
    }

    @Test
    public void whenLoginAttempSucceeds(){
        presenter.onLoginClick();
        Mockito.verify(loginView).goToProfilePageActivity();
    }

    @Test
    public void whenLoginAttempFail(){
        presenter.onLoginClick();
        Mockito.verify(loginView).showToast(Mockito.anyString());
    }

    @Test
    public void whenRegisterButtonIsClicked(){
        Mockito.verify(loginView).goToCreateAccountActivity();
    }

    @Test
    public void whenEmailIsEmpty(){
        Mockito.when(loginView.getEmail()).thenReturn("");
        Mockito.verify(loginView).showToast(Mockito.anyString());
    }

    @Test
    public void whenPasswordIsEmpty(){
        Mockito.verify(loginView).showToast(Mockito.anyString());
    }

    @Test
    public void whenEmailAndPasswordDoesNotMatch(){
        Mockito.verify(loginView).showToast(Mockito.anyString());
    }
}
