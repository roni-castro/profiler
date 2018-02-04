package com.example.roni.profiler;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.auth.AuthServiceInjection;
import com.example.roni.profiler.dataModel.auth.Credentials;
import com.example.roni.profiler.dataModel.scheduler.SchedulerProviderInjection;
import com.example.roni.profiler.login.LoginContract;
import com.example.roni.profiler.login.LoginPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Completable;

/**
 * Created by roni on 03/02/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {
    private static final String VALID_EMAIL = "email@example.com";
    private static final String INVALID_EMAIL = "emailexample.com";
    private static final String VALID_PASSWORD = "123456";
    private static final String INVALID_PASSWORD = "123"; // user real password is not 123

    private LoginPresenter presenter;

    @Mock
    private AuthService authService;

    @Mock
    LoginContract.View loginView;

    @Before
    public void setUpTest(){
        MockitoAnnotations.initMocks(this);

        presenter = new LoginPresenter(
                authService,
                loginView,
                SchedulerProviderInjection.getSchedulerProvider());
    }

    @Test
    public void whenLoginAttempSucceeds(){
        Mockito.when(loginView.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(loginView.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(authService.loginAccount(Mockito.any(Credentials.class)))
                .thenReturn(Completable.complete());
        presenter.onLoginClick();
        Mockito.verify(loginView).goToProfilePageActivity();
    }

    @Test
    public void whenLoginAttempFailsWithoutCredentials(){
        Mockito.when(loginView.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(loginView.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(authService.loginAccount(Mockito.any(Credentials.class))).thenReturn(Completable.error(new Exception()));
        presenter.onLoginClick();
        Mockito.verify(loginView).showToast(Mockito.anyString());
    }

    // The same test that the one above, but now the credentials matter
    @Test
    public void whenLoginAttempFailsWithCredentials () throws Exception {
        Credentials cred = new Credentials(VALID_PASSWORD, "", VALID_EMAIL);
        Mockito.when(authService.loginAccount(cred)).thenReturn(Completable.error(new Exception()));
        presenter.attemptLogIn(cred);
        Mockito.verify(loginView).showToast(Mockito.anyString());
    }

    @Test
    public void whenRegisterButtonIsClicked(){
        presenter.onRegisterClick();
        Mockito.verify(loginView).goToCreateAccountActivity();
    }

    @Test
    public void whenEmailIsEmpty(){
        Mockito.when(loginView.getEmail()).thenReturn("");
        Mockito.when(loginView.getPassword()).thenReturn(VALID_PASSWORD);
        presenter.onLoginClick();
        Mockito.verify(loginView).showToast(Mockito.anyString());
    }

    @Test
    public void whenEmailIsInvalid(){
        Mockito.when(loginView.getEmail()).thenReturn(INVALID_EMAIL);
        Mockito.when(loginView.getPassword()).thenReturn(VALID_PASSWORD);
        presenter.onLoginClick();
        Mockito.verify(loginView).showToast(Mockito.anyString());
    }


    @Test
    public void whenPasswordIsEmpty(){
        Mockito.when(loginView.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(loginView.getPassword()).thenReturn("");
        presenter.onLoginClick();
        Mockito.verify(loginView).showToast(Mockito.anyString());
    }

    @Test
    public void whenEmailOrPasswordAreNotValidShowsMessage(){
        Mockito.when(loginView.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(loginView.getPassword()).thenReturn(INVALID_PASSWORD);
        Mockito.when(authService.loginAccount(Mockito.any(Credentials.class)))
                .thenReturn(Completable.error(new Exception()));
        presenter.onLoginClick();
        Mockito.verify(loginView).showToast(Mockito.anyString());
    }
}
