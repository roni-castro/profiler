package com.example.roni.profiler;

import com.example.roni.profiler.dataModel.auth.AuthService;
import com.example.roni.profiler.dataModel.auth.Credentials;
import com.example.roni.profiler.dataModel.auth.User;
import com.example.roni.profiler.dataModel.database.DatabaseSource;
import com.example.roni.profiler.ui.login.LoginContract;
import com.example.roni.profiler.ui.login.LoginPresenter;

import org.hamcrest.core.AnyOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

import static org.mockito.ArgumentMatchers.any;

/**
 * Created by roni on 03/02/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {
    private static final String VALID_EMAIL = "email@example.com";
    private static final String INVALID_EMAIL = "emailexample.com";
    private static final String VALID_PASSWORD = "123456";
    private static final String INVALID_PASSWORD = "123"; // user real password is not 123
    private static final String VALID_UID = "UID";
    private static final String VALID_USERNAME = "Roni";

    private LoginPresenter<LoginContract.AppView> presenter;

    @Mock
    AuthService authService;

    @Mock
    DatabaseSource databaseSource;

    @Mock
    LoginContract.AppView loginAppView;

    @Before
    public void setUpTest(){
        MockitoAnnotations.initMocks(this);

        presenter = new LoginPresenter<>(
                authService,
                TestSchedulerProvider.getInstance(),
                new CompositeDisposable(),
                databaseSource);
        presenter.onAttach(loginAppView);
    }

    @After
    public void tearDown() throws Exception {
        presenter.onDetach();
    }

    @Test
    public void whenLoginAttempSucceeds(){
        Mockito.when(loginAppView.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(loginAppView.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(authService.loginAccount(any(Credentials.class)))
                .thenReturn(Single.just(Mockito.mock(User.class)));
        presenter.onLoginClick();
        Mockito.verify(loginAppView).showLoading();
        Mockito.verify(loginAppView).hideLoading();
        Mockito.verify(loginAppView).goToProfilePageActivity();
    }

    @Test
    public void whenLoginAttempFailsWithoutCredentials(){
        Mockito.when(loginAppView.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(loginAppView.getPassword()).thenReturn(VALID_PASSWORD);
        Mockito.when(authService.loginAccount(any(Credentials.class)))
                .thenReturn(Single.<User>error(new Exception("Error to login")));
        presenter.onLoginClick();
        Mockito.verify(loginAppView).showLoading();
        Mockito.verify(loginAppView).hideLoading();
        Mockito.verify(loginAppView).showMessage(Mockito.anyString());
    }

    // The same test that the one above, but now the credentials matter
    @Test
    public void whenLoginAttempFailsWithCredentials () throws Exception {
        Credentials cred = new Credentials(VALID_PASSWORD, "", VALID_EMAIL);
        Mockito.when(authService.loginAccount(cred)).thenReturn(Single.<User>error(new Exception()));
        presenter.attemptLogIn(cred);
        Mockito.verify(loginAppView).showLoading();
        Mockito.verify(loginAppView).hideLoading();
        Mockito.verify(loginAppView).showMessage(Mockito.anyString());
    }

    @Test
    public void whenRegisterButtonIsClicked(){
        presenter.onRegisterClick();
        Mockito.verify(loginAppView).goToCreateAccountActivity();
    }

    @Test
    public void whenEmailIsEmpty(){
        Mockito.when(loginAppView.getEmail()).thenReturn("");
        Mockito.when(loginAppView.getPassword()).thenReturn(VALID_PASSWORD);
        presenter.onLoginClick();
        Mockito.verify(loginAppView).showMessage(Mockito.anyString());
    }

    @Test
    public void whenEmailIsInvalid(){
        Mockito.when(loginAppView.getEmail()).thenReturn(INVALID_EMAIL);
        Mockito.when(loginAppView.getPassword()).thenReturn(VALID_PASSWORD);
        presenter.onLoginClick();
        Mockito.verify(loginAppView).showMessage(Mockito.anyString());
    }


    @Test
    public void whenPasswordIsEmpty(){
        Mockito.when(loginAppView.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(loginAppView.getPassword()).thenReturn("");
        presenter.onLoginClick();
        Mockito.verify(loginAppView).showMessage(Mockito.anyString());
    }

    @Test
    public void whenEmailOrPasswordAreNotValidShowsMessage(){
        Mockito.when(loginAppView.getEmail()).thenReturn(VALID_EMAIL);
        Mockito.when(loginAppView.getPassword()).thenReturn(INVALID_PASSWORD);
        Mockito.when(authService.loginAccount(any(Credentials.class))).thenReturn(Single.<User>error(new Exception()));
        presenter.onLoginClick();
        Mockito.verify(loginAppView).showMessage(Mockito.anyString());
    }
}
